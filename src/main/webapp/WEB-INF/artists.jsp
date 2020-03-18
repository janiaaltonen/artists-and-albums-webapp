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
    <h1> List of all the artists</h1>
    <p> <span onclick="showForm()"> Add new artist to list </span> </p>
    <form class="form" action="/artists" id="add_new_form" method="post">
        <input id="new-item-name" name="name" required type="text" placeholder=" type artist name here..." autofocus />
        <input type="submit" id="add-new-item" value="Add to list" />
    </form>
    <table>
        <thead>
        <tr>
            <th> # </th>
            <th> Artist </th>
            <th> Albums </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${artistList}" var="item">
            <c:if test="${item.albums > 0}">
                <tr id="artist-${item.id}">
                    <td><c:out value="${item.number}"/></td>
                    <td><a href="${pageContext.request.contextPath}/albums?artistId=${item.id}"><c:out value="${item.name}"/></a></td>
                    <td><c:out value="${item.albums}"/> </td>
                </tr>
            </c:if>
            <c:if test="${item.albums == 0}">
                <tr id="artist-${item.id}">
                    <td><c:out value="${item.number}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.albums}"/> </td>
                    <td><button class="remove" onclick="removeArtist(${item.id})">&times;</button></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>