package com.login;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/save")
public class RegistrationDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
            try {
                PreparedStatement st = ConnectionClass.getConnection().prepareStatement
                        ("insert into customer(Name,EmailId,Password,RegisteredDate) values(?,?,?,?)");
                st.setString(1, request.getParameter("Name"));
                st.setString(2, request.getParameter("EmailId"));
                st.setString(3, request.getParameter("Password"));
                st.setString(4, request.getParameter("RegisteredDate"));
                st.executeUpdate();

                out.print("Registration successfully");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

