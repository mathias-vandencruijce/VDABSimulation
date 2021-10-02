package ucll.project.ui.controller;

import okhttp3.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CloseClass extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        service.changeJoinableState(false, request.getParameter("lesId"));
        request.getSession().removeAttribute("CurrentClass");
        return "Controller?command=viewTodaysClasses";
    }
}
