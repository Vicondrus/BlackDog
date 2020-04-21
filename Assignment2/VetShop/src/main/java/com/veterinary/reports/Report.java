package com.veterinary.reports;

import com.itextpdf.text.DocumentException;
import com.veterinary.entities.Consultation;

import java.io.IOException;

public interface Report {

    void generateReport(Consultation consultation, String path) throws IOException, DocumentException;

}
