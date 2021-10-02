<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <title>Lessen Overzicht</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="css/sample.css">
</head>
<body>
<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="classOverview"/>
        </jsp:include>

        <div class="container p-3 my-3 border">

            <c:if test="${not empty error}">
                <div id="fout" class="alert alert-danger">
                    <ul>
                        <li><c:out value='${error}'/></li>
                    </ul>
                </div>
            </c:if>

            <form novalidate="novalidate" action="Controller?command=viewClassesForDate" method="post">
                <label for="date">Zoek lessen voor datum: </label> <input type="date" name="date" id="date">
                <input type="submit" class="btn btn-primary" id="zoek" value="Zoek"></p>
            </form>

            <c:choose>
                <c:when test="${not empty lessen}">
                    <h2>Lessen overzicht voor vandaag:</h2>
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
                                    <td><c:out value="${les.plaats}"/><a href="Controller?command=changePlaats&plaats=${les.plaats}&id=${les.id}"> Change</a></td>
                                    <c:choose>
                                        <c:when test="${actieveLes and les.joinable == true}">
                                            <td><a href="Controller?command=startClass&id=${les.id}">Actieve Les</a></td>
                                        </c:when>
                                        <c:when test="${actieveLes and les.joinable == false}">
                                            <td><p>N/A</p></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href="Controller?command=startClass&id=${les.id}">Start Les</a></td>
                                        </c:otherwise>
                                    </c:choose>


                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Er zijn geen geplande lessen vandaag.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
</body>
</html>