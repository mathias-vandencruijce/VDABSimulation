package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class OverviewReportStudents extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Date from = Date.valueOf(LocalDate.now().minusDays(7));
        Date until = Date.valueOf(LocalDate.now());

        request.setAttribute("reportWaardes", service.getStudentReportsBetweenDates(from, until));
        return "overzichtRapportStudenten.jsp";
    }
}
