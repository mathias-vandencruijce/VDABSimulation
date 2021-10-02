<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Lessen Overzicht</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="currentClass"/>
        </jsp:include>

        <div class="container p-3 my-3 border">
            <p>ID: ${sessionScope.CurrentClass.id}</p>
            <p>Lector: ${sessionScope.CurrentClass.lector}</p>
            <p>Les: ${sessionScope.CurrentClass.vak} </p>
            <p>Van: ${sessionScope.CurrentClass.vanTijd} </p>
            <p>Tot: ${sessionScope.CurrentClass.totTijd}</p>

            <c:choose>
                <c:when test="${not empty deelnemers}">
                    <h2>Aanwezigen:</h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>userid</th>
                                <th>Naam</th>
                                <th>Reeks</th>
                                <th>Commentaar</th>
                                <th>Edit Commentaar</th>
                                <th>Verwijder</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="deelnemer" items="${deelnemers}">
                                <tr>
                                    <td><c:out value='${deelnemer.userid}'/></td>
                                    <td><c:out value="${deelnemer.voornaam}"/> <c:out value="${deelnemer.achternaam}"/></td>
                                    <td><c:out value='${deelnemer.reeks}'/></td>
                                    <td><c:out value="${deelnemer.commentaar}"/></td>
                                    <td>
                                        <a href="Controller?command=GoToEditCommentaar&studentNr=${deelnemer.userid}&idLes=${sessionScope.CurrentClass.id}&destination=CurrentClass">Edit
                                            commentaar</a></td>
                                    <td>
                                        <a href="Controller?command=removerParticipant&id=${deelnemer.userid}&lesId=${sessionScope.CurrentClass.id}">Verwijder</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Er zijn nog geen studenten tot de les toegetreden.</p>
                </c:otherwise>
            </c:choose>
            <form novalidate="novalidate" method="post"
                  action="Controller?command=CloseClass&lesId=${sessionScope.CurrentClass.id}">
                <input class="btn btn-primary" type="submit" id="bevestig" value="Beëindig les">
            </form>
            <br>

            <c:choose>
                <c:when test="${not empty afwezigen}">
                    <h2>Niet aanwezigen:</h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>userid</th>
                                <th>Naam</th>
                                <th>Reeks</th>
                                <th>Toevoegen</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="deelnemer" items="${afwezigen}">
                                <tr>
                                    <td><c:out value='${deelnemer.userid}'/></td>
                                    <td><c:out value="${deelnemer.voornaam}"/> <c:out value="${deelnemer.achternaam}"/></td>
                                    <td><c:out value='${deelnemer.reeks}'/></td>
                                    <td>
                                        <a href="Controller?command=AddParticipant&id=${deelnemer.userid}&lesId=${sessionScope.CurrentClass.id}">Toevoegen</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Er zijn geen studenten die niet aanwezig zijn.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
</body>
</html>
