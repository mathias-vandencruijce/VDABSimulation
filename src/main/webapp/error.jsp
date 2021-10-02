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
    <title>Something went wrong</title>

</head>
<body>


<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="error"/>
        </jsp:include>

        <article class="container p-3 my-3 border">

            <c:if test="${error != null}">
                <p>Er is iets verkeerd gegaan.</p>
                <p>Je hebt een ${error} op de server veroorzaakt.</p>
                <p>Ga naar <a href="index.jsp">hoofdpagina</a>.</p>
            </c:if>


        </article>
    </div>
</main>
</body>
</html>