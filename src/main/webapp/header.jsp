<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header role="banner">
    <nav>
        <ul class="nav nav-pills nav-fill">
            <li class="nav-item">
                <a ${param.page eq 'home'?'class="nav-link active"':""} class="nav-link" href="Controller?command=Index">Hoofdpagina</a>
            </li>

            <c:if test="${sessionScope.loginPerson.role == 'student' and empty sessionScope.deelnemerClass}">
                <li class="nav-item">
                    <a ${param.page eq 'registerClass'?'class="nav-link active"':""} class="nav-link" href="Controller?command=RegisterForClassPage">Les Toetreden</a>
                </li>
            </c:if>


            <c:if test="${sessionScope.loginPerson.role eq 'lector'}">
                    <li class="nav-item">
                        <a ${param.page eq 'classOverview'?'class="nav-link active"':""} class="nav-link" href="Controller?command=viewTodaysClasses">Lessen Overzicht</a>
                    </li>
                <c:if test="${not empty sessionScope.CurrentClass}">
                    <li class="nav-item">
                        <a ${param.page eq 'currentClass'?'class="nav-link active"':""} class="nav-link" href="Controller?command=CurrentClass">Huidige Les</a>
                    </li>
                </c:if>
            </c:if>


            <c:if test="${sessionScope.loginPerson.role eq 'studentendienst'}">
                <li class="nav-item">
                    <a ${param.page eq 'viewStudents'?'class="nav-link active"':""} class="nav-link" href="Controller?command=viewStudents">Studenten overzicht</a>
                </li>

                <li class="nav-item">
                    <a ${param.page eq 'overviewReportStudents'?'class="nav-link active"':""} class="nav-link" href="Controller?command=OverviewReportStudents">Overzicht rapport studenten</a>
                </li>

                <li class="nav-item">
                    <a ${param.page eq 'coach'?'class="nav-link active"':""} class="nav-link" href="Controller?command=coach">Studentencoach</a>
                </li>

                <li class="nav-item">
                    <a ${param.page eq 'gengeerReport'?'class="nav-link active"':""} class="nav-link" href="Controller?command=ToGenerateReport">Genereer Report</a>
                </li>
            </c:if>
        </ul>
    </nav>
</header>


