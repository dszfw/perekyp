package org.maxi.booter.service.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.maxi.booter.domain.car.EngineType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Deprecated
@Aspect
//@Component
public class ServiceMonitor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// TODO should be delete
	@AfterReturning("execution(* org.springframework.data.repository.CrudRepository.save(*)) && args(engineType)")
	public void logServiceAccess2(JoinPoint joinPoint, EngineType engineType) {
		// logger.info("Completed: " + joinPoint);
		System.out.println("Completed: " + joinPoint);
		System.out.println(engineType.getName());
	}

}
