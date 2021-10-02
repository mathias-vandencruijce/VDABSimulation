<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<main class="container">
    <div class="col-12">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="gengeerReport"/>
        </jsp:include>


        <div class="container p-3 my-3 border">
                
    
            <c:if test="${not empty confirmation}">
                <div  id="confirmation" class="alert alert-success">
                    <ul>
                        <li><c:out value='${confirmation}'/></li>
                    </ul>
                </div>
            </c:if>


            <c:if test="${not empty errors}">
            <div id="fout" class="alert alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li><c:out value='${error}'/></li>
                    </c:forEach>
                </ul>
            </div>
            </c:if>

    <form class="form-group" novalidate="novalidate" action="Controller?command=GenerateReport" method="post">
            <div class="form-group mx-sm-3 mb-2">
                <label for="from">Van: </label>
                <input type="date" class="form-control" id="from" name="from" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="to">Tot: </label>
                <input type="date" class="form-control" id="to" name="to" required>
            </div>
        <div class="form-group mx-sm-3 mb-2">
            <input class="btn btn-primary" type="submit" id="Generate" value="Generate">
        </div>
    </form>

</main>
</body>
</html>
