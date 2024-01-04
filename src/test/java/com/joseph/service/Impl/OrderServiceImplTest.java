package com.joseph.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joseph.Model.rapportData;
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
	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@BeforeEach
	@DisplayName("Setup Tests for Orders")
	void setupTest() {
		Client c = new Client();
		c.setIdclient(1);
		Order o1 = new Order();
		o1.setDateorder(LocalDate.now());
		o1.setStatusorder(Status.INPROGRESS);
		o1.setClient(c);
		o1.setTotalorder(300.0);
		o1 = orderRepository.save(o1);
		Order o2 = new Order();
		o2.setDateorder(LocalDate.now());
		o2.setStatusorder(Status.INPROGRESS);
		o2.setClient(c);
		o2.setTotalorder(300.0);
		o2 = orderRepository.save(o2);
		Order o3 = new Order();
		o3.setDateorder(LocalDate.now());
		o3.setStatusorder(Status.INPROGRESS);
		o3.setClient(c);
		o3.setTotalorder(300.0);
		o3 = orderRepository.save(o3);

	}

	@Test
	@DisplayName("Test Delete Order")
	void testdeleteOrder() {
		Order o = orderRepository.findAll().stream().findFirst().orElse(null);
		orderServiceImpl.deleteOrder(o.getIdorder());
		Integer size = orderRepository.findAll().size();
		assertEquals(2, size);
	}

	@Test
	@DisplayName("Test Get Order")
	void getOrder() {
		Order firstOrder = orderRepository.findAll().stream().findFirst().orElse(null);
		Order o = orderServiceImpl.getOrder(firstOrder.getIdorder());
		assertNotNull(o);
	}

	@Test
	@DisplayName("Test Get All Orders")
	void getOrders() {
		List<Order> orders = orderServiceImpl.getOrders();
		assertEquals(3, orders.size());
		assertNotNull(orders);
	}

	@Test
	@DisplayName("test save Order")
	void testSaveOrder() {
		Client c = new Client();
		c.setIdclient(1);
		Order o = new Order();
		o.setIdorder(1L);
		o.setDateorder(LocalDate.now());
		o.setStatusorder(Status.INPROGRESS);
		o.setClient(c);
		o.setTotalorder(300.0);
		orderServiceImpl.saveOrder(o);
		assertEquals(4, orderRepository.findAll().size());

	}

	@Nested
	@ContextConfiguration(classes = { PersistenceJPAConfig.class })
	class rapports {
		@Test
		@DisplayName("test get Total Orders Between other Dates")
		void getorderdate2() {
			Client c = new Client();
			c.setIdclient(1);
			Order o1 = new Order();
			o1.setDateorder(LocalDate.of(2022, 12, 23));
			o1.setStatusorder(Status.INPROGRESS);
			o1.setClient(c);
			o1.setTotalorder(300.0);
			o1 = orderRepository.save(o1);
			Order o2 = new Order();
			o2.setDateorder(LocalDate.of(2022, 12, 23));
			o2.setStatusorder(Status.INPROGRESS);
			o2.setClient(c);
			o2.setTotalorder(300.0);
			o2 = orderRepository.save(o2);
			Order o3 = new Order();
			o3.setDateorder(LocalDate.of(2022, 12, 23));
			o3.setStatusorder(Status.INPROGRESS);
			o3.setClient(c);
			o3.setTotalorder(300.0);
			o3 = orderRepository.save(o3);
			List<rapportData> ordeRapportDatas = orderServiceImpl.getTotalOrdersBetweenDates(LocalDate.of(2022, 12, 23),
					LocalDate.now());
			assertEquals(900, ordeRapportDatas.get(0).getTotal());
			assertEquals(900, ordeRapportDatas.get(1).getTotal());
			assertEquals(2, ordeRapportDatas.size());
		}

		@Test
		@DisplayName("test get Total Orders Between Dates")
		void getorderdate1() {
			List<rapportData> ordeRapportDatas = orderServiceImpl.getTotalOrdersBetweenDates(LocalDate.now(),
					LocalDate.now());
			assertEquals(900, ordeRapportDatas.get(0).getTotal());
			assertEquals(1, ordeRapportDatas.size());
		}
	}

	@AfterEach
	@DisplayName("Cleanup Test")
	void cleanupTest() {
		orderRepository.deleteAll();
	}

}