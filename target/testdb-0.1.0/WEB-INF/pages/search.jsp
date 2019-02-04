<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var prefix = '/search/';
    var Search = function() {
        var key = document.getElementById("findKey").value;

        $.ajax({
            type: 'GET',
            url:  prefix + '/' +key,
            dataType: 'json',
            async: true,
            success: function(result) {
                alert(result);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
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


<table>
    <tr>
        <th>Key</th>
    </tr>
    <tr>
        <td><input id="findKey" name="key" type="text" value=""></td>
    </tr>
    <tr>
        <td><button onclick="Search()">Search</button></td>

    </tr>
</table>


</body>
</html>