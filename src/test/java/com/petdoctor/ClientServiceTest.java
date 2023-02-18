package com.petdoctor;

import com.petdoctor.enrollment.repository.ClientRepository;
import com.petdoctor.enrollment.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @BeforeEach
    private void setUp() {

    }
}
