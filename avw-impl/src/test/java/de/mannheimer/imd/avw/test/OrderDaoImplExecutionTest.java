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
import de.mannheimer.imd.avw.impl.persistence.OrderDaoImpl;

/**
 * Standard execution test class for {@link OrderDaoImpl}
 * <p>
 * This class tests the standard execution of available public methods in
 * {@link OrderDaoImpl}.
 * <p>
 * Following method tests are covered:
 * <p>
 * {@link OrderDaoImpl#persist(Order)}
 * <ul>
 * <li>{@link OrderDaoImplExecutionTest#testPersistValidOrder()}</li>
 * <li>{@link OrderDaoImplExecutionTest#testPersistNullOrder()}</li>
 * </ul>
 * <p>
 * {@link OrderDaoImpl#findById(String)}
 * <ul>
 * <li>{@link OrderDaoImplExecutionTest#testFindOrderByInvalidIdString()}</li>
 * <li>{@link OrderDaoImplExecutionTest#testFindOrderByNullIdString()}</li>
 * <li>{@link OrderDaoImplExecutionTest#testFindOrderByValidIdString()}</li>
 * </ul>
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

	Order currentOrder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {

		currentOrder = orderDao.getNewInstance();
		orderDao.persist(currentOrder);

	}

	@After
	public void tearDown() throws Exception {

		orderDao.delete(currentOrder);

	}

	/**
	 * Tests method {@link OrderDaoImpl#persist(Order)}
	 * <p>
	 * Test persisting a normal and valid {@link Order}.
	 */
	@Test
	public void testPersistValidOrder() {

		Order order = currentOrder;

		assertNotNull("Current created order is null", order);
		assertNotNull("Documents list in order is null", order.getDocuments());
		assertNotNull("Documents list size is > 0",
				order.getDocuments().size() == 0);

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
	public void testPersistNullOrder() throws IllegalArgumentException {

		orderDao.persist(null);

	}

	/**
	 * Tests method {@link OrderDaoImpl#findById(String)}
	 * <p>
	 * Test to find order by a valid id string.
	 */
	@Test
	public void testFindOrderByValidIdString() {

		Order order = orderDao.findById(currentOrder.getId());
		assertNotNull("Order find by id is null", order);
		assertTrue("Persisted order id doesn't match",
				order.getId().equals(currentOrder.getId()));

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
		assertNull(order);

	}

	/**
	 * Tests method {@link OrderDaoImpl#findById(String)}
	 * <p>
	 * Test to find order by a <code>null</code> id string.
	 */
	@Test
	public void testFindOrderByNullIdString() {

		// test null string
		Order order2 = orderDao.findById(null);
		assertNull(order2);

	}

	/**
	 * Setter for {@link OrderDao} on which the tests will be executed. Actually
	 * this object is marked with {@link Inject} means that it will may be
	 * injected by a framework. If not you can use this setter to set this order
	 * dao object.
	 * 
	 * @param orderDao
	 *            The order dao object.
	 */
	public void setOrderDao(OrderDao orderDao) {

		this.orderDao = orderDao;
	}

	/**
	 * Returns the currently used {@link OrderDao} object on which all tests
	 * will be executed.
	 * 
	 * @return The current used order dao object
	 */
	public OrderDao getOrderDao() {

		return orderDao;
	}

}
