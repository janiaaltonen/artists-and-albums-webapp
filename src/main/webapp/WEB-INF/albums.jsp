<%@ page language="java" contentType="text/html; utf-8"
         pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Artists</title>

</head>

<body>
<h1> List of all the albums</h1>
<table>
    <thead>
    <tr><th>Name</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${albumList}" var="item">
        <tr id="artist-${item.albumId}">
            <td><c:out value="${item.number}"/></td>
            <td><c:out value="${item.title}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>