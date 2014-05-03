package de.mannheimer.imd.avw.impl;

import javax.inject.Named;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Named
public class ApplicatonContextHelper implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		if (context == null) {
			ApplicatonContextHelper.context = applicationContext;
		}

	}

	public static ApplicationContext getContext() {

		return context;
	}

	public static void setContext(ApplicationContext context) {

		ApplicatonContextHelper.context = context;
	}

}
