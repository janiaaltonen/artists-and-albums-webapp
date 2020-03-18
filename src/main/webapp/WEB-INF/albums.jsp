<%@ page language="java" contentType="text/html; utf-8"
         pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Artists</title>
    <script src="/scripts/app.js"></script>
    <link rel="stylesheet" href="/styles/app.css">
</head>

<body>
<h1 class="artist"> Albums of <c:out value="${artist.name}"/> </h1>
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
        <tr id="album-${item.albumId}">
            <td><c:out value="${item.number}"/></td>
            <td><c:out value="${item.title}"/></td>
            <td><c:out value="${item.tracks}"/></td>
            <!-- Foreign key constraint need to be handled before this will be shown
            <td><button class="remove" onclick="removeAlbum(${item.albumId})">&times;</button></td>
            -->
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>