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
        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        Pattern pattern1 = null;
        Pattern pattern2 = null;
        pattern1 = pattern1.compile("[A-Z][a-z 0-9]{3,}");
        pattern2 = pattern2.compile("[0-9]{4,}");
        boolean isPresent = false;

        if ((pattern1.matches(valueOf(pattern1), UserName)) && (pattern2.matches(valueOf(pattern2), Password))) {
            System.out.println("in loop");
            try {
                PreparedStatement ps = ConnectionClass.getConnection().prepareStatement("select * from customer where Name=\'"+UserName+"\'" +
                        "AND Password=\'"+Password+"\'");
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
                RequestDispatcher rd=request.getRequestDispatcher("Login");
                rd.forward(request, response);
            }

        } else {
            out.print("Sorry UserName or Password Error!");
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);
        }
    }
}