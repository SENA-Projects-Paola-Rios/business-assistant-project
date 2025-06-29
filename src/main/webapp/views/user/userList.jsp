<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.model.User" %>

<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>

<%@ include file="../partials/header.jsp" %>
<%@ include file="../partials/sidebar.jsp" %>

<h2 class="mb-4">User List</h2>

<!-- Button to trigger modal -->
<button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#userModal"
        onclick="openAddUserModal()">Add User</button>

<!-- User Table -->
<table class="table table-bordered table-striped">
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
            for (User u : userList) {
    %>
    <tr>
        <td><%= u.getIdUser() %></td>
        <td><%= u.getName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getRole() %></td>
        <td>
            <a href="UserController?action=edit&id=<%=u.getIdUser()%>" class="btn btn-sm btn-warning">Edit</a>
            <a href="Us
