package de.mannheimer.imd.avw.impl;

import javax.inject.Named;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import de.ahaus.dennis.javautils.impl.helper.Assert;

@Named
public class ApplicatonContextHelper implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		setContext(applicationContext);

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
