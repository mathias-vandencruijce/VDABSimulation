package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class startClass extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int id = -1;
        try {
             id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception exc) {
            System.out.println("exception in startclass" + exc);
        }
        request.getSession().setAttribute("CurrentClass", service.getLes(id));
        request.setAttribute("deelnemers", service.getAllPeopleFromLes(id));
        request.setAttribute("afwezigen", service.getAfwezigeStudenten(service.getLes(id).getReeks(), String.valueOf(id)));

        service.changeJoinableState(true, Integer.toString(id));      //maak de les joinable
        return "viewClass.jsp";
    }
}