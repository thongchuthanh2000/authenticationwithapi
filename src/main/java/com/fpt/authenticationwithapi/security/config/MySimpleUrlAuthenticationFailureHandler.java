package com.fpt.authenticationwithapi.security.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String msg = "";
        if (e.getClass().isAssignableFrom(BadCredentialsException.class)) {
            msg = "Invalid username or password";
        } else {
            msg = "Error - " + e.getMessage();
        }
        httpServletRequest.getSession().setAttribute("msg", msg);
        httpServletResponse.sendRedirect("/login");
    }
}
