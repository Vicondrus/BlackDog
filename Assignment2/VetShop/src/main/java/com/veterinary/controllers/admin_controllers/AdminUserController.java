package com.veterinary.controllers.admin_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.controllers.common_controllers.InspectAnimalsController;
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
