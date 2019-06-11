<%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 2019-06-11
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String code = String.valueOf(request.getAttribute("code"));
    String message = String.valueOf(request.getAttribute("message"));
    String content = String.valueOf(request.getAttribute("content"));
%>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h1><%= "Error " + code%> - <%= message%></h1>
    <div>
        <%= content%>
    </div>
</body>
</html>
