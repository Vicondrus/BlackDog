package com.vetshop.notifications;

import com.vetshop.entities.Consultation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Getter
@Setter
public class ConcreteSubject extends Observable {

    private String topic;

    private List<Consultation> newlyAddedConsultations = new ArrayList<>();

    public ConcreteSubject(String topic){
        this.topic = topic;
    }

    public void addConsultation(Consultation consultation){
        newlyAddedConsultations.add(consultation);
        this.setChanged();
        this.notifyObservers("Consultation added for " + consultation.getDate());
    }

}
