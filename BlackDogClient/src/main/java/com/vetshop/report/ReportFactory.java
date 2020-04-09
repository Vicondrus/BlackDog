package com.vetshop.report;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class ReportFactory {

    public Report generateReport(ReportType reportType) throws IOException, DocumentException {

        Report r = null;

        if(reportType.equals(ReportType.PDF)){
            r = new PdfReport();
        }else if(reportType.equals(ReportType.TXT)){
            r = new TxtReport();
        }

        return r;

    }

}
