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
    Student student = (Student) request.getAttribute("student");
%>
<html>
<head>
    <title>Student Update</title>
</head>
<body>
    <h1>Student Form</h1>
    <form action="/member/update" method="post">
        <div>
            Username <input type="text" name="username" value="<%= student.getUsername()%>">
        </div>
        <div>
            Full Name <input type="text" name="fullName" value="<%= student.getFullName()%>">
        </div>
        <div>
            Email <input type="text" name="email" value="<%= student.getEmail()%>">
        </div>
        <div>
            Address <input type="text" name="address" value="<%= student.getAddress()%>">
        </div>
        <div>
            Phone <input type="text" name="phone" value="<%= student.getPhone()%>">
        </div>
        <div>
            Role <select name="role">
                    <%
                        for (Student.Role r : roles) {
                    %>
                        <option value="<%=r.getValue()%>" <%= r.getValue() == student.getRole() ? "selected": ""%>>
                            <%=r.name()%>
                        </option>
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
