<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: askudnov
  Date: 30.01.2019
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/result">Result</a>
<a href="/create">Create</a>
<a href="/remove">Remove</a>
<a href="/search">Search</a>
<a href="/update">Update</a>

<h1>Search</h1>

<form action="search/" method="get">
    Key:
    <input type="text" name="key" required="required"/>
    <br/>
    <br/>
    <input type="submit" value="найти" style="text-align:center;"/>
</form>
    <p>${getStringValue}</p>


</body>
</html>
