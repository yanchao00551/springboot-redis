package com.power.modules.app.interceptor;

import com.power.common.exception.RRException;
import com.power.modules.app.annotation.Login;
import com.power.modules.app.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * 权限（TOKEN）验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtils jwtUtils;

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if(handler instanceof HandlerMethod){
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());  //如果Header请求头里存在内容 ，此行还没有token
        if(StringUtils.isBlank(token)){ //如果请求头里存在内容
            token = request.getParameter(jwtUtils.getHeader());  //拿到了token
        }
        //凭证为空
        if (StringUtils.isBlank(token)) {
            throw new RRException(jwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }
        Claims claims = jwtUtils.getClaimByToken(token);  //此行解密token
        if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
            throw new RRException(jwtUtils.getHeader() + "失效，请重新登录",HttpStatus.UNAUTHORIZED.value());
        }

        //设置userId到request里，后续跟进userId,获取用户信息
        request.setAttribute(USER_KEY,Long.parseLong(claims.getSubject()));

        return true;
    }
}
