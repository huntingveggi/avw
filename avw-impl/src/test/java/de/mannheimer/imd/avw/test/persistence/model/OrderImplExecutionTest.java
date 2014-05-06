package de.mannheimer.imd.avw.test.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.api.model.Message;
import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;

/**
 * @author Dennis Ahaus
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/spring/*-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class OrderImplExecutionTest {

	static State defaultTestState;

	@Inject
	ApplicationContext context;

	@Before
	public void setUpBe() {

		defaultTestState = context.getBean("initial", State.class);

		Assert.assertNotNull(defaultTestState);
	}

	@Test
	public void testOrderImplWithValidStateName() {

		OrderImpl order = new OrderImpl();

		order.setStateName(defaultTestState.getName());
		State s = order.getState();

		Assert.assertNotNull(order.getStateName());
		Assert.assertTrue(s == defaultTestState);

		boolean nameEquals = order.getStateName().equals(
				defaultTestState.getName());

		Assert.assertTrue(nameEquals);
		Assert.assertNotNull(order.getState());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOrderImplWithNullStateName() {

		OrderImpl order = new OrderImpl();

		order.setStateName(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testOrderImplWithNullState() {

		OrderImpl order = new OrderImpl();

		order.setState(null);

	}

	@Test
	public void testSetContextWithValidContext() {

		OrderImpl order = new OrderImpl();

		order.setContext(context);

		Assert.assertTrue(order.getContext() == context);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetContextWithNullContext() {

		OrderImpl order = new OrderImpl();

		order.setContext(null);

	}

	@Test
	public void testSetMessageWithValidList() {

		OrderImpl order = new OrderImpl();

		List<Message> messages = new ArrayList<Message>();

		order.setMessages(messages);

		Assert.assertNotNull("order.messages is null", order.getMessages());
		Assert.assertTrue("order message does not match created messages",
				order.getMessages() == messages);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMessageWithNullList() {

		OrderImpl order = new OrderImpl();

		order.setMessages(null);

	}
}
