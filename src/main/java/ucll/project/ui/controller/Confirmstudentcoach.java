package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Confirmstudentcoach extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid =request.getParameter("userid");
        String coachid =request.getParameter("studentencoach");
        service.setstudentencoach(userid,coachid);
        request.setAttribute("studenten", service.getAllvdabStudents());
        request.setAttribute("coachen",service.getAllstudentencoachen());
        return "studentenCoach.jsp";
    }
}
