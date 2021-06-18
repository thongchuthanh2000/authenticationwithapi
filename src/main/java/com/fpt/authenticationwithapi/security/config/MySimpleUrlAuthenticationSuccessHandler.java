package com.fpt.authenticationwithapi.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class MySimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        handle(request, response, authentication);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        GrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_admin");
        GrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_user");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(roleAdmin)) {
            return "/admin";
        } else if (authorities.contains(roleUser)) {
            return "/main";
        } else {
            throw new IllegalStateException();
        }
    }
}