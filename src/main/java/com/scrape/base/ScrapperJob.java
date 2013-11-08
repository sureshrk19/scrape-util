package com.scrape.base;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.scrape.db.DataBaseHelper;

@Component
public class ScrapperJob extends QuartzJobBean {

	private static Logger log = Logger.getLogger(ScrapperJob.class);

	@Autowired
	DataBaseHelper dataBaseHelper;

	/**
	   * Setter called after the ScrapperJob is instantiated
	   * with the value from the JobDetailBean
	   */
	  public void setDataBaseHelper(DataBaseHelper dataBaseHelper) {
	    this.dataBaseHelper = dataBaseHelper;
	  }
	  
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		log.info("executeInternal :: ScrapperJob :: Begin");
		dataBaseHelper.insertAllEvents();
		log.info("executeInternal :: ScrapperJob :: End");
	}
}
