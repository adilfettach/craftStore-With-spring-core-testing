package com.joseph.service.Impl;

import com.joseph.config.PersistenceJPAConfig;
import com.joseph.entity.Client;
import com.joseph.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @BeforeEach
    void setUp() {

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
}
