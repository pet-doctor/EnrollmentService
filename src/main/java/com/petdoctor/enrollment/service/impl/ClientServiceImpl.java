package com.petdoctor.enrollment.service.impl;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.entity.ClientEntity;
import com.petdoctor.enrollment.repository.ClientRepository;
import com.petdoctor.enrollment.service.ClientService;
import com.petdoctor.enrollment.tool.exception.EnrollmentServiceNotFoundException;
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

        ClientEntity clientEntity = clientRepository
                .findClientEntityById(clientId).orElse(null);

        if (clientEntity == null) {
            return null;
        }

        return mapEntityToDto(clientEntity);
    }

    @Override
    public ClientDto registerClient(ClientDto clientDto) {

        ClientEntity clientEntity = clientMapper.clientDtoToClientEntity(clientDto);

        return mapEntityToDto(
                clientRepository
                        .save(clientEntity));

    }


    @Override
    @Transactional
    public ClientDto changeClientInfo(Long clientId, ClientDto clientDto) {

        ClientEntity clientEntity = findClientById(clientId);

        if (clientDto.getId() != null) clientEntity.setId(clientDto.getId());
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
                .orElseThrow(() -> {
                    throw new EnrollmentServiceNotFoundException("Client has not found");
                });
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
