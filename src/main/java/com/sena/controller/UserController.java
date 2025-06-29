package com.sena.controller;

import com.sena.dao.UserDAO;
import com.sena.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listUsers(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                listUsers(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<User> users = userDAO.getAll();
        request.setAttribute("userList", users);
        request.getRequestDispatcher("views/user/userList.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        User user = extractUserFromRequest(request);
        userDAO.insert(user);
        response.sendRedirect("UserController?action=list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        User user = extractUserFromRequest(request);
        user.setIdUser(Integer.parseInt(request.getParameter("idUser")));
        userDAO.update(user);
        response.sendRedirect("UserController?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.getById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("views/user/userList.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(id);
        response.sendRedirect("UserController?action=list");
    }

    private User extractUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));
        user.setAccessRestriction(request.getParameter("accessRestriction"));
        return user;
    }
}
