package com.isc.absa.threadsinterruptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity implements OnClickListener,OnTouchListener {

    Button inter1,inter2;
    EditText print1,print2,log;
    Thread1 t1;
    Thread2 t2;
    Thread3 t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buscaComponentes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_start) {
            t1=new Thread1(getApplicationContext(),print1);
            t2=new Thread2(getApplicationContext(),print2);
            t3=new Thread3(t1,t2,log,getApplicationContext());
            t1.start();
            t2.start();
            t3.start();
        }

        return super.onOptionsItemSelected(item);
    }

    private void buscaComponentes(){
        inter1= (Button) findViewById(R.id.btn_interrupt1);
        inter2=(Button) findViewById(R.id.btn_interrupt2);
        print1=(EditText) findViewById(R.id.et_printThread1);
        print2=(EditText) findViewById(R.id.et_printThread2);
        log=(EditText) findViewById(R.id.et_logs);
        inter1.setOnClickListener(this);
        inter2.setOnClickListener(this);
        print1.setOnTouchListener(this);
        print2.setOnTouchListener(this);

//        print1.setEnabled(false);
//        print2.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.btn_interrupt1:
                t1.inter();
                break;
            case R.id.btn_interrupt2:
                t2.inter();
                break;
            default:
                Log.d("test","default switch");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.requestFocus();
        return false;
    }
}
