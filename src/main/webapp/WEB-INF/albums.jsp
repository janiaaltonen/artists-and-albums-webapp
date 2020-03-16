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
<h1 class="artist"> <c:out value="${artist.name}"/></h1>
<table>
    <thead>
    <tr>
        <th> # </th>
        <th> Title </th>
        <th> Tracks </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${albumList}" var="item">
        <tr id="artist-${item.albumId}">
            <td><c:out value="${item.number}"/></td>
            <td><c:out value="${item.title}"/></td>
            <td><c:out value="${item.tracks}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>