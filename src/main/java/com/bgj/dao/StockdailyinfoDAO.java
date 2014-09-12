package com.bgj.dao;

import java.util.Date;
import java.util.List;

import com.bgj.util.DateUtil;



public class StockdailyinfoDAO {
	public static List getPrevious9DaysInfo(String stockId) {
		String sql = "select * from stockdailyinfo where stockid = '" + stockId
				+ "' order by date desc";
		String className = "com.ghlh.data.db.StockdailyinfoVO";
		return GhlhDAO.list(sql, className, 0, 9);
	}

	public static List getDaysInfo(String stockId, int count, Date date) {
		String sql = "select * from stockdailyinfo where stockid = '" + stockId
				+ "' AND DATE < '" + DateUtil.formatDay(date)
				+ "' and currentprice != 0 order by date desc";

		String className = "com.ghlh.data.db.StockdailyinfoVO";
		return GhlhDAO.list(sql, className, 0, count);
	}

	public static List getDaysInfo(String stockId, Date date) {
		String sql = "select * from stockdailyinfo where stockid = '" + stockId
				+ "' AND DATE < '" + DateUtil.formatDay(date)
				+ "' order by date desc";

		String className = "com.ghlh.data.db.StockdailyinfoVO";
		return GhlhDAO.list(sql, className);
	}

	public static List getDaysInfo(String stockId) {
		String sql = "select * from stockdailyinfo where stockid = '" + stockId
				+ "' and currentPrice != 0 order by date asc";
		String className = "com.ghlh.data.db.StockdailyinfoVO";
		return GhlhDAO.list(sql, className);
	}

	
}
