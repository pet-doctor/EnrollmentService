package com.petdoctor.enrollment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("pet_name")
    private String petName;

    @JsonProperty("pet_problem")
    private String petProblem;

    @JsonProperty("vet_clinic_id")
    private Long vetClinicId;
}
