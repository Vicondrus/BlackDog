package com.vetshop.report;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

/**
 * The type Report factory.
 */
public class ReportFactory {

    /**
     * Generate report report.
     *
     * @param reportType the report type
     * @return the report
     * @throws IOException       the io exception
     * @throws DocumentException the document exception
     */
    public Report generateReport(ReportType reportType) throws IOException, DocumentException {

        Report r = null;

        if (reportType.equals(ReportType.PDF)) {
            r = new PdfReport();
        } else if (reportType.equals(ReportType.TXT)) {
            r = new TxtReport();
        }

        return r;

    }

}
