package com.bgj.autojobs;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import com.bgj.collecting.AutoDataCollectingJob;

public class BgjAutoQuartzServer {
	private static Logger logger = Logger.getLogger(BgjAutoQuartzServer.class);

	private static BgjAutoQuartzServer instance = new BgjAutoQuartzServer();

	public static BgjAutoQuartzServer getInstance() {
		return instance;
	}

	private Scheduler scheduler = null;

	private void scheduleJob(int hour, int mins, String jobName, Class jobClass) {
		try {
			JobDetail job = new JobDetail(jobName + "Job",
					Scheduler.DEFAULT_GROUP, jobClass);
			Trigger trigger = TriggerUtils.makeDailyTrigger(jobName + "Triger",
					hour, mins);
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException ex) {
			logger.error("Make auto job : " + jobName + "throw: ", ex);
		}
	}

	private BgjAutoQuartzServer() {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduleJob(15, 1, "AfterCloseAutoTradeJob",
					AutoCollectingAfterCloseJob.class);

		} catch (SchedulerException ex) {
			logger.error("Make auto trade scheduler throw: ", ex);
		}
	}

	public void startJob() {
		try {
			scheduler.start();
		} catch (SchedulerException ex) {
			logger.error("Start AutoJob throw: ", ex);
		}
	}

	public void stopJob() {
		try {
			scheduler.standby();
		} catch (SchedulerException ex) {
			logger.error("Stop AutoJob throw: ", ex);
		}
	}
}