package ucll.project.ui.controller;

import ucll.project.domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("loginPerson");

        if (person != null && person.getRole().equals("student")) {
            request.getSession().setAttribute("deelnemerClass", service.getAllActiveLessenFromStudent(person.getUserid()));
        }
        return "index.jsp";
    }
}
