package com.login;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
import static java.lang.String.valueOf;

@WebServlet("/Login")
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        Pattern pattern1 = null;
        Pattern pattern2 = null;
        pattern1 = pattern1.compile("[A-Z][a-z 0-9]{3,}");
        pattern2 = pattern2.compile("[0-9]{4,}");

        if ((pattern1.matches(valueOf(pattern1), UserName)) && (pattern2.matches(valueOf(pattern2), Password))) {
            response.sendRedirect("Welcome.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}