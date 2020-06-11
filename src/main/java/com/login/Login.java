package com.login;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import static java.lang.String.valueOf;
@WebServlet("/Login")
public class Login extends HttpServlet {
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("UserName");
        String password = request.getParameter("Password");
        Pattern userNamePattern = null;
        Pattern passwordPattern = null;
        userNamePattern = userNamePattern.compile("[A-Z][a-z 0-9]{3,}");
        passwordPattern = passwordPattern.compile("[0-9]{4,}");
        boolean isPresent = false;

        if ((userNamePattern.matches(valueOf(userNamePattern), userName)) && (passwordPattern.matches(valueOf(passwordPattern), password))) {
            System.out.println("in loop");
            try {
                PreparedStatement ps = ConnectionClass.getConnection().prepareStatement("select * from customer where Name=\'"+userName+"\'" +
                        "AND Password=\'"+password+"\'");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(rs);
                    isPresent = true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (isPresent) {
                out.print(" Login successfully");
                RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            }

        } else {
            out.print(" UserName or Password Invalid!");
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);
        }
    }
}