package de.mannheimer.imd.avw.test;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.impl.aspects.LoggingAspect;

/**
 * @author Dennis Ahaus
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/*-context.xml" })
public class MiscExecutionTest {

	@Inject
	ApplicationContext context;

	@Inject
	LoggingAspect aspect;

	@Test
	public void testLoggingAspectPersistenceExecution() {

		aspect.persistenceExecution();
	}

	@Test
	public void testStateGetDescription() {

		Collection<State> states = context.getBeansOfType(State.class).values();
		for (State state : states) {
			Assert.assertNotNull(state.getDescription());
			Assert.assertTrue(state.getDescription().length() > 10);
		}

	}

}
