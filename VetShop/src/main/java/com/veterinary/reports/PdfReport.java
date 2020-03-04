package com.veterinary.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.veterinary.entities.Consultation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfReport implements Report{

    @Override
    public void generateReport(Consultation consultation, String path) throws FileNotFoundException, DocumentException {

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(path+"\\Consultation"+consultation.getConsultationId()+".pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        document.addTitle("Consultation Report");

        List<Phrase> phrases= new ArrayList<Phrase>();

        phrases.add(new Phrase(consultation.getDate().toString(), font));

        phrases.add(new Phrase("Doctor: " + consultation.getDoctorName(), font));

        phrases.add(new Phrase("Patient: " + consultation.getAnimalName(), font));

        phrases.add(new Phrase("Owner: " + consultation.getOwnerName(), font));

        phrases.add(new Phrase("Details: " + consultation.getDetails(), font));

        phrases.add(new Phrase("Diagnostic: " + consultation.getDiagnostic(), font));

        phrases.add(new Phrase("Recommendations: " + consultation.getRecommendations(), font));

        phrases.forEach(x -> {
            try {
                document.add(x);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });

        document.close();

    }
}
