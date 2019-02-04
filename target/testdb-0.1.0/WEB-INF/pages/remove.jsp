<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove</title>
</head>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var prefix = '/remove';
    var Remove = function() {
        var key = document.getElementById("findKey").value;
        var idGroup = document.getElementById("idGroup").value;
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: prefix + key,
            data: key +" "+ idGroup,
            dataType: 'text',
            success: function (data) {
                alert(data);
                location.reload()
            }
        });
    }
</script>
<body>

<h3>Search</h3>
<a href="/result">Result</a>
<a href="/create">Create</a>
<a href="/remove">Remove</a>
<a href="/search">Search</a>
<a href="/update">Update</a>
<br/>
<br/>

<table>
    <tr>
        <th>Key</th>
    </tr>
    <tr>
        <td><input id="findKey" name="key" type="text" value=""></td>
    </tr>
    <tr>
        <br/>
        <br/>
    Алфавит:
    <select id="idGroup" name="idGroup">
        <c:forEach var="grps" items="${alphaStringList}">
            <option value="${grps}">
                <c:out value="${grps}"/>
            </option>
        </c:forEach>
    </select>
    </tr>

    <tr>
        <td><button onclick="Remove()">Remove</button></td>

    </tr>
</table>


</body>
</html>