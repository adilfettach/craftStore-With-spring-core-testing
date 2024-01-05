package com.joseph.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.joseph.config.PersistenceJPAConfig;
import com.joseph.entity.Client;
import com.joseph.repository.ClientRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class })
@Transactional
class ClientServiceImplTest {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	Client client;

	@BeforeEach
	@DisplayName("Setup Tests for Client")
	void setUp() {
		clientRepository.deleteAll();
		client = new Client();
		client.setEmailclient("test@test.com");
		client.setNameclient("test");
		clientRepository.save(client);
		client = new Client();
		client.setEmailclient("test1@test.com");
		client.setNameclient("test");
		clientRepository.save(client);
		client = new Client();
		client.setEmailclient("test2@test.com");
		client.setNameclient("test");
		clientRepository.save(client);

	}

	@Test
	@DisplayName("Test Get Client")
	void TestGetClient() {
		client = clientRepository.findAll().stream().findFirst().orElse(null);
		clientServiceImpl.getClient(client.getIdclient());
	}

	@Test
	@DisplayName("Test Get Clients")
	void TestGetClients() {
		Integer size = clientServiceImpl.getClients().size();
		assertEquals(3, size);
	}

	@Test
	@DisplayName("Test Save Client")
	void testSaveClient() {
		client = new Client();
		client.setEmailclient("test4@test.com");
		client.setNameclient("test4");
		clientServiceImpl.saveClient(client);
		assertEquals(4, clientRepository.findAll().size());
	}

	@Test
	@DisplayName("Test Delete Client")
	void testDeleteClient() {
		clientServiceImpl.deleteClient(client.getIdclient());
		assertEquals(2, clientRepository.findAll().size());
	}

	@Nested
	@ContextConfiguration(classes = { PersistenceJPAConfig.class })
	class parameterizedAndRepetedTest {
		@ParameterizedTest
		@DisplayName("Test save clients from csv")
		@CsvFileSource(resources = "/data-test.csv")
		void saveClientWithParameterizedData(String email, String name) {
			client.setEmailclient(email);
			client.setNameclient(name);
			clientServiceImpl.saveClient(client);
			assertEquals(3, clientRepository.findAll().size());
		}
	}

	@AfterEach
	@DisplayName("Cleanup Test")
	void cleanupTest() {
		clientRepository.deleteAll();
	}

}
