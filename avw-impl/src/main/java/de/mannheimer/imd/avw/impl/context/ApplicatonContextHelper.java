package de.mannheimer.imd.avw.impl.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import de.mannheimer.imd.avw.api.commons.Assert;

public class ApplicatonContextHelper implements ApplicationContextAware {

	static final Logger logger = LoggerFactory
			.getLogger(ApplicatonContextHelper.class);

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		setContext(applicationContext);
		logger.debug("Set static application context to " + applicationContext);

	}

	public static ApplicationContext getContext() {

		return context;
	}

	public static void setContext(ApplicationContext context) {

		Assert.notNull(
				"Error trying to set ApplicationContext: Parameter context is null!",
				context);

		ApplicatonContextHelper.context = context;
	}

}
