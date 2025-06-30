package com.sena.controller;

import com.sena.dao.CategoryDAO;
import com.sena.model.Category;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CategoryController extends HttpServlet {

    private final CategoryDAO categoryDAO = new CategoryDAO();

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
                softDeleteCategory(request, response);
                break;
            case "json":
                getCategoryAsJson(request, response);
                break;
            default:
                listCategories(request, response);
                break;
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/views/category/category-list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.findById(id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/category/category-form.jsp").forward(request, response);
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.findById(id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/category/category-view.jsp").forward(request, response);
    }

    private void getCategoryAsJson(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.findById(id);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(category));
    }

    private void softDeleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryDAO.softDelete(id);  // ✅ uso de softDelete
        response.sendRedirect("CategoryController?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (idParam == null || idParam.isEmpty()) {
            // Create
            Category newCategory = new Category(name, description);
            categoryDAO.insert(newCategory);
        } else {
            // Update
            int id = Integer.parseInt(idParam);
            Category updatedCategory = new Category(id, name, description, null); // null en deletedAt
            categoryDAO.update(updatedCategory);
        }

        response.sendRedirect("CategoryController?action=list");
    }
}
