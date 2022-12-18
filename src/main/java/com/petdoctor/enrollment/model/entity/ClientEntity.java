package com.petdoctor.enrollment.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "pet_problem", nullable = false)
    private String petProblem;

    @Column(name = "vet_clinic_entity_id")
    private Long vetClinicEntityId;
}
