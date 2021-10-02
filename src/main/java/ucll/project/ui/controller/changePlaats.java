package ucll.project.ui.controller;

import ucll.project.domain.model.Les;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class changePlaats extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String plaats = request.getParameter("plaats");
        int id = Integer.parseInt(request.getParameter("id"));

        if (plaats.equals("online")) {
            service.changePlaats("fysiek", id);
        } else {
            service.changePlaats("online", id);
        }

        return "Controller?command=viewTodaysClasses";
    }
}
