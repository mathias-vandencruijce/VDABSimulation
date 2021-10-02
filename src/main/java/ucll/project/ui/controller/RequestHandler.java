package ucll.project.ui.controller;

import ucll.project.domain.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected Service service;

    public void setPersonService(Service service) {
        this.service = service;
    }

    public Service getService() { return service;}

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
