package com.bgj.autojobs;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.bgj.collecting.DataCollector;
import com.bgj.dao.GhlhDAO;
import com.bgj.util.DateUtil;
import com.bgj.util.EventRecorder;
import com.bgj.util.StockMarketUtil;

public class AutoCollectingAfterCloseJob implements Job {
	private static Logger logger = Logger
			.getLogger(AutoCollectingAfterCloseJob.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String message = "��ʼ15:05�̺���";
		EventRecorder.recordEvent(this.getClass(), message);
		if (StockMarketUtil.isMarketRest()) {
			return;
		}

		collectStockDailyInfo();
		message = "����15:05�̺���";
		EventRecorder.recordEvent(this.getClass(), message);
	}

	private void collectStockDailyInfo() {
		Date now = new Date();
		EventRecorder.recordEvent(this.getClass(), "��ʼ�ռ���������");
		String today = DateUtil.formatDay(now);
		for (int i = 0; i < 5; i++) {
			new DataCollector().collectDailyInfo(now, false);
			String sql = "SELECT COUNT(*) FROM stockdailyinfo WHERE "
					+ "(stockid = '000001' OR stockid = '600036' OR stockid = '000002') AND DATE LIKE '"
					+ today + "%'";
			String value = GhlhDAO.selectSingleValue(sql);
			int count = Integer.parseInt(value);
			if (count != 0) {
				EventRecorder.recordEvent(this.getClass(), "�� " + (i + 1)
						+ "�ռ��ɹ�");
				break;
			} else {
				if (i == 4) {
					EventRecorder.recordEvent(this.getClass(), "�� " + (i + 1)
							+ "�ռ� ����û�гɹ�!");
				}
			}
		}
		EventRecorder.recordEvent(this.getClass(), "�����ռ���������");
	}

}
