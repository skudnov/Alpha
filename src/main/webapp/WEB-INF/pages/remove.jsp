<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: askudnov
  Date: 30.01.2019
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>

<a href="/result">Result</a>
<a href="/create">Create</a>
<a href="/remove">Remove</a>
<a href="/search">Search</a>
<a href="/update">Update</a>

<h1>Remove</h1>

<form action="remove" method="post">
    Key:
    <input type="text" name="key" required="required"/>
    <br/>
    <br/>
    Алфавит:
    <select name="idGroup">
        <c:forEach var="grps" items="${alphaStringList}">
            <option value="${grps}">
                <c:out value="${grps}"/>
            </option>
        </c:forEach>
    </select>
    <br/>
    <br/>
    <input type="submit" name="submit" value="удалить" style="text-align:center;"/>
</form>

</body>
</html>