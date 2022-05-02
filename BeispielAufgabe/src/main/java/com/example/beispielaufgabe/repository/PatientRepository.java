package com.example.beispielaufgabe.repository;

import com.example.beispielaufgabe.model.Patient;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAll();

    Optional<Patient> findByVersichertenNummer(String versichertenNummer);


}
