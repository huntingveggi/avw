package de.mannheimer.imd.avw.impl.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	String preffix = "";
	String append = "   ";

	@Pointcut("within(de.mannheimer.imd.avw..*)")
	public void persistenceExecution() {
	};

	@Before("persistenceExecution()")
	public void beforeMethodtrace(JoinPoint joinPoint) {

		preffix += append;

		String target = joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName();
		logger.info(preffix + " ENTERING " + target);
	}

	@After("persistenceExecution()")
	public void AfterMethodtrace(JoinPoint joinPoint) {

		String target = joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName();
		logger.info(preffix + " LEAVING " + target);

		preffix = preffix.substring(0, preffix.length() - append.length());
	}
}
