package com.example.adolfo.threads;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private TextView tvOutput;

    class MyThread extends Thread {
        private int n, res;

        public MyThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            res = factorial(n);
            //tvOutput.append(res + "\n");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvOutput.append(res + "\n");
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.et_input);
        tvOutput = findViewById(R.id.tv_output);
    }

    public void compute(View view) {
        int n = Integer.parseInt(etInput.getText().toString());
        tvOutput.append(n + "! = ");
        MyThread thread = new MyThread(n);
        thread.start();
    }

    public int factorial(int n) {
        int res = 1;
        for (int i=1; i<=n; i++) {
            res *= i;
            SystemClock.sleep(1000);
        }
        return res;
    }
}
