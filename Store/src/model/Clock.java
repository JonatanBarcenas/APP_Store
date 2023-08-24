package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    
    public void currentHour(javax.swing.JLabel Label){
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Label.setText(format.format(LocalDateTime.now()).toUpperCase());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        
    }
    
}
