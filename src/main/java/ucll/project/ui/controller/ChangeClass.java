package ucll.project.ui.controller;

import ucll.project.domain.model.Les;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeClass extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("oudeLes", service.getLes(id));
            request.setAttribute("deelnemers", service.getAllPeopleFromLes(id));
            request.setAttribute("afwezigen", service.getAfwezigeStudenten(service.getLes(id).getReeks(), String.valueOf(id)));
        } catch (Exception exc) {
            System.out.println("exception in startclass" + exc);
        }
        return "viewClassForDate.jsp";
    }
}
