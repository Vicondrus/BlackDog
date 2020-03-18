package com.veterinary.controllers;

import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.DTO;

public class UpdateConsultationController implements DTOController{

    private ConsultationDTO consultationDTO;

    @Override
    public void setDTO(DTO dto) {
        consultationDTO = (ConsultationDTO) dto;
    }
}
