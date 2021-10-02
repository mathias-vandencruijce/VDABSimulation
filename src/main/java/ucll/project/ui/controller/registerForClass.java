package ucll.project.ui.controller;

import ucll.project.domain.model.Les;
import ucll.project.domain.model.Person;
import ucll.project.domain.model.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class registerForClass extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int id;
        Student student = (Student) request.getSession().getAttribute("loginPerson");
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception exc) {
            request.setAttribute("error", "Geef een valid nummer op.");
            return "registerForClass.jsp";
        }

        if (!service.getLesIds().contains(id)) {
            request.setAttribute("error", "Het opgegeven ID werd niet terug gevonden.");
            return "registerForClass.jsp";
        } else {
            Les les = service.getLes(id);
            if (!LocalDate.parse(les.getDatum()).isEqual(LocalDate.now()) || !les.getJoinable()) {
                request.setAttribute("error", "Je kan nog niet tot deze les toetreden.");
                return "registerForClass.jsp";
            } else {
                boolean found = false;
                for (Person person : service.getAllPeopleFromLes(les.getId())) {
                    if (person.getUserid().equals(student.getUserid())) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    request.setAttribute("error", "Je bent al tot deze les toegetreden.");
                    return "registerForClass.jsp";
                } else {
                    request.getSession().setAttribute("deelnemerClass", service.getAllActiveLessenFromStudent(student.getUserid()));
                    service.addPersonToLes(student.getUserid(), id);
                    return "Controller?command=Index";
                }
            }
        }
    }
}
