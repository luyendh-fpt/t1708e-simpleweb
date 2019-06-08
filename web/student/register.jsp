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
    <title>Student Register</title>
</head>
<body>
    <h1>Student Form</h1>
    <form action="/student/register" method="post">
        <div>
            Username <input type="text" name="username">
        </div>
        <div>
            Password <input type="password" name="password">
        </div>
        <div>
            Confirm password <input type="password" name="confirmPassword">
        </div>
        <div>
            Full Name <input type="text" name="fullName">
        </div>
        <div>
            Email <input type="text" name="email">
        </div>
        <div>
            Address <input type="text" name="address">
        </div>
        <div>
            Phone <input type="text" name="phone">
        </div>
        <div>
            Role <select name="role">
                    <%
                        for (Student.Role r : roles) {
                    %>
                        <option value="<%=r.getValue()%>"><%=r.name()%></option>
                    <%
                        }
                    %>
                 </select>
        </div>
        <div>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </div>
    </form>
</body>
</html>
