package de.mannheimer.imd.avw.test.persistence.model;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/*-context.xml" })
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

}
