<%
    if (session == null || session.getAttribute("loggedUser") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.model.Category" %>

<jsp:include page="../partials/header.jsp" />
<jsp:include page="../partials/sidebar.jsp" />

<%
    List<Category> categoryList = (List<Category>) request.getAttribute("categories");
%>

<div class="main-content" style="margin-left: 10px; padding: 20px;">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2>Category List</h2>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#categoryModal">Add Category</button>
        </div>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (categoryList != null) {
                        for (Category category : categoryList) {
                %>
                <tr>
                    <td><%= category.getId() %></td>
                    <td><%= category.getName() %></td>
                    <td><%= category.getDescription() %></td>
                    <td>
                        <button class="btn btn-info btn-sm" onclick="viewCategory(<%= category.getId() %>)">View</button>
                        <button class="btn btn-warning btn-sm" onclick="editCategory(<%= category.getId() %>)">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="confirmDelete(<%= category.getId() %>)">Delete</button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal de creación/edición de categoría -->
<jsp:include page="category-modal.jsp" />

<jsp:include page="../partials/footer.jsp" />

<!-- View Category Modal -->
<div class="modal fade" id="viewCategoryModal" tabindex="-1" aria-labelledby="viewCategoryModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="viewCategoryModalLabel">Category Details</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p><strong>ID:</strong> <span id="viewCategoryId"></span></p>
        <p><strong>Name:</strong> <span id="viewCategoryName"></span></p>
        <p><strong>Description:</strong> <span id="viewCategoryDescription"></span></p>
      </div>
    </div>
  </div>
</div>

<!-- Scripts para acciones -->
<script>
    function confirmDelete(id) {
        if (confirm("Are you sure you want to delete this category?")) {
            window.location.href = "CategoryController?action=delete&id=" + id;
        }
    }

    function viewCategory(id) {
        fetch("CategoryController?action=json&id=" + id)
          .then(response => response.json())
          .then(data => {
            document.getElementById("viewCategoryId").textContent = data.id;
            document.getElementById("viewCategoryName").textContent = data.name;
            document.getElementById("viewCategoryDescription").textContent = data.description;
            new bootstrap.Modal(document.getElementById("viewCategoryModal")).show();
          })
          .catch(error => {
            alert("Error fetching category data.");
            console.error(error);
          });
    }

    function editCategory(id) {
        fetch("CategoryController?action=json&id=" + id)
          .then(response => response.json())
          .then(data => {
            document.getElementById("categoryId").value = data.id;
            document.getElementById("categoryName").value = data.name;
            document.getElementById("categoryDescription").value = data.description;
            new bootstrap.Modal(document.getElementById("categoryModal")).show();
          });
    }
</script>
