package com.veterinary.controllers;

import com.veterinary.application.JavaFXApplication;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("regularuser-stage.fxml")
public class RegularUserController implements Controller{

    public void getConsultationsWindow(){
        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }

    public void getAnimalsWindow(){
        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }


}
