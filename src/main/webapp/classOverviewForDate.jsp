<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <title>Lessen Overzicht</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<main class="container">
    <div class="col-12">

        <jsp:include page="header.jsp">
            <jsp:param name="page" value="classOverview"/>
        </jsp:include>
        <div class="container p-3 my-3 border">
            <c:choose>
                <c:when test="${not empty lessen}">
                    <h2>Lessen overzicht voor ${date}:</h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Vak</th>
                                <th>Datum</th>
                                <th>Start</th>
                                <th>Eind</th>
                                <th>Fysiek/Online</th>
                                <th>Actie</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="les" items="${lessen}">
                                <tr>
                                    <td><c:out value='${les.vak}'/></td>
                                    <td><c:out value="${les.datum}"/></td>
                                    <td><c:out value='${les.vanTijd}'/></td>
                                    <td><c:out value='${les.totTijd}'/></td>
                                    <td><c:out value="${les.plaats}"/></td>
                                    <td><a href="Controller?command=ChangeClass&id=${les.id}">Change Class</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Er waren geen lessen op ${date}.</p>
                    <a href="Controller?command=viewTodaysClasses">Terug</a>
                </c:otherwise>
            </c:choose>
        </div>
</main>
</body>
</html>
