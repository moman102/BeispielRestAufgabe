package com.example.beispielaufgabe.request;


import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class PatientDTO {

        @GeneratedValue
        private Long ID;

        @Size(min = 10, max = 10)
        @Column(unique = true)
        private String versichertenNummer;

        @NotEmpty(message = "Please enter name")
        private String name;

        @NotEmpty(message = "Please enter vorname")
        private String vorname;

        @NotEmpty(message = "Please enter kassen name")
        private String kassenName;

        @NotEmpty(message = "Please enter IK")
        private String institutionsKennzeichen;

        @Future
        private Date ablaufdatum;

    }

