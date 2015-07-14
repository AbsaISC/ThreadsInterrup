package com.isc.absa.threadsinterruptions;

import android.content.Context;
import android.os.Handler;
import android.widget.EditText;

/**
 * Created by Absalom on 13/07/2015.
 */
public class Thread3 extends Thread {
    Thread1 thread1;
    Thread2 thread2;
    EditText log;
    Context context;

    public Thread3(Thread1 thread1, Thread2 thread2, EditText log, Context context) {
        this.thread1 = thread1;
        this.thread2 = thread2;
        this.log = log;
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            sleep();
            Handler hadler = new Handler(context.getMainLooper());
            String newLog = "";
            newLog += FechaHora.getH() + " | " + thread1.getId() + " | " + thread1.getName() + " | "+thread1.getState()+
                    " | " + thread1.getSecuencia() + " | "+ thread1.getContador() + "\n";
            newLog += FechaHora.getH() + " | " + thread2.getId() + " | " + thread2.getName() + " | "
                    +thread1.getState()+" | "+ thread2.getSecuencia() + " | "+ thread2.getContador() + "\n";
            final String newLog2 = newLog;
            hadler.post(new Runnable() {
                @Override
                public void run() {
                    log.setText(log.getText() + newLog2);
                    log.setSelection(log.getText().length());
                }
            });
            if (!thread1.isAlive()){
                if(!thread2.isAlive()){
                    Handler hadler2 = new Handler(context.getMainLooper());
                    hadler.post(new Runnable() {
                        @Override
                        public void run() {
                            log.setText(log.getText() + "Hilo terminado \n");
                            log.setSelection(log.getText().length());
                        }
                    });
                    break;
                }
            }
        }
    }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
