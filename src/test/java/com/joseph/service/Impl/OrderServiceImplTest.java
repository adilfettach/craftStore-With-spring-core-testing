package com.joseph.service.Impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joseph.config.PersistenceJPAConfig;
import com.joseph.entity.Client;
import com.joseph.entity.Order;
import com.joseph.entity.Status;
import com.joseph.repository.OrderRepository;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { PersistenceJPAConfig.class })
class OrderServiceImplTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@DisplayName("test save Order")
	void testSaveOrder() {
		Client c = new Client();
		c.setNameclient("yassine");
		c.setIdclient(1);
		c.setEmailclient("yassine@gmail.com");

		Order o = new Order();
		o.setIdorder(1L);
		o.setDateorder(LocalDate.now());
		o.setStatusorder(Status.INPROGRESS);
		o.setClient(c);
		o.setTotalorder(300.0);
		assertNotNull(orderRepository.save(o));

	}
}