package com.microsoft.aop;

import com.microsoft.mapper.OperateLogMapper;
import com.microsoft.pojo.OperateLog;
import com.microsoft.utils.CurrentHold;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.microsoft.anno.LogOperation)")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        long costTime = end - begin;
        log.info("耗时{}ms", costTime);
        // 添加日志
        OperateLog operateLog = new OperateLog();
        Integer currentId = CurrentHold.getCurrentId();
        operateLog.setOperateEmpId(currentId);
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(pjp.getTarget().getClass().getName());
        operateLog.setMethodName(pjp.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);
        // 插入日志
        operateLogMapper.insert(operateLog);
        return result;
    }
}
