package ucll.project.ui.controller;

import ucll.project.domain.model.Person;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        try {
            Person person = service.getPerson(userid);
            if (person != null && service.isCorrectPassword(userid, password)) {
                request.getSession().setAttribute("loginPerson", person);
                if (person.getRole().equals("student")) {
                    request.getSession().setAttribute("deelnemerClass", service.getAllActiveLessenFromStudent(userid));
                }
                return "Controller?command=Index";
            } else {
                return "index.jsp";
            }
        } catch (Exception exc) {
            request.setAttribute("loginerror", "No valid userid/password");
            return "index.jsp";
        }
    }
}
