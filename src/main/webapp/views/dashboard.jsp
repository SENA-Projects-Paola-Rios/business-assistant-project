<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>


<jsp:include page="partials/header.jsp" />
<jsp:include page="partials/sidebar.jsp" />

<!-- Contenido principal -->
<div class="main-content" style="margin-left: 250px; padding: 20px;">
    <div class="container-fluid">
        <h1 class="mt-4">Dashboard</h1>
        <p>Welcome to the Business Assistant system!</p>

        <div class="alert alert-success" role="alert">
            You are now logged in as <strong><%= ((com.sena.model.User) session.getAttribute("loggedUser")).getName() %></strong>.
        </div>
    </div>
</div>

<jsp:include page="partials/footer.jsp" />
