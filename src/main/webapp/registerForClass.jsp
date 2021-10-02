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
            <jsp:param name="page" value="registerClass"/>
        </jsp:include>


        <div class="container p-3 my-3 border">
            <c:if test="${not empty error}">
                <div id="fout" class="alert alert-danger">
                    <p><c:out value='${error}'/></p>
                </div>
            </c:if>
            <form class="form-group" novalidate="novalidate" action="Controller?command=registerForClass" method="post">
                <div class="form-group mx-sm-3 mb-2">
                    <label for="id">Les id</label>
                    <input type="number" class="form-control" id="id" name="id" required>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                    <input type="submit" class="btn btn-primary" id="Login" value="Treed toe tot les">
                </div>
            </form>
        </div>
    </div>
</main>
</body>
</html>

