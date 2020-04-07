package com.vetshop.controllers;


import com.vetshop.application.JavaFXApplication;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("adminuser-stage.fxml")
public class AdminUserController implements Controller {

    public void getUsersWindow(){
        JavaFXApplication.changeScene(InspectUsersController.class);
    }

    public void getAnimalsWindow(){
        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }

    public void getConsultationsWindow(){
        JavaFXApplication.changeScene(InspectConsultationsAdminController.class);
    }

}
