<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yarne
  Date: 15/12/2020
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Overzicht rapport studenten</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="overviewReportStudents"/>
        </jsp:include>
        <div class="container p-3 my-3 border">

            <form method="POST" action="Controller?command=FilterVDABOStudentOverzicht" novalidate="novalidate">
                <div class="form-row align-items-center">
                    <div class="col-sm-3 my-1">
                        <label for="dateFrom">From</label>
                        <input type="date" id="dateFrom" name="dateFrom" required
                               value="${fromDatePreviousValue}" class="form-control">
                    </div>
                    <div class="col-sm-3 my-1">
                        <label for="dateUntil">Until</label>
                        <div class="input-group">
                            <input type="date" class="form-control" type="date" id="dateUntil" name="dateUntil" required
                                   value="${untilDatePreviousValue}">
                        </div>
                    </div>
                    <div class="col-sm-3 my-1">
                        <br>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
                <a href="Controller?command=OverviewReportStudents">clear filter</a>
            </form>

            <c:choose>
                <c:when test="${not empty reportWaardes}">

                    <h2>Vdab studenten</h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>User id</th>
                                <th>Reeks</th>
                                <th>Uren aanwezig</th>
                                <th>Totale uren</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="vdabStudent" items="${reportWaardes}">
                                <tr>
                                    <td><c:out value='${vdabStudent.userId}'/></td>
                                    <td><c:out value="${vdabStudent.reeks}"/></td>
                                    <td><c:out value='${vdabStudent.aanwezig}'/></td>
                                    <td><c:out value='${vdabStudent.totaal}'/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Er zijn geen vdab studenten</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
</body>
</html>
