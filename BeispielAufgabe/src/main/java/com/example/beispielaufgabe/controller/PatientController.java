package com.example.beispielaufgabe.controller;

import com.example.beispielaufgabe.model.Patient;
import com.example.beispielaufgabe.request.PatientDTO;
import com.example.beispielaufgabe.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @RequestMapping("/all")
    public List<Patient> index(){
        return patientService.findAll();
    }

    @RequestMapping("/{versichertenNummer}")
    public Optional<Patient> getPatientById(@PathVariable String versichertenNummer){
        return patientService.findByVersichertenNummer(versichertenNummer);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Validated @RequestBody PatientDTO patient) {
        return ResponseEntity.ok(patientService.create(patient));
    }

    @DeleteMapping("/{versichertenNummer}")
    public ResponseEntity<Patient> deletePatient(@PathVariable String versichertenNummer){
        patientService.deleteByVersichertenNummer(versichertenNummer);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{versichertenNummer}")
    public ResponseEntity<Patient> updatePatient(@Validated @PathVariable String versichertenNummer,
                                                         @RequestBody PatientDTO patientUpdated){

        return ResponseEntity.ok(patientService.updatePatient(versichertenNummer, patientUpdated));

    }

}
