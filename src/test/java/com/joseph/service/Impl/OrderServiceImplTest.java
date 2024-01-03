package com.joseph.service.Impl;

import java.time.LocalDate;
import java.util.List;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { PersistenceJPAConfig.class })
class OrderServiceImplTest {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderServiceImpl orderServiceImpl;

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
	@Test
	@DisplayName("test delete Order")
	void testDeleteOrder() {
		Client c = new Client();
		c.setNameclient("yassine");
		c.setIdclient(1);
		c.setEmailclient("yassine@gmail.com");

		Order o = new Order();
		o.setDateorder(LocalDate.now());
		o.setStatusorder(Status.INPROGRESS);
		o.setClient(c);
		o.setTotalorder(300.0);
		o = orderRepository.save(o);
		orderRepository.deleteById(o.getIdorder());
		Order finalO = o;
		assertThrows(NullPointerException.class,()->{orderServiceImpl.getOrder(finalO.getIdorder());});
//		assertNull(orderServiceImpl.getOrder(2L));
	}
//	@Test
//	@DisplayName("test get Order")
//	void testGetOrder() {
//		Client c = new Client();
//		c.setIdclient(1);
//
//		Order o1 = new Order();
//		o1.setDateorder(LocalDate.now());
//		o1.setStatusorder(Status.INPROGRESS);
//		o1.setClient(c);
//		o1.setTotalorder(300.0);
//		o1 = orderRepository.save(o1);
//
//		Order o2 = new Order();
//		o2.setDateorder(LocalDate.now());
//		o2.setStatusorder(Status.INPROGRESS);
//		o2.setClient(c);
//		o2.setTotalorder(300.0);
//		o2 = orderRepository.save(o2);
//		Order o = new Order();
//		o.setDateorder(LocalDate.now());
//		o.setStatusorder(Status.INPROGRESS);
//		o.setClient(c);
//		o.setTotalorder(300.0);
//		o = orderRepository.save(o);
//
//
//		assertEquals(3,orderServiceImpl.getOrders().size());
//	}

	@Test
	@DisplayName("test get Order")

	public void testGetOrder() {
		Order order = new Order();
		orderServiceImpl.save(order);
		Order retrievedOrder = orderRepository.findById(order.getIdorder()).orElse(null);
		assertEquals(order, retrievedOrder);}

//	@Test
//	@DisplayName("test get Orders")
//
//	public void testGetOrders() {
//		Order order1 = new Order();
//		// set properties of order1
//		orderRepository.save(order1);
//
//		Order order2 = new Order();
//		// set properties of order2
//		orderRepository.save(order2);
//
//		List<Order> orders = orderRepository.findAll();
//		assertTrue(orders.contains(order1));
//		assertTrue(orders.contains(order2));
//	}

}