package com.login;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse) response;
        String path=req.getRequestURI();
        String userName=req.getParameter("UserName");
        if(path.contains("Login"))
        {
            if(userName==null) {
                ((HttpServletResponse) response).sendRedirect("index.jsp");
            }
        }
        filterChain.doFilter(req,res);
    }

    @Override
    public void destroy() {
    }
}
