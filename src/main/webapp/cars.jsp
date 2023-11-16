<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in Failed Page</title>
</head>
<body>
<table>
    <tr>
        <th>Car ID</th>
        <th>Mark</th>
        <th>Model</th>
        <th>Year</th>
        <th>Owner ID</th>

    </tr>
    <tr>
        <jsp:useBean id="car" scope="request" type="java.util.List"/>
        <c:forEach var="cars" items="${car}">
            <td>${cars.id}</td>
            <td>${cars.mark}</td>
            <td>${cars.model}</td>
            <td>${cars.year}</td>
            <td>${cars.owner_id}</td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
