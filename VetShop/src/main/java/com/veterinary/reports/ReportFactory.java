package com.veterinary.reports;

import com.veterinary.entities.Consultation;

public class ReportFactory {

    public void generateReport(ReportType reportType, Consultation consultation){

        Report r = null;

        if(reportType.equals(ReportType.PDF)){
            r = new PdfReport();
        }else if(reportType.equals(ReportType.TXT)){
            r = new TxtReport();
        }

        r.generateReport(consultation);
    }

}
