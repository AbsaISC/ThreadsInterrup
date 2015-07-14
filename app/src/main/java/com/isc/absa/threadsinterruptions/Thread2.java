package com.isc.absa.threadsinterruptions;

import android.content.Context;
import android.os.Handler;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by Absalom on 13/07/2015.
 */
public class Thread2 extends Thread {
    Context context;
    EditText print;
    String secuencia="";
    int evenNum= 0;
    int contador = 0;
    public boolean flagInterrup = false;

    public Thread2(Context context, EditText print) {
        super("Hilo_secuencia2");
        this.context = context;
        this.print = print;
    }

    @Override
    public void run() {
//        super.run();
        while(true){
            if(contador>=10){
                print("\n Hilo 2 detenido  \n");
                break;
            }
            secuencia="Números random";
            print("\nNúmeros random \n");
            randomNumber();
            secuencia="Números pares";
            print("\nNúmeros pares\n");
            evenNumbers();
        }
    }

    public void randomNumber() {
        int min = 1, max = 100;
        Random random = new Random();
        for (; ; ) {
            if (flagInterrup) {
                contador++;
                flagInterrup = false;
                return;
            }
            int randomNum = random.nextInt((max - min) + 1) + min;
            print(", "+randomNum);
            sleep();
        }
    }

    public void evenNumbers() {
        for (; ; ) {
            if (flagInterrup) {
                contador++;
                flagInterrup = false;
                return;
            }
            if(evenNum%2==0)
                print(", " + evenNum);
            evenNum++;
            sleep();
        }
    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void print(final String text) {
        Handler handler=new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                print.setText(print.getText().toString() + text);
                print.setSelection(print.getText().length());
            }
        });

    }

    public void inter(){
        flagInterrup=true;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public int getContador() {
        return contador;
    }
}
