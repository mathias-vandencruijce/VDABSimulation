package ucll.project.ui.controller;

import ucll.project.domain.model.Les;
import ucll.project.domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class viewClassesForDate extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("loginPerson");
        List<Les> lessen = service.getAllLessenFromLector(person.getUserid());
        String dateStr = request.getParameter("date");
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
            if (date.isAfter(LocalDate.now())) throw new IllegalArgumentException();
        } catch (Exception exception) {
            request.setAttribute("error", "Geef valid date.");
            lessen.removeIf(les -> !LocalDate.parse(les.getDatum()).isEqual(LocalDate.now()));
            lessen.sort(Comparator.comparing(Les::getId));
            request.setAttribute("lessen", lessen);
            return "classOverview.jsp";
        }

        lessen.removeIf(les -> !LocalDate.parse(les.getDatum()).isEqual(date));
        lessen.sort(Comparator.comparing(Les::getId));
        request.setAttribute("lessen", lessen);
        request.setAttribute("date", date.toString());

        return "classOverviewForDate.jsp";
    }
}
