<%-- 
    Document   : index
    Created on : Jun 29, 2025, 10:57:14 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>Hello World4!</h1>
    </body>
</html>
