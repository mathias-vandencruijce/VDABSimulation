package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class coach extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("studenten", service.getAllvdabStudents());
        request.setAttribute("coachen",service.getAllstudentencoachen());
        return "studentenCoach.jsp";
    }
}
