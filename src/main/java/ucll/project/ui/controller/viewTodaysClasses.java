package ucll.project.ui.controller;

import ucll.project.domain.model.Les;
import ucll.project.domain.model.Person;
import ucll.project.domain.model.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class viewTodaysClasses extends RequestHandler{

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("loginPerson");
        List<Les> lessen = service.getAllLessenFromLector(person.getUserid());

        lessen.removeIf(les -> !LocalDate.parse(les.getDatum()).isEqual(LocalDate.now()));
        lessen.sort(Comparator.comparing(Les::getId));
        request.setAttribute("lessen", lessen);

        boolean actief = false;
        for (Les les : lessen) {
            if (les.getJoinable()) {
                actief = true;
                break;
            }
        }

        request.setAttribute("actieveLes", actief);

        return "classOverview.jsp";
    }

}
