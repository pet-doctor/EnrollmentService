package com.petdoctor.enrollment.tool.mapper;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "petName", target = "petName"),
            @Mapping(source = "petProblem", target = "petProblem"),
            @Mapping(source = "vetClinicEntityId", target = "vetClinicId")
    })
    ClientDto clientEntityToClientDto(ClientEntity clientEntity);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "petName", target = "petName"),
            @Mapping(source = "petProblem", target = "petProblem"),
            @Mapping(source = "vetClinicId", target = "vetClinicEntityId")
    })
    ClientEntity clientDtoToClientEntity(ClientDto clientDto);
}
