<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gerbe
  Date: 16/12/2020
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>studentencoach</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>


<body>

<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="coach"/>
        </jsp:include>

        <div class="container p-3 my-3 border">
            <c:if test="${not empty studenten}">
                <h2>Alle studenten:</h2>


                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>userid</th>
                            <th>Reeks</th>
                            <th>studentenCoach</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="student" items="${studenten}">
                            <tr>
                                <td><c:out value='${student.userid}'/></td>
                                <td><c:out value='${student.reeks}'/></td>
                                <form method="post" novalidate="novalidate"
                                      action="Controller?command=Confirmstudentcoach">
                                    <input type="hidden" name="userid" value="${student.userid}">
                                    <td>
                                        <select name="studentencoach" id="coach">
                                            <c:if test="${student.studentencoach eq null}">
                                                <option selected></option>
                                            </c:if>
                                            <c:if test="${not empty coachen}">

                                                <c:forEach var="coach" items="${coachen}">
                                                    <option
                                                            <c:if test="${coach.userid eq student.studentencoach}">selected</c:if>
                                                            value="${coach.userid}">${coach.naam}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                    <td><p><input class="btn btn-primary" type="submit" id="bevestig" value="Bevestig"></p></td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <caption>Studenten Overzicht</caption>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
