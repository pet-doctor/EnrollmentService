package com.petdoctor.enrollment.tool.mapper;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.entity.ClientEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-18T23:44:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDto clientEntityToClientDto(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setId( clientEntity.getId() );
        clientDto.setName( clientEntity.getName() );
        clientDto.setSurname( clientEntity.getSurname() );
        clientDto.setEmail( clientEntity.getEmail() );
        clientDto.setPetName( clientEntity.getPetName() );
        clientDto.setPetProblem( clientEntity.getPetProblem() );
        clientDto.setVetClinicId( clientEntity.getVetClinicEntityId() );

        return clientDto;
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
