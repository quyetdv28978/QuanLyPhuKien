/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utility;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Admin
 */
public class TimeTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
      @Override
      public void run() {
        // task to run goes here
//        System.out.println("Hello !!!");
        new view.KhuyenMai1().setVisible(true);
      }
    };
    Timer timer = new Timer();
    long delay = 0;
//    long intevalPeriod = 12 * 60 * 60 * 1000; 
long intevalPeriod = 3* 1000; 
    // schedules the task to be run in an interval 
    timer.scheduleAtFixedRate(task, delay,
                                intevalPeriod);
  } // end of main
    }
    

