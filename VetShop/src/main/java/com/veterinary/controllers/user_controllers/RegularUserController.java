package com.veterinary.controllers.user_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.controllers.common_controllers.InspectAnimalsController;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("regularuser-stage.fxml")
public class RegularUserController implements Controller {

    public void getConsultationsWindow(){
        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }

    public void getAnimalsWindow(){
        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }


}
