package ucll.project.ui.controller;

import ucll.project.domain.model.VdabStudent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class FilterVDABOStudentOverzicht extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String sFrom = request.getParameter("dateFrom");
        String sUntil = request.getParameter("dateUntil");

        request.setAttribute("fromDatePreviousValue", sFrom);
        request.setAttribute("untilDatePreviousValue", sUntil);

        ArrayList<String> errors = new ArrayList<>();

        if ((sFrom.isEmpty() || sUntil.isEmpty()) || (sFrom.isEmpty() && sUntil.isEmpty())) {
            errors.add("Vul alle velden in aub");
            request.getSession().setAttribute("errors", errors);
            return "Controller?command=OverviewReportStudents";
        }


        Date from = Date.valueOf(sFrom);
        Date until = Date.valueOf(sUntil);

        if (!from.toLocalDate().isBefore(until.toLocalDate())) {
            errors.add("Begin datum moet voor eind datum");
            request.getSession().setAttribute("errors", errors);
            return "Controller?command=OverviewReportStudents";
        }

        request.setAttribute ("reportWaardes", service.getStudentReportsBetweenDates(from, until));
        return "overzichtRapportStudenten.jsp";
    }
}
