package com.veterinary.controllers.user_controllers;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.services.ConsultationService;
import de.saxsys.javafx.test.JfxRunner;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RunWith(JfxRunner.class)
public class InspectConsultationsControllerTest {

    @Mock
    private TableColumn<ConsultationDTO, Date> date;

    @Mock
    private TableColumn<ConsultationDTO, String> animal;

    @Mock
    private TableColumn<ConsultationDTO, String> diagnostic;

    @Mock
    private TableColumn<ConsultationDTO, String> recommendations;

    @Mock
    private TableColumn<ConsultationDTO, String> details;

    @Mock
    private ConsultationService consultationService;

    @Mock
    private SelectionModel<ConsultationDTO> selectionModel;

    @Mock
    private TableView<ConsultationDTO> table;

    @InjectMocks
    private InspectConsultationsController inspectConsultationsController;

    private List<ConsultationDTO> consultationDTOList;

    private boolean mockInitialized = false;
    @Before
    public void before() {
        if (!mockInitialized) {
            MockitoAnnotations.initMocks(this);
            mockInitialized = true;
        }

        consultationDTOList = getListOfDTOs();
    }

    private List<ConsultationDTO> getListOfDTOs(){
        List<ConsultationDTO> consultationDTOList = new ArrayList<>();
        for(int i = 0; i<10;i++){
            ConsultationDTO cons = ConsultationDTO.builder().animal(AnimalDTO.builder().owner(i+"").name(i+"").build()).date(new Date()).details(i+"").doctor(RegularUserDTO.builder().username(i+"").build()).diagnostic(i+"").consultationId(i).recommendations(i+"").build();
            consultationDTOList.add(cons);
        }
        return consultationDTOList;
    }


    @Test
    public void updateTest(){

        Mockito.when(table.getSelectionModel().getSelectedItem()).thenReturn(consultationDTOList.get(new Random().nextInt(consultationDTOList.size())));
        inspectConsultationsController.update();
    }

}
