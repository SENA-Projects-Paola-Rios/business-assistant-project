<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.model.User" %>

<jsp:include page="../partials/header.jsp" />
<jsp:include page="../partials/sidebar.jsp" />

<%
    List<User> userList = (List<User>) request.getAttribute("users");
%>

<div class="main-content" style="margin-left: 250px; padding: 20px;">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2>User List</h2>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#userModal">Add User</button>
        </div>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (userList != null) {
                        for (User user : userList) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td>
                        <button class="btn btn-info btn-sm" onclick="viewUser(<%= user.getId() %>)">View</button>
                        <button class="btn btn-warning btn-sm" onclick="editUser(<%= user.getId() %>)">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="confirmDelete(<%= user.getId() %>)">Delete</button>
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

<!-- Modal para agregar/editar usuario -->
<jsp:include page="user-modal.jsp" />

<jsp:include page="../partials/footer.jsp" />

<!-- Script para confirmar eliminación -->
<script>
    function confirmDelete(id) {
        if (confirm("Are you sure you want to delete this user?")) {
            window.location.href = "UserController?action=delete&id=" + id;
        }
    }

    function viewUser(id) {
        alert("View user with ID " + id); // puedes personalizar
    }

    function editUser(id) {
        // cargar datos en el modal
        fetch("UserController?action=edit&id=" + id)
            .then(response => response.json())
            .then(data => {
                document.getElementById("userId").value = data.id;
                document.getElementById("name").value = data.name;
                document.getElementById("email").value = data.email;
                document.getElementById("role").value = data.role;
                document.getElementById("password").value = ""; // dejar vacío
                new bootstrap.Modal(document.getElementById("userModal")).show();
            });
    }
</script>
