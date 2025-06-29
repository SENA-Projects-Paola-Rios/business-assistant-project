package com.sena.controller;

import com.sena.dao.UserDAO;
import com.sena.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;



public class LoginController extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        

        User user = userDAO.login(email, password);
        
               

        if (user != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("loggedUser", user);
            response.sendRedirect("views/dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid credentials.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
