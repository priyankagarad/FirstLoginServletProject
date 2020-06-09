package com.login;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/save")
public class RegistrationDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            try {
                PreparedStatement st = ConnectionClass.getConnection().prepareStatement
                        ("insert into customer(Name,EmailId,Password,RegisteredDate) values(?,?,?,?)");
                st.setString(1, request.getParameter("Name"));
                st.setString(2, request.getParameter("EmailId"));
                st.setString(3, request.getParameter("Password"));
                st.setString(4, request.getParameter("RegisteredDate"));

                st.executeUpdate();
                System.out.println("data save successfully");

                response.sendRedirect("saveData.jsp");
                st.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
