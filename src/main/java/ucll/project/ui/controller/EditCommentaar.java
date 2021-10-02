package ucll.project.ui.controller;

import ucll.project.domain.model.Les;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCommentaar extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String commentaar = request.getParameter("commentaar");
        String userid = request.getParameter("studentNr");
        String destination = request.getParameter("destination");

        String id = request.getParameter("idLes");


        service.changeCommentaar(userid, id, commentaar);
        return "Controller?command="+destination+"&id="+id;
    }
}
