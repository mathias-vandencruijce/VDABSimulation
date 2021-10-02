<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/sample.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Home</title>

</head>
<body>


<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="home"/>
        </jsp:include>

        <article class="container p-3 my-3 border">
            <c:if test="${error != null}">
                <p>Er werd een fout opgegooid in Servlet.</p>
                <p>Message: ${error}.</p>
                <p>Stacktrace:</p>
                <ul>
                    <c:forEach items="${stacktrace}" var="s">
                        <li>${s}</li>
                    </c:forEach>
                </ul>
            </c:if>

            <c:choose>
                <c:when test="${not empty sessionScope.loginPerson}">
                    <p id="welcome">Welkom <c:out value='${sessionScope.loginPerson.userid}'/>!</p>
                    <form novalidate="novalidate" action="Controller?command=Logout" method="post">
                        <input type="submit" class="btn btn-primary" id="Logout" value="Log uit"></p>
                    </form>

                </c:when>
                <c:otherwise>

                    <c:if test="${not empty loginerror}">
                        <div id="fout" class="alert alert-danger">
                            <ul>
                                <li><c:out value='${loginerror}'/></li>
                            </ul>
                        </div>
                    </c:if>


                    <form class="form-group" novalidate="novalidate" action="Controller?command=Login" method="post">
                        <div class="form-group mx-sm-3 mb-2">
                            <label for="userid">Userid</label>
                            <input type="text" class="form-control" id="userid" name="userid" required>
                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <label for="password">Passwoord</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <input type="submit" class="btn btn-primary" id="Login" value="Log in">
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${not empty sessionScope.deelnemerClass}">
                    <c:forEach items="${sessionScope.deelnemerClass}" var="looples">
                        <p>ID:: ${looples.id}</p>
                        <p>Lector: ${looples.lector}</p>
                        <p>Datum: ${looples.datum}</p>
                        <p>Plaats: ${looples.plaats}</p>
                        <p>Les: ${looples.vak}</p>
                        <p>Van: ${looples.vanTijd}</p>
                        <p>Tot: ${looples.totTijd}</p>
                    </c:forEach>

                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </article>
    </div>
</main>
</body>
</html>