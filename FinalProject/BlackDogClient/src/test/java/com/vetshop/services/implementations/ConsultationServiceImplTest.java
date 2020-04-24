package com.vetshop.services.implementations;

import com.itextpdf.text.DocumentException;
import com.vetshop.dtos.*;
import com.vetshop.exceptions.FieldException;
import com.vetshop.report.PdfReport;
import com.vetshop.report.Report;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ConsultationServiceImplTest {

    private ConsultationServiceImpl consultationService;

    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    @BeforeEach
    void setUp() {
        consultationService = new ConsultationServiceImpl();
        consultationService.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void postFindAllForLoggedUser() {
        UserDTO user = UserDTO.builder().idUser(1).build();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
        List<ConsultationDTO> list1 = new ArrayList<>();
        List<ConsultationDTO> list2 = new ArrayList<>();
        for(int i=0;i<10;i++){
            ConsultationDTO consultationDTO = ConsultationDTO.builder().consultationId(i).date(new Date()).build();
            list1.add(consultationDTO);
            list2.add(consultationDTO);
        }
        ConsultationsListWrapperDTO consultationsListWrapperDTO = ConsultationsListWrapperDTO.builder().list(list1).build();
        Mockito.doReturn(consultationsListWrapperDTO).when(restTemplate).postForObject("http://localhost:8080/getAllConsultationsForUser", user, ConsultationsListWrapperDTO.class);
        restTemplate.postForObject("http://localhost:8080/getAllConsultationsForUser", user, ConsultationsListWrapperDTO.class);
        List<ConsultationDTO> list3 = consultationService.postFindAllForLoggedUser();
        Assertions.assertIterableEquals(list3, list2);
    }

    @Test
    void reportConsultation() throws IOException, DocumentException {
        AnimalDTO a = AnimalDTO.builder().animalId(1).name("a").build();
        UserDTO r = UserDTO.builder().idUser(1).username("u").build();
        ConsultationDTO ru = ConsultationDTO.builder().consultationId(1).animal(a).date(new Date()).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();

        Report q = consultationService.reportConsultation(ru,"lol","PDF");
        Report q1 = new PdfReport();

        Assertions.assertTrue(new ReflectionEquals(q1).matches(q));
    }

    @Test
    void postCreateConsultation() throws FieldException {
        AnimalDTO a = AnimalDTO.builder().animalId(1).name("a").build();
        UserDTO r = UserDTO.builder().idUser(1).username("u").build();
        ConsultationDTO expected = ConsultationDTO.builder().consultationId(1).status(StatusDTO.DONE).animal(a).date(new Date()).details("details").diagnostic("diagnostic").doctor(r).recommendations("recommendation").build();

        Mockito.doReturn(expected).when(restTemplate).postForObject(Mockito.eq("http://localhost:8080/createConsultation"), Mockito.any(ConsultationDTO.class), Mockito.eq(ConsultationDTO.class));

        ConsultationDTO returned = consultationService.postCreateConsultation(a,r,"diagnostic","details","recommendations","11","12",new Date(), StatusDTO.DONE);

        Assertions.assertTrue(new ReflectionEquals(returned, "date", "consultationId").matches(expected));
    }

    @Test
    void postScheduleConsultation() throws FieldException {
        AnimalDTO a = AnimalDTO.builder().animalId(1).name("a").build();
        UserDTO r = UserDTO.builder().idUser(1).username("u").build();
        ConsultationDTO expected = ConsultationDTO.builder().consultationId(1).status(StatusDTO.DONE).animal(a).date(new Date()).details("-").diagnostic("-").doctor(r).recommendations("-").build();

        Mockito.doReturn(expected).when(restTemplate).postForObject(Mockito.eq("http://localhost:8080/scheduleConsultation"), Mockito.any(ConsultationDTO.class), Mockito.eq(ConsultationDTO.class));

        ConsultationDTO returned = consultationService.postScheduleConsultation(a,r,"11","12",new Date(new Date().getTime() + 100000000), null);

        Assertions.assertTrue(new ReflectionEquals(returned, "date", "consultationId").matches(expected));
    }

    @Test
    void postUpdateConsultation() throws FieldException {
        AnimalDTO a = AnimalDTO.builder().animalId(1).name("a").build();
        UserDTO r = UserDTO.builder().idUser(1).username("u").build();
        ConsultationDTO expected = ConsultationDTO.builder().consultationId(1).status(StatusDTO.DONE).animal(a).date(new Date()).details("-").diagnostic("-").doctor(r).recommendations("-").build();

        Mockito.doReturn(expected).when(restTemplate).postForObject(Mockito.eq("http://localhost:8080/updateConsultation"), Mockito.any(ConsultationDTO.class), Mockito.eq(ConsultationDTO.class));

        ConsultationDTO returned = consultationService.postUpdateConsultation(1, a,r, "diagnostic", "details", "recommendations", "11","12",new Date(new Date().getTime() + 100000000), StatusDTO.DONE);

        Assertions.assertTrue(new ReflectionEquals(returned, "date", "consultationId").matches(expected));
    }

    @Test
    void deleteConsultation() {
        AnimalDTO a = AnimalDTO.builder().animalId(1).name("a").build();
        UserDTO r = UserDTO.builder().idUser(1).username("u").build();
        ConsultationDTO expected = ConsultationDTO.builder().consultationId(1).status(StatusDTO.DONE).animal(a).date(new Date()).details("-").diagnostic("-").doctor(r).recommendations("-").build();

        Mockito.doReturn(expected).when(restTemplate).postForObject(Mockito.eq("http://localhost:8080/deleteConsultation"), Mockito.any(ConsultationDTO.class), Mockito.eq(ConsultationDTO.class));

        ConsultationDTO returned = consultationService.deleteConsultation(expected);

        Assertions.assertTrue(new ReflectionEquals(returned, "date", "consultationId").matches(expected));
    }
}