package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToEditCommentaar extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("studentNr");
        request.setAttribute("studentNr", userid);

        String idLes = request.getParameter("idLes");
        request.setAttribute("idLes", idLes);

        String destination = request.getParameter("destination");
        request.setAttribute("destination", destination);

        request.setAttribute("commentaar", service.getCommentaarFromUser(userid, idLes));

        return "editCommentaar.jsp";
    }
}
