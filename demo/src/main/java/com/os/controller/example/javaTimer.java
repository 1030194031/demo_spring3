package com.os.controller.example;

import com.os.comment.utils.ObjectUtils;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Administrator
 * 2015-5-5
 */
@Controller 
public class javaTimer {
	//时间间隔
	private static final long PERIOD_DAY =1000;
	private static Timer timer = null;
	public static void main(String[] args) {
		startTime("14:35:40",1);
//		stopTimer();
	}
	private static void startTime(String sendTime,int startType){
		System.out.println("定时开始");
		Calendar calendar = Calendar.getInstance();
		String[] timeArray=sendTime.split(":");
		
		//处理时间
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt( timeArray[0]));
		calendar.set(Calendar.MINUTE,  Integer.parseInt(timeArray[1]));
		calendar.set(Calendar.SECOND,  Integer.parseInt(timeArray[2]));
		if(startType==1){
			//每天执行
			startEvery(calendar.getTime());
		}
		if(startType==2){
			//执行单次
			startSingle(calendar.getTime());
		}
	}
	/**
	 * 停止定时
	 */
	public static void stopTimer(){
		if(ObjectUtils.isNotNull(timer)){
			timer.cancel();
		}
	}
	/**
	 * 定时执行开始 （重复执行）
	 */
	public static void startEvery(Date start){
		 timer=new Timer();
		 if (start.before(new Date())) {
			 start = addDay(start,1);
		 }
		 
		 TimerTask a= new TimerTask() {  
	        public void run() {  
	        	System.out.println("定时任务执行（重复执行）");
	        }  
		 };
		 //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		 timer.schedule(a,start,PERIOD_DAY);
	 }
	/**
	 * 定时执行开始（执行一次）
	 * @param start
	 * @param timing
	 */
	public static void startSingle(Date start){
		timer=new Timer();
		 if (start.before(new Date())) {
			 start = addDay(start,1);
		  }
		 //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		 timer.schedule(new TimerTask() {  
		        public void run() {  
		        	System.out.println("定时任务执行(执行一次)");
		        }  
		    }, start);
	 }
	/**
	 * 增加或减少天数
	 * @param date
	 * @param num
	 * @return
	 */
	private static Date addDay(Date date, int num){
		 Calendar startDT = Calendar.getInstance();
		 startDT.setTime(date);
		 startDT.add(Calendar.DAY_OF_MONTH, num);
		 return startDT.getTime();
	}
}
