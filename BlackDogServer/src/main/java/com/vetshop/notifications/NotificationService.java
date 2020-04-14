package com.vetshop.notifications;

import com.vetshop.entities.Consultation;
import com.vetshop.services.exceptions.AlreadyExistingException;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

@Service
public class NotificationService {

    private Map<String, ConcreteSubject> subjectsByTopic = new HashMap<String, ConcreteSubject>();

    private Map<String, String> idTopicMap = new HashMap<>();

    public void addTopic(WebSocketSession session, String topic) throws AlreadyExistingException {
        Observer observer = new ConcreteObserver(session);
        ConcreteSubject subject = new ConcreteSubject(topic);
        subject.addObserver(observer);
        if(subjectsByTopic.containsKey(topic))
            throw new AlreadyExistingException("Topic already registered");
        subjectsByTopic.put(topic, subject);
        idTopicMap.put(session.getId(),topic);
    }

    public void removeSubjectAndTopic(String topic){
        ConcreteSubject subject = subjectsByTopic.get(topic);
        if(subject != null)
            subject.deleteObservers();
        subjectsByTopic.remove(topic);
    }

    public void removeSession(String id){
        removeSubjectAndTopic(idTopicMap.get(id));
        idTopicMap.remove(id);
    }

    public void updateSubjectByTopic(String topic, Consultation consultation){
        subjectsByTopic.get(topic).addConsultation(consultation);
    }

}