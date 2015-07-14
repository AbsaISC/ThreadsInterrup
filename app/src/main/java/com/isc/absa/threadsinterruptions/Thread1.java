package com.isc.absa.threadsinterruptions;

import android.content.Context;
import android.os.Handler;
import android.widget.EditText;

import static java.lang.Math.*;

/**
 * Created by Absalom on 13/07/2015.
 */
public class Thread1 extends Thread {
    Context context;
    EditText print;

    int numPrime = 1;
    String secuencia = "";
    int numFib1 = 0;
    int numFib2 = 1;
    int numAux;
    int contador = 0;
    public boolean flagInterrup = false;
    public boolean stop = false;

    public Thread1(Context context, EditText print) {
        super("Hilo_secuencia1");
        this.context = context;
        this.print = print;
    }

    @Override
    public void run() {
//        super.run();
        while (true) {
            if (contador >= 10) {
                print("\nHilo 1 detenido \n");
                break;
            }
            secuencia = "Serie Fibonancci";
            print("\nFibonancci \n");
            fibonancci();
            secuencia = "Números primos";
            print("\nNúmeros primos \n");
            primeNumber();
        }
    }

    public void fibonancci() {
        for (; ; ) {
            if (flagInterrup) {
                contador++;
                flagInterrup = false;
                return;
            }
            if (numFib1 == 0) {
                print(numFib1 + ", " + (numFib1 + numFib2));
            }
            numAux = numFib2;
            numFib2 = numFib2 + numFib1;
            numFib1 = numAux;
            print(", " + (numFib2));
            sleep();
        }
    }

    public void primeNumber() {
        int j, div, raiz;
        boolean isFinish = true;
        for (; ; ) {
            if (flagInterrup) {
                contador++;
                flagInterrup = false;
                break;
            }
            div = 0;
            raiz = (int) sqrt(numPrime);
            for (j = 2; j <= raiz; j++) {
                if (numPrime % j == 0) {
                    isFinish = false;
                    break;
                }


            }
            if (isFinish) {
                if (numPrime != 2)
                    print(", " + numPrime);
            }
            isFinish = true;
            numPrime++;
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
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                print.setText(print.getText().toString() + text);
                print.setSelection(print.getText().length());
            }
        });

    }

    public void inter() {
        flagInterrup = true;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public int getContador() {
        return contador;
    }
}
