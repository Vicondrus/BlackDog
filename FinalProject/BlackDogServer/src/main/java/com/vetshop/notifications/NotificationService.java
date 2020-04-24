package com.vetshop.notifications;

import com.vetshop.entities.Consultation;
import com.vetshop.services.exceptions.AlreadyExistingException;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

/**
 * The type Notification service.
 */
@Service
public class NotificationService {

    private final Map<String, ConcreteSubject> subjectsByTopic = new HashMap<String, ConcreteSubject>();

    private final Map<String, String> idTopicMap = new HashMap<>();

    /**
     * Add topic.
     *
     * @param session the session
     * @param topic   the topic
     * @throws AlreadyExistingException the already existing exception
     */
    public void addTopic(WebSocketSession session, String topic) throws AlreadyExistingException {
        Observer observer = new ConcreteObserver(session);
        ConcreteSubject subject = new ConcreteSubject(topic);
        subject.addObserver(observer);
        if (subjectsByTopic.containsKey(topic))
            throw new AlreadyExistingException("Topic already registered");
        subjectsByTopic.put(topic, subject);
        idTopicMap.put(session.getId(), topic);
    }

    /**
     * Remove subject and topic.
     *
     * @param topic the topic
     */
    public void removeSubjectAndTopic(String topic) {
        ConcreteSubject subject = subjectsByTopic.get(topic);
        if (subject != null)
            subject.deleteObservers();
        subjectsByTopic.remove(topic);
    }

    /**
     * Remove session.
     *
     * @param id the id
     */
    public void removeSession(String id) {
        removeSubjectAndTopic(idTopicMap.get(id));
        idTopicMap.remove(id);
    }

    /**
     * Update subject by topic.
     *
     * @param topic        the topic
     * @param consultation the consultation
     */
    public void updateSubjectByTopic(String topic, Consultation consultation) {
        if (subjectsByTopic.containsKey(topic))
            subjectsByTopic.get(topic).addConsultation(consultation);
    }

}
