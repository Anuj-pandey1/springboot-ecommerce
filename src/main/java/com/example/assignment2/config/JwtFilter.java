package com.example.assignment2.config;

import com.example.assignment2.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

//@Configuration
public class JwtFilter extends GenericFilterBean {
    private TokenService tokenService;

    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String token = httpServletRequest.getHeader("Authorization");
        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "Success");
            return;
        }

        if (allowRequestWithoutToken(httpServletRequest)) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "Success");
        } else {
            Integer id = new Integer(tokenService.getUserIdByToken(token));
            httpServletRequest.setAttribute("userId", "id");
            filterChain.doFilter(req, res);
        }
    }

    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getRequestURI());
        if (httpServletRequest.getRequestURI()=="/user/registration") {
            System.out.println("dfg");
            return true;
        }

        return false;
    }
}