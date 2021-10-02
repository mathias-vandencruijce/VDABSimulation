package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class removerParticipantOldClass extends RequestHandler{

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        int lesId = Integer.parseInt(request.getParameter("lesId"));
        service.removeParticipantFromClass(id, lesId);
        return "Controller?command=ChangeClass&id=" + lesId;
    }
}