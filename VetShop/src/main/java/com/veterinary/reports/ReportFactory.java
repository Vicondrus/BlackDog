package com.veterinary.reports;

import com.itextpdf.text.DocumentException;
import com.veterinary.entities.Consultation;

import java.io.IOException;

public class ReportFactory {

    public void generateReport(ReportType reportType, Consultation consultation, String path) throws IOException, DocumentException {

        Report r = null;

        if(reportType.equals(ReportType.PDF)){
            r = new PdfReport();
        }else if(reportType.equals(ReportType.TXT)){
            r = new TxtReport();
        }

        r.generateReport(consultation, path);
    }

}
