<%@ page import="java.util.ArrayList" %>
<%@ page import="simpleweb.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 2019-06-04
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ArrayList<Student> list =  (ArrayList<Student>) request.getAttribute("list");
  if(list == null){
      list = new ArrayList<>();
  }
%>
<html>
  <head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  </head>
  <body>
  <h2>Hello Controller</h2>
  <ul>
    <%
      for (int i = 0; i < list.size(); i++) {
    %>
        <li><%= list.get(i).getUsername()%></li>
    <%
      }
    %>
  </ul>
  </body>
</html>
