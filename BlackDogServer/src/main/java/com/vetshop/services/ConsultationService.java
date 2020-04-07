package com.vetshop.services;


import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.RegularUserDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public interface ConsultationService {

    List<ConsultationDTO> findAll();

    ConsultationDTO save(String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date);

    ConsultationDTO update(int consultationId, String patientId, String doctorId, String diagnostic, String details, String recommendations, String hour, String minute, Date date);

    ConsultationDTO delete(int id);

    //Report reportConsultation(int id, String path, String type) throws IOException, DocumentException;

    List<ConsultationDTO> findAllForLoggedUser(RegularUserDTO regularUserDTO);

}
