package com.petdoctor.enrollment.service.impl;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.entity.ClientEntity;
import com.petdoctor.enrollment.repository.ClientRepository;
import com.petdoctor.enrollment.service.ClientService;
import com.petdoctor.enrollment.tool.exception.EnrollmentNotFoundException;
import com.petdoctor.enrollment.tool.exception.EnrollmentServiceNotFoundException;
import com.petdoctor.enrollment.tool.exception.EnrollmentValidationException;
import com.petdoctor.enrollment.tool.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto getClientById(Long clientId) {

        ClientEntity clientEntity = findClientById(clientId);

        if (clientEntity == null) {
            throw new EnrollmentNotFoundException("Client doesn't exist");
        }

        return mapEntityToDto(clientEntity);
    }

    @Override
    public ClientDto registerClient(ClientDto clientDto) {

        if (findClientById(clientDto.getId()) != null) {
            throw new EnrollmentNotFoundException("Client is already exists");
        }

        ClientEntity clientEntity = clientMapper.clientDtoToClientEntity(clientDto);

        return mapEntityToDto(
                clientRepository
                        .save(clientEntity));

    }


    @Override
    public ClientDto changeClientInfo(Long clientId, ClientDto clientDto) {

        ClientEntity clientEntity = findClientById(clientId);

        if (clientEntity == null) {
            throw new EnrollmentValidationException("Client is already exist");
        }

        if (clientDto.getName() != null) clientEntity.setName(clientDto.getName());
        if (clientDto.getSurname() != null) clientEntity.setSurname(clientDto.getSurname());
        if (clientDto.getEmail() != null) clientEntity.setEmail(clientDto.getEmail());
        if (clientDto.getPetName() != null) clientEntity.setPetName(clientDto.getPetName());
        if (clientDto.getPetProblem() != null) clientEntity.setPetProblem(clientDto.getPetProblem());
        if (clientDto.getVetClinicId() != null) clientEntity.setVetClinicEntityId(clientDto.getVetClinicId());

        return mapEntityToDto(clientEntity);
    }

    private ClientEntity findClientById(Long clientId) {

        return clientRepository.findClientEntityById(clientId)
                .orElse(null);
    }

    private ClientEntity mapDtoToEntity(ClientDto clientDto) {

        return clientMapper
                .clientDtoToClientEntity(clientDto);
    }

    private ClientDto mapEntityToDto(ClientEntity clientEntity) {

        return clientMapper
                .clientEntityToClientDto(clientEntity);
    }
}
