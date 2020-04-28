package com.vetshop.controllers.user;


import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.DTO;
import com.vetshop.services.ConsultationService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * The type Complete consultation controller.
 */
@Component
@FxmlView("completeconsultation-stage.fxml")
public class CompleteConsultationController implements DTOController {

    private final ConsultationService consultationService;

    private ConsultationDTO consultationDTO;

    @FXML
    private TextField details;

    @FXML
    private TextField diagnostic;

    @FXML
    private TextField recommendations;

    /**
     * Instantiates a new Complete consultation controller.
     *
     * @param consultationService the consultation service
     */
    public CompleteConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @Override
    public void setDTO(DTO dto) {
        consultationDTO = (ConsultationDTO) dto;
    }

    /**
     * Complete.
     */
    public void complete() {
        consultationService.postCompleteConsultation(consultationDTO, diagnostic.getText(), details.getText(), recommendations.getText());

        Stage stage = (Stage) details.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) details.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }
}
