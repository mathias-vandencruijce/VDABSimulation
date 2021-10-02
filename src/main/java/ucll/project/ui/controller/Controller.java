package ucll.project.ui.controller;

import ucll.project.domain.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private HandlerFactory handlerFactory;
    private Service personservice;


    @Override
    public void init() throws ServletException {
        super.init();
        handlerFactory = new HandlerFactory();
        personservice = new Service();

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // checks connection with database and reconnects when necessary
            // with singleton-pattern: application is connected to database when initiated (cfr util.AppContextListener)
            // when the connection with the database has been idle for some time, the database itself disconnects the application.
            // therefore the application must reconnect to the database
            if (personservice.getConnection().isClosed()) {
                System.out.println("connection has been closed");
                personservice.reConnect();
            }

            if (request.getSession().getAttribute("errors") != null) {
                request.setAttribute("errors", request.getSession().getAttribute("errors"));
                request.getSession().setAttribute("errors", null);
            }

            String command = request.getParameter("command");
            if (command == null || command.trim().isEmpty()) {
                command = "Index";
            }
            RequestHandler handler = handlerFactory.getHandler(command, personservice);
            String destination = handler.handleRequest(request, response);

            if (destination.contains(".jsp")) {
                request.getRequestDispatcher(destination).forward(request,response);
            } else {
                response.sendRedirect(destination);
            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

