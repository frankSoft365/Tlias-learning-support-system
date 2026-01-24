package com.microsoft.interceptor;

import com.microsoft.utils.CurrentHold;
import com.microsoft.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    // 这个方法在回到拦截器时调用，作用是清理ThreadLocal
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        CurrentHold.removeId();
        log.info("清理ThreadLocal");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // localStorage没有token。因为首次登录请求是POST，且localStorage没有token请求头不包含Authorization，不发送OPTION请求。
        // 登录成功后，localStorage有token，再发送GET请求：因为请求头包含Authorization，发送OPTION请求。OPTION进入拦截器并被放行，浏览器收到并缓存；GET请求因为有token而顺利放行。
        // 之后再发送各种业务请求，因为OPTION缓存，时效内不再发送OPTION请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.info("直接放行OPTION请求");
            return true;
        }
        // 看这个请求有没有token 有则检查token有效性 没有则拒绝访问接口 返回401
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
        }
        if (token == null || token.isEmpty()) {
            log.info("没有token，拒绝访问接口");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 检查token有效性，无效则拒绝访问接口 返回401
        try {
            Claims claims = JwtUtils.parseToken(token);
            // 拿到员工id
            Integer id = Integer.valueOf(claims.get("id").toString());
            // 存入ThreadLocal
            CurrentHold.setCurrentId(id);
            log.info("将员工id：{}存入ThreadLocal", id);
        } catch (Exception e) {
            log.info("token无效，拒绝访问接口");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 有效则允许访问接口
        log.info("token有效，允许访问接口");
        return true;
    }
}
