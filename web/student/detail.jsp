<%@ page import="simpleweb.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 2019-06-04
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Student student = (Student) request.getAttribute("student");
%>
<html>
<head>
    <title>Student Detail</title>
</head>
<body>
    <h1>Student Profile</h1>
    <div>
        Username <%= student.getUsername()%>
    </div>
    <div>
        Fullname <%= student.getFullName()%>
    </div>
    <div>
        Email <%= student.getEmail()%>
    </div>
    <div>
        Address <%= student.getAddress()%>
    </div>
</body>
</html>
