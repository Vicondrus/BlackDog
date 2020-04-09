package com.vetshop.controllers.user;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.controllers.common.InspectAnimalsController;
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

    public void scheduleConsultation(){
        JavaFXApplication.changeScene(ScheduleConsultationController.class);
    }

}
