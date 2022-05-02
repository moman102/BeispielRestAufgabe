package com.example.beispielaufgabe.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique=true)
    private String versichertenNummer;

    @Column
    private String name;

    @Column
    private String vorname;

    @Column
    private String institutionsKennzeichen;

    @Column
    private String kassenName;

    @Column
    private Date ablaufDatum;

}
