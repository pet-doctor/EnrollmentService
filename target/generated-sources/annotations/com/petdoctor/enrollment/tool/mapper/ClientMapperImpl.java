package com.petdoctor.enrollment.tool.mapper;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.dto.ClientDto.ClientDtoBuilder;
import com.petdoctor.enrollment.model.entity.ClientEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-24T18:14:30+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDto clientEntityToClientDto(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        ClientDtoBuilder clientDto = ClientDto.builder();

        clientDto.id( clientEntity.getId() );
        clientDto.name( clientEntity.getName() );
        clientDto.surname( clientEntity.getSurname() );
        clientDto.email( clientEntity.getEmail() );
        clientDto.petName( clientEntity.getPetName() );
        clientDto.petProblem( clientEntity.getPetProblem() );
        clientDto.vetClinicId( clientEntity.getVetClinicEntityId() );

        return clientDto.build();
    }

    @Override
    public ClientEntity clientDtoToClientEntity(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( clientDto.getId() );
        clientEntity.setName( clientDto.getName() );
        clientEntity.setSurname( clientDto.getSurname() );
        clientEntity.setEmail( clientDto.getEmail() );
        clientEntity.setPetName( clientDto.getPetName() );
        clientEntity.setPetProblem( clientDto.getPetProblem() );
        clientEntity.setVetClinicEntityId( clientDto.getVetClinicId() );

        return clientEntity;
    }
}
