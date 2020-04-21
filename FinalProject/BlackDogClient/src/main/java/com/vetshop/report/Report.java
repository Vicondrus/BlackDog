package com.vetshop.report;

import com.itextpdf.text.DocumentException;
import com.vetshop.dtos.ConsultationDTO;

import java.io.IOException;

/**
 * The interface Report.
 */
public interface Report {

    /**
     * Generate report.
     *
     * @param consultation the consultation
     * @param path         the path
     * @throws IOException       the io exception
     * @throws DocumentException the document exception
     */
    void generateReport(ConsultationDTO consultation, String path) throws IOException, DocumentException;

}
