package com.surya.filters;

import com.surya.authentications.OtpAuthentiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class OtpFlilter implements Filter {
    @Autowired
    AuthenticationManager am;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;
        String otp=req.getHeader("otp");
        String name=req.getHeader("uname");
        OtpAuthentiction otpAuthentiction=new OtpAuthentiction(name,otp,null);
        Authentication auth=am.authenticate(otpAuthentiction);
        if(auth.isAuthenticated())
        {
            ((HttpServletResponse) response).setHeader("Authorization", UUID.randomUUID().toString());
//            chain.doFilter(request,response);
        }

    }
}
