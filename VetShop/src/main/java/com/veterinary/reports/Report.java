package com.veterinary.reports;

import com.veterinary.entities.Consultation;

public interface Report {

    void generateReport(Consultation consultation);

}
