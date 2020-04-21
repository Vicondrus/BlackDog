package com.vetshop.report;

import com.vetshop.dtos.ConsultationDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Txt report.
 */
public class TxtReport implements Report {

    @Override
    public void generateReport(ConsultationDTO consultation, String path) throws IOException {

        FileWriter fileWriter = new FileWriter(path+" -  Consultation"+consultation.getConsultationId()+".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Consultation Report");
        printWriter.println(consultation.getDate().toString());
        printWriter.println("Doctor: " + consultation.getDoctorName());
        printWriter.println("Patient: " + consultation.getAnimalName());
        printWriter.println("Owner: " + consultation.getOwnerName());
        printWriter.println("Details: " + consultation.getDetails());
        printWriter.println("Diagnostic: " + consultation.getDiagnostic());
        printWriter.println("Recommendations: " + consultation.getRecommendations());
        printWriter.close();
        fileWriter.close();

    }
}
