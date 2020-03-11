<%@ page language="java" contentType="text/html; utf-8"
         pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Artists</title>
    <script src="/scripts/front.js"></script>
</head>

<body>
    <h1> List of all the artists</h1>
    <table>
        <thead>
        <tr><th>Name</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${artistList}" var="item">
            <tr id="artist-${item.id}">
                <td><c:out value="${item.number}"/></td>
                <td><a href="${pageContext.request.contextPath}/albums?artistId=${item.id}"><c:out value="${item.name}"/></a></td>
                <td><c:out value="${item.albums}"/> </td>
                <td><button class="remove" onclick="removeArtist(${item.id})">&times;</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2> Add new artist to list</h2>
    <form action="/artists" id="add-new-form" method="post">
        <input id="new-item-name" name="name" required type="text" placeholder=" type artist name here..." autofocus />
        <input type="submit" id="add-new-item" value="Add to list" />
    </form>

</body>
</html>