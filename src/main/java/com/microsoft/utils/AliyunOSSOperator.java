package com.microsoft.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {
    // Endpoint 北京
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    // Bucket名称 java-web-frank
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    // Bucket所在地域。北京 Region填写为cn-beijing
    @Value("${aliyun.oss.region}")
    private String region;

    /**
     * 根据阿里云javaSDK文件上传方法改写
     * 通过文件本身的字节数组与文件原始名称将文件上传到阿里云OSS中并返回文件的url
     * @param content 二进制文件字节数组
     * @param objectName 文件原始名称 如 001.jpg
     * @return 该文件在阿里云OSS中的url
     * @throws ClientException
     */
    public String upload(byte[] content, String objectName) throws ClientException {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 重设置文件名 示例 2025/01/xxxxx.jpg
        // 获取日期形式文件夹名称
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        // 获取原文件名的后缀
        String fileExtension = objectName.substring(objectName.lastIndexOf("."));
        // 获取包装后的新文件名
        String resultObjectName = dir + "/" + UUID.randomUUID() + fileExtension;

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, resultObjectName, new ByteArrayInputStream(content));
            // 创建PutObject请求。
            ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            ossClient.shutdown();
        }
        // 将返回的url进行包装 规则是：原本的endpoint中连接"bucketName." 后接文件存放路径名：resultObjectName
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + resultObjectName;
    }
}
