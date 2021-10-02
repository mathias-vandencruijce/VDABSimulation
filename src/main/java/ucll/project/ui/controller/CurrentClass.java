package ucll.project.ui.controller;

import ucll.project.domain.model.Les;
import ucll.project.domain.model.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentClass extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Les les = (Les) request.getSession().getAttribute("CurrentClass");

        int id = les.getId();
        request.getSession().setAttribute("CurrentClass", service.getLes(id));
        request.setAttribute("deelnemers", service.getAllPeopleFromLes(id));
        request.setAttribute("afwezigen", service.getAfwezigeStudenten(les.getReeks(), String.valueOf(id)));
        return "viewClass.jsp";
    }
}
