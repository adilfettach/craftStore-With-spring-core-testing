package com.joseph.service.Impl;

import com.joseph.config.PersistenceJPAConfig;
import com.joseph.entity.Client;
import com.joseph.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@Transactional
class ClientServiceImplTest {

    @Autowired
    private ClientRepository crepo;
    Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
    }
    @AfterEach
    void tearDown (){
        Client client = null;
    }

    @Test
    @DisplayName("Test Return a list of clients")
    void getClients() {
        assertEquals(16, crepo.findAll().size());
    }

    @Test
    @DisplayName("Test Save a client")
   void saveClient() {
        Client client = new Client();
        client.setEmailclient("test@example.com");
        client.setNameclient("adil ye");

        Client savedClient = crepo.save(client);

        assertEquals(client.getEmailclient(), savedClient.getEmailclient());
        assertEquals(client.getNameclient(), savedClient.getNameclient());
    }

    @Test
    @DisplayName("Test Get a client by ID")
    void getClient() {
        Client client = new Client();
        client.setEmailclient("test@example.com");
        client.setNameclient("Test Client");

        Client savedClient = crepo.save(client);

        Client retrievedClient = crepo.findById(savedClient.getIdclient()).orElse(null);

        assertNotNull(retrievedClient);
        assertEquals(client.getEmailclient(), retrievedClient.getEmailclient());
        assertEquals(client.getNameclient(), retrievedClient.getNameclient());
    }

    @Test
    @DisplayName("Test Delete a client by ID")
    void deleteClient() {
        Client client = new Client();
        client.setEmailclient("test@example.com");
        client.setNameclient("Test Client");

        Client savedClient = crepo.save(client);

        crepo.deleteById(savedClient.getIdclient());

        assertFalse(crepo.findById(savedClient.getIdclient()).isPresent());
    }
    @Nested
    class TestData  {
        @ParameterizedTest
        @CsvFileSource(resources = "/data-test.csv")
        void saveClientWithParameterizedData(String email, String name){
            client.setEmailclient(email);
            client.setNameclient(name);
            crepo.save(client);
            assertEquals(6,crepo.findAll().size());
        }
    }

}
