package com.scrape.service;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class ScrapperJob extends QuartzJobBean {

	private static Logger log = Logger.getLogger(ScrapperJob.class);

	@Autowired
	InitEventService initEventService;

	/**
	   * Setter called after the ScrapperJob is instantiated
	   * with the value from the JobDetailBean
	   */
	  public void setDataBaseHelper(InitEventService initEventService) {
	    this.initEventService = initEventService;
	  }
	  
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		log.info("executeInternal :: ScrapperJob :: Begin");
		initEventService.insertAllEvents();
		log.info("executeInternal :: ScrapperJob :: End");
	}
}
