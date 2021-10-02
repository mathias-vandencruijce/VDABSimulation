package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddParticipantOldClass extends RequestHandler{

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        int id = Integer.parseInt(request.getParameter("id"));
        service.addPersonToLes(userId, id);
        return "Controller?command=ChangeClass&id=" + id;
    }
}