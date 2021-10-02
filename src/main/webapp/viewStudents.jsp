<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Studenten Overzicht</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
<main class="container">
    <div class="col-12">

        <jsp:include page="header.jsp">
            <jsp:param name="page" value="viewStudents"/>
        </jsp:include>

        <div class="container p-3 my-3 border">
            <c:if test="${not empty studenten}">
                <h2>Alle studenten:</h2>
                <form method="post" novalidate="novalidate" action="Controller?command=ConfirmVDAB">
                    <p><input class="btn btn-primary" type="submit" id="bevestig" value="Bevestig"></p>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>userid</th>
                                <th>Reeks</th>
                                <th>Vdab</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="student" items="${studenten}">
                                <tr>
                                    <td><c:out value='${student.userid}'/></td>
                                    <td><c:out value='${student.reeks}'/></td>
                                    <td><input type="checkbox" id="vdab" name="vdab" value="${student.userid}"
                                               <c:if test="${student.vdab eq true}">checked</c:if> ></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
