package de.mannheimer.imd.avw.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.impl.context.ApplicatonContextHelper;

/**
 * @author Dennis Ahaus
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/spring/context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class ApplicationContextHelperExecutionTest {

	@Inject
	ApplicationContext context;

	@Test
	public void testSetContextWithValidContext() {

		ApplicatonContextHelper.setContext(this.context);
		Assert.assertNotNull(this.context);
		Assert.assertTrue(ApplicatonContextHelper.getContext() == this.context);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetContextWithNullContext() {

		ApplicatonContextHelper.setContext(null);
	}

}
