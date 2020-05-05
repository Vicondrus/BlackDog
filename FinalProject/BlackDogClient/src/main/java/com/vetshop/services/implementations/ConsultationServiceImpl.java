package com.vetshop.services.implementations;

import com.itextpdf.text.DocumentException;
import com.vetshop.dtos.*;
import com.vetshop.exceptions.FieldException;
import com.vetshop.exceptions.OutOfStockException;
import com.vetshop.report.Report;
import com.vetshop.report.ReportFactory;
import com.vetshop.report.ReportType;
import com.vetshop.services.ConsultationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The type Consultation service.
 */
@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final String uriRoot;
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Instantiates a new Consultation service.
     *
     * @param hostAndPort the host and port
     */
    public ConsultationServiceImpl(@Value("${server.host-and-port}") String hostAndPort) {
        this.uriRoot = "http://" + hostAndPort;
    }

    /**
     * Sets rest template.
     *
     * @param restTemplate the rest template
     */
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ConsultationDTO> postFindAllForLoggedUser() {
        final String uri = uriRoot + "/getAllConsultationsForUser";

        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ConsultationsListWrapperDTO consultations = restTemplate.postForObject(uri, principal, ConsultationsListWrapperDTO.class);

        return consultations.getList();
    }

    @Override
    public Report reportConsultation(ConsultationDTO cons, String path, String type) throws IOException, DocumentException {
        ReportFactory rf = new ReportFactory();
        Report report = rf.generateReport(ReportType.valueOf(type));
        report.generateReport(cons, path);
        return report;
    }

    @Override
    public ConsultationDTO postCreateConsultation(AnimalDTO patient, UserDTO doctor, String diagnostic, String details, String recommendations, String hour, String minute, Date date, StatusDTO status) throws FieldException {
        final String uri = uriRoot + "/createConsultation";

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        if (hour.equals("") || minute.equals(""))
            throw new FieldException("Hours and minutes cannot be empty");
        if (Integer.parseInt(hour) >= 24 || Integer.parseInt(hour) < 0)
            throw new FieldException("Hours must be between 00 and 23");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        if (Integer.parseInt(minute) >= 60 || Integer.parseInt(minute) < 0)
            throw new FieldException("Minutes must be between 00 and 59");
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        Date correctDate = new Date(time);

        ConsultationDTO consultation = ConsultationDTO.builder().status(status).animal(patient).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(correctDate).build();

        consultation = restTemplate.postForObject(uri, consultation, ConsultationDTO.class);

        return consultation;
    }

    @Override
    public ConsultationDTO postScheduleConsultation(AnimalDTO patinet, UserDTO doctor, String hour, String minute, Date date, List<ItemDTO> neededItems) throws FieldException {
        final String uri = uriRoot + "/scheduleConsultation";

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        if (hour.equals("") || minute.equals(""))
            throw new FieldException("Hours and minutes cannot be empty");
        if (Integer.parseInt(hour) >= 24 || Integer.parseInt(hour) < 0)
            throw new FieldException("Hours must be between 00 and 23");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        if (Integer.parseInt(minute) >= 60 || Integer.parseInt(minute) < 0)
            throw new FieldException("Minutes must be between 00 and 59");
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        Date correctDate = new Date(time);
        if (correctDate.getTime() <= new Date().getTime()) {
            throw new FieldException("Scheduled consultations must be after this moment");
        }

        ConsultationDTO consultation = ConsultationDTO.builder().status(StatusDTO.SCHEDULED).items(neededItems).animal(patinet).details("-").diagnostic("-").doctor(doctor).recommendations("-").date(correctDate).build();

        consultation = restTemplate.postForObject(uri, consultation, ConsultationDTO.class);

        return consultation;
    }

    @Override
    public ConsultationDTO postUpdateConsultation(int id, AnimalDTO patinet, UserDTO doctor, String diagnostic, String details, String recommendations, String hour, String minute, Date date, StatusDTO status) throws FieldException {
        final String uri = uriRoot + "/updateConsultation";

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        if (hour.equals("") || minute.equals(""))
            throw new FieldException("Hours and minutes cannot be empty");
        if (Integer.parseInt(hour) >= 24 || Integer.parseInt(hour) < 0)
            throw new FieldException("Hours must be between 00 and 23");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        if (Integer.parseInt(minute) >= 60 || Integer.parseInt(minute) < 0)
            throw new FieldException("Minutes must be between 00 and 59");
        cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        Date correctDate = new Date(time);
        ConsultationDTO consultation = ConsultationDTO.builder().status(status).consultationId(id).animal(patinet).details(details).diagnostic(diagnostic).doctor(doctor).recommendations(recommendations).date(correctDate).build();

        consultation = restTemplate.postForObject(uri, consultation, ConsultationDTO.class);

        return consultation;
    }

    @Override
    public ConsultationDTO deleteConsultation(ConsultationDTO consultation) {
        final String uri = uriRoot + "/deleteConsultation";

        consultation = restTemplate.postForObject(uri, consultation, ConsultationDTO.class);
        return consultation;
    }

    @Override
    public ConsultationDTO postBeginConsultation(int consultationId) throws OutOfStockException {
        final String uri = uriRoot + "/beginConsultation";

        ConsultationDTO consultation = restTemplate.postForObject(uri, consultationId, ConsultationDTO.class);
        if (consultation == null) {
            throw new OutOfStockException("Necessary gear out of stock");
        }
        return consultation;
    }

    @Override
    public ConsultationDTO postCompleteConsultation(ConsultationDTO consultation, String diagnostic, String details, String recommendations) {
        final String uri = uriRoot + "/completeConsultation";

        consultation.setDetails(details);
        consultation.setDiagnostic(diagnostic);
        consultation.setRecommendations(recommendations);

        consultation = restTemplate.postForObject(uri, consultation, ConsultationDTO.class);

        return consultation;
    }


}
