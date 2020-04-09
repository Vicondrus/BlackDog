package com.vetshop.report;

import com.itextpdf.text.DocumentException;
import com.vetshop.dtos.ConsultationDTO;

import java.io.IOException;

public interface Report {

    void generateReport(ConsultationDTO consultation, String path) throws IOException, DocumentException;

}
