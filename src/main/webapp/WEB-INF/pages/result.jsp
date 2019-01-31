<%@page import="java.util.Hashtable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%> <%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: askudnov
  Date: 30.01.2019
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Result</title>
</head>
<body>
<a href="/result">Result</a>
<a href="/create">Create</a>
<a href="/remove">Remove</a>
<a href="/search">Search</a>
<a href="/update">Update</a>

<h1>Alphabet</h1>

<table> <c:forEach var="entry" items="${alphabeted}">
    <tr><td><c:out value="${entry.key}"/></td> <td><c:out value="${entry.value}"/>
    </td></tr> </c:forEach>
</table>

</body>
</html>