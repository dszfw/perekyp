package org.maxi.booter.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
@EnableScheduling
public class SchedulerTest {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm.ss");
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Scheduled(fixedDelay = 5000)
	public void doShedule() {
		logger.info("Now :" + dateFormat.format(new Date()));
	}

}
