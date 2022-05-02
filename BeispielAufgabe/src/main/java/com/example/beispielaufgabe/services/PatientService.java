package com.example.beispielaufgabe.services;

import com.example.beispielaufgabe.model.Patient;
import com.example.beispielaufgabe.repository.PatientRepository;
import com.example.beispielaufgabe.request.PatientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<Patient> findAll() {
        return repo.findAll();
    }

    public Optional<Patient> findByVersichertenNummer(String versichertenNummer) {
        return repo.findByVersichertenNummer(versichertenNummer);
    }

    public Patient create(PatientDTO request) {

        if(checkPrufSumme(request.getVersichertenNummer())==true) {

            Patient patient = new Patient();
            patient.setName(request.getName());
            patient.setVorname(request.getVorname());
            patient.setKassenName(request.getKassenName());
            patient.setInstitutionsKennzeichen(request.getInstitutionsKennzeichen());
            patient.setVersichertenNummer(request.getVersichertenNummer());
            patient.setAblaufDatum(request.getAblaufdatum());

            return repo.save(patient);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ungultige versicherten nummer");
        }

    }

    private boolean checkPrufSumme(String versichertenNummer) {

        int sum = 0;
        int[] numbers = new int[versichertenNummer.length()];
        for(int i = 3; i < versichertenNummer.length() - 1 ; i++) {
            numbers[i] = versichertenNummer.charAt(i) - '0';
            if(i % 2 == 1) {
                numbers[i] =  numbers[i] * 2 ;
                if( numbers[i] > 9) {
                    numbers[i] -= 10;
                    sum += numbers[i] + 1;
                }else{
                    sum += numbers[i];
                }
            }
            if(i % 2 == 0) {
                numbers[i] =  numbers[i] * 1 ;
                if (numbers[i] > 9) {
                    numbers[i] -= 10;
                    sum += numbers[i] + 1;
                } else {
                    sum += numbers[i];
                }
            }
        }
        int prufZiffer = sum % 10 ;
        int angegenbenPrufZiffer = versichertenNummer.charAt(9) - '0';;

        if(prufZiffer == angegenbenPrufZiffer) {
            return true;
        }else {
            return false;
        }
    }

    public void deleteByVersichertenNummer(String versichertenNummer) {
        Long id = repo.findByVersichertenNummer(versichertenNummer).get().getID();
        repo.deleteById(id);
    }

    public Patient updatePatient(String versichertenNummer, PatientDTO patientUpdated) {

       Patient patient = repo.findByVersichertenNummer(versichertenNummer).get();

        patient.setName(patientUpdated.getName());
        patient.setVorname(patientUpdated.getVorname());
        patient.setAblaufDatum(patientUpdated.getAblaufdatum());

        return repo.save(patient);

    }

}
