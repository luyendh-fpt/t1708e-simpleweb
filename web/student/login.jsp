<%@ page import="simpleweb.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 2019-06-04
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Student.Role[] roles = (Student.Role[]) request.getAttribute("roles");
%>
<html>
<head>
    <title>Student Login</title>
</head>
<body>
    <h1>Student Form</h1>
    <form action="/login" method="post">
        <div>
            Username <input type="text" name="username">
        </div>
        <div>
            Password <input type="password" name="password">
        </div>
        <div>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </div>
    </form>
</body>
</html>
