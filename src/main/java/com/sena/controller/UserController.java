package com.sena.controller;

import com.sena.dao.UserDAO;
import com.sena.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class UserController extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "view":
                showDetails(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/user/user-list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/user/user-form.jsp").forward(request, response);
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/user/user-view.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.softDelete(id);
        response.sendRedirect("UserController?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("saving user");
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (idParam == null || idParam.isEmpty()) {
            LOGGER.info("create");
            // Create
            User newUser = new User(0, name, email, password, role, null);
            userDAO.insert(newUser);
        } else {
            LOGGER.info("update");
            // Update
            int id = Integer.parseInt(idParam);
            User updatedUser = new User(id, name, email, password, role, null);
            userDAO.update(updatedUser);
        }

        response.sendRedirect("UserController");
    }
}
