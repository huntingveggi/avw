package de.mannheimer.imd.avw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.persistence.OrderDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/*-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class OrderDaoImplExecutionTest {

	Logger logger = LoggerFactory.getLogger(OrderDaoImplExecutionTest.class);

	Order currentCreatedOrder;

	@Inject
	OrderDao orderDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {

		logger.info("----------- SET UP TEST ----------------------------------------------------");
	}

	@After
	public void tearDown() throws Exception {

		orderDao.delete(currentCreatedOrder);

	}

	@Test
	public void testPersistValidOrder() {

		currentCreatedOrder = orderDao.getNewInstance();
		orderDao.persist(currentCreatedOrder);

		assertNotNull("Current created order is null", currentCreatedOrder);
		assertNotNull("Documents list in order is null",
				currentCreatedOrder.getDocuments());
		assertNotNull("Documents list size is > 0", currentCreatedOrder
				.getDocuments().size() == 0);

	}

	@Test
	public void testPersistNullOrder() {

		orderDao.persist(null);

	}

	@Test
	public void testFindOrderByValidIdString() {

		Order order = orderDao.findById(currentCreatedOrder.getId());
		assertNotNull("Order find by id is null", order);
		assertTrue("Persisted order id doesn't match",
				order.getId().equals(currentCreatedOrder.getId()));

	}

	@Test
	public void testFindOrderByInvalidIdString() {

		// test not existing id string
		String wrongIdString = System.currentTimeMillis() + "123--123";
		Order order = orderDao.findById(wrongIdString);
		assertNull(order);

	}

	@Test
	public void testFindOrderByNullIdString() {

		// test null string
		Order order2 = orderDao.findById(null);
		assertNull(order2);

	}

}
