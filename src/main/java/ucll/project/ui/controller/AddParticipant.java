package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddParticipant extends RequestHandler{

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        int lesId = Integer.parseInt(request.getParameter("lesId"));
        service.addPersonToLes(id, lesId);
        return "Controller?command=CurrentClass";
    }
}