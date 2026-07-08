package com.example.filter;

import com.example.utils.CurrentHolder;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取到请求路径
        try {
            String requestURI = request.getRequestURI(); // /employee/login


            //2. 判断是否是登录请求, 如果路径中包含 /login, 说明是登录操作, 放行
            if (requestURI.contains("/login")) {
                log.info("登录请求, 放行");
                filterChain.doFilter(request, response);
                return;
            }

            //3. 获取请求头中的token
            String token = request.getHeader("token");

            //4. 判断token是否存在, 如果不存在, 说明用户没有登录, 返回错误信息(响应401状态码)
            if (token == null || token.isEmpty()) {
                log.info("令牌为空, 响应401");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            //5. 如果token存在, 校验令牌, 如果校验失败 -> 返回错误信息(响应401状态码)
            try {
                Claims claims = JwtUtils.parseToken(token);
                // 新增日志：打印解析出的 id
                log.info("解析 Token 得到的员工 ID: {}", claims.get("id"));
                Integer empId = Integer.valueOf(claims.get("id").toString());
                CurrentHolder.setCurrentId(empId);
                log.info("存入 ThreadLocal 的员工 ID: {}", CurrentHolder.getCurrentId()); // 验证存值是否成功
            } catch (Exception e) {
                log.error("Token 解析失败", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            //6. 校验通过, 放行
            log.info("令牌合法, 放行");
            filterChain.doFilter(request, response);
        } finally { // 新增finally块
            //7. 删除ThreadLocal中的数据 (请求处理完成后才清除)
            CurrentHolder.remove();
            log.info("清除ThreadLocal中的员工ID");
        }
    }
}
