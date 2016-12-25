package demos;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * java的定时器的使用
 * @author wdj
 * 2016年8月9日
 * qq:2230419705
 * */
public class TestTimer extends TestCase{

    static int count = 0;
    
    public static void showTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 12, 9, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);
        
        int period = 2 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }

    @Test
    public static void testSubString(){
    	String name = "20160811111614_4853.jpeg";
    	String temp = name.substring(name.lastIndexOf("."),name.length());
    	System.out.println("temp==="+temp);
    	
    }
    
    
    
    
    
    public static void main(String[] args) {
//        showTimer();
        testSubString();
        
    }
	
}
