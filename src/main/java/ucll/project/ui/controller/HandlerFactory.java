package ucll.project.ui.controller;

import ucll.project.domain.service.Service;

public class HandlerFactory {

    public HandlerFactory(){

    }

    public RequestHandler getHandler(String command, Service service) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ucll.project.ui.controller."+ command);

            Object handlerObject = handlerClass.getConstructor().newInstance();

            handler = (RequestHandler) handlerObject;
            handler.setPersonService(service);
        } catch (ClassNotFoundException e){
            throw new ControllerException(e.getMessage());
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        return handler;
    }
}
