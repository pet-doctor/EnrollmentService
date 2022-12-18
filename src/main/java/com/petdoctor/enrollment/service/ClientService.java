package com.petdoctor.enrollment.service;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.entity.ClientEntity;

import java.util.Optional;

public interface ClientService {

    ClientDto getClientById(Long clientId);
    ClientDto registerClient(ClientDto clientDto);
    ClientDto changeClientInfo(Long clientId, ClientDto clientDto);
}
