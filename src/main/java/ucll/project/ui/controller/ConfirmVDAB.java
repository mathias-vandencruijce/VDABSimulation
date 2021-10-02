package ucll.project.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmVDAB extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String[] checkedIds = request.getParameterValues("vdab");

        service.updateVDAB(checkedIds);

        return "Controller?command=viewStudents";
    }
}
