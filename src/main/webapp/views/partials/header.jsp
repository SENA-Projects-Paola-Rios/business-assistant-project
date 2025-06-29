<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    
    if (session == null || session.getAttribute("loggedUser") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Business Assistant</title>
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            min-height: 100vh;
            flex-direction: column;
        }
        .main-content {
            flex: 1;
            display: flex;
        }
        .sidebar {
            width: 200px;
            background-color: #343a40;
            padding-top: 20px;
            min-height: 100vh;
        }
        .sidebar a {
            color: #fff;
            padding: 10px 20px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
    </style>
</head>
<body>
<div class="main-content">
