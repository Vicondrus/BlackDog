package com.vetshop.repositories;

import com.vetshop.entities.Consultation;
import com.vetshop.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    List<Consultation> findByDoctorUsername(String username);

}
