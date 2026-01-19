package com.microsoft;

import com.microsoft.mapper.DeptMapper;
import com.microsoft.mapper.StudentMapper;
import com.microsoft.pojo.Dept;
import com.microsoft.service.StudentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class TliasLearningSupportSystemApplicationTests {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @Test
    public void testViolationAction() {
        studentService.violationAction(2, 3);
    }

    @Test
    public void testGenerateJwt() {
        SecretKey key = Keys.hmacShaKeyFor("ZnJhbmtTb2Z0ZnJhbmtTb2Z0ZnJhbmtTb2Z0".getBytes());
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "frank");
        dataMap.put("password", 1);
        Date expirationDate = new Date(System.currentTimeMillis() + 3600 * 1000);
        String jwt = Jwts.
                builder().
                signWith(key).
                claims(dataMap).
                expiration(expirationDate).
                compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6MSwibmFtZSI6ImZyYW5rIiwiZXhwIjoxNzY4ODIwNTExfQ.DT_CRpYuWVXj-hZMBfXdUhviHWaMEiiyD4RpYuRs6vc";
        SecretKey key = Keys.hmacShaKeyFor("ZnJhbmtTb2Z0ZnJhbmtTb2Z0ZnJhbmtTb2Z0".getBytes());
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
        System.out.println(payload);
    }

}
