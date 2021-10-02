<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Les toetreden</title>
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


            <c:if test="${not empty error}">
                <div id="fout" class="alert alert-danger">
                    <p><c:out value='${error}'/></p>
                </div>
            </c:if>

            <p>Edit commentaar voor student met studentNr: ${studentNr}</p>

            <form novalidate="novalidate" method="post" action="Controller?command=EditCommentaar">
                <p><label for="commentaar">Commentaar: </label><input class="form-control" type="text" id="commentaar"
                                                                      name="commentaar"
                                                                      required value="${commentaar}"/></p>
                <input value="${studentNr}" type="hidden" name="studentNr">
                <input value="${idLes}" type="hidden" name="idLes">
                <input value="${destination}" type="hidden" name="destination">

                <p><input class="btn btn-primary" type="submit" id="update" value="Commentaar updaten"></p>

            </form>
        </div>
    </div>
</main>
</body>
</html>

