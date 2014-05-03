package de.mannheimer.imd.avw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.api.persistence.OrderDao;
import de.mannheimer.imd.avw.impl.persistence.OrderDaoImpl;

/**
 * Standard execution test class for {@link OrderDao}
 * <p>
 * This class tests the standard execution of available public methods in
 * {@link OrderDao} implementation.
 * 
 * @author Dennis Ahaus
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/*-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class OrderDaoImplExecutionTest {

	Logger logger = LoggerFactory.getLogger(OrderDaoImplExecutionTest.class);

	@Inject
	OrderDao orderDao;

	@Inject
	DocumentDao documentDao;

	Order currentOrder;

	static List<Order> createdOrders = new LinkedList<Order>();
	static OrderDao staticOrderDao;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		for (Order order : createdOrders) {
			staticOrderDao.delete(order);
		}

	}

	@Before
	public void setUp() throws Exception {

		if (staticOrderDao == null) {
			staticOrderDao = this.orderDao;
		}

		currentOrder = orderDao.getNewInstance();
		orderDao.persist(currentOrder);
		createdOrders.add(currentOrder);

	}

	/**
	 * Tests method {@link OrderDaoImpl#persist(Order)}
	 * <p>
	 * Test persisting an initial, valid {@link Order} without existing
	 * {@link Document}s.
	 */
	@Test
	public void testPersistInitialOrder() {

		Order order = currentOrder;

		assertNotNull("Current created order is null", order);
		assertNotNull("Documents list in order is null", order.getDocuments());
		assertNotNull("Documents list size is > 0",
				order.getDocuments().size() == 0);
		Assert.assertNotNull("order.messages is null", order.getMessages());
		Assert.assertNotNull("order.messages is null", order.getState());

	}

	/**
	 * Tests method {@link OrderDaoImpl#persist(Order)}
	 * <p>
	 * Test persisting a <code>null</code> {@link Order}.
	 * {@link IllegalArgumentException} is expected because of <code>null</code>
	 * argument.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown because of <code>null</code> argument
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPersistNullOrder() {

		orderDao.persist(null);

	}

	/**
	 * Tests method {@link OrderDaoImpl#persist(Order)}.
	 * <p>
	 * TODO comment
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	@Transactional
	public void testPersistInitialOrderWithNewDocuments() throws IOException {

		Order newOrder = orderDao.getNewInstance();
		createdOrders.add(newOrder);

		Document doc1 = documentDao.getNewInstance(MimeTypes.APPLICATION_PDF);
		newOrder.getDocuments().add(doc1);

		Document doc2 = documentDao.getNewInstance(MimeTypes.APPLICATION_PDF);
		newOrder.getDocuments().add(doc2);

		orderDao.persist(newOrder);

		Order persistedOrder = orderDao.findById(newOrder.getId());

		assertTrue(persistedOrder.getDocuments().size() == 2);

	}

	/**
	 * Tests method {@link OrderDaoImpl#findById(String)}
	 * <p>
	 * Test to find order by a valid id string.
	 */
	@Test
	public void testFindOrderByValidIdString() {

		Order order = orderDao.findById(currentOrder.getId());

		Assert.assertNotNull("Order find by id is null", order);
		Assert.assertTrue("Persisted order id doesn't match", order.getId()
				.equals(currentOrder.getId()));

	}

	/**
	 * Tests method {@link OrderDaoImpl#findById(String)}
	 * <p>
	 * Test to find order by and invalid (not existing) id string.
	 */
	@Test
	public void testFindOrderByInvalidIdString() {

		// test not existing id string
		String wrongIdString = System.currentTimeMillis() + "123--123";
		Order order = orderDao.findById(wrongIdString);

		Assert.assertNull(order);

	}

	/**
	 * Tests method {@link OrderDaoImpl#findById(String)}
	 * <p>
	 * Test to find order by a <code>null</code> id string.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindOrderByNullIdString() {

		// test null string
		orderDao.findById(null);

	}

	@Test
	public void testFindAll() {

		List<Order> orders = orderDao.findAll();
		Assert.assertTrue(orders.size() > 0);
	}

	@Test
	public void testGetNewInstance() {

		Order order = orderDao.getNewInstance();
		Assert.assertNotNull("order is null", order);
		Assert.assertNotNull("order.id is null", order.getId());
		Assert.assertNotNull("order.creationDate is null",
				order.getCreationDate());
	}

}
