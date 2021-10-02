package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class viewStudents extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("studenten", service.getAllPeople());
        return "viewStudents.jsp";
    }
}
