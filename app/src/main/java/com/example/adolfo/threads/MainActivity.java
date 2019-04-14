package com.example.adolfo.threads;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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

    class MyTask extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(MainActivity.this);
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setMessage("Calculating...");
            progress.setCancelable(false);
            progress.setMax(100);
            progress.setProgress(0);
            progress.show();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            // Executes on a secondary thread
            int res = 1;
            for (int i=1; i <= integers[0]; i++) {
                res *= i;
                SystemClock.sleep(1000);
                publishProgress(i * 100 / integers[0]);
            }
            //res = factorial(integers[0]);
            return res;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            // Executes on the UI thread
            progress.dismiss();
            tvOutput.append(integer + "\n");
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
        //MyThread thread = new MyThread(n);
        //thread.start();
        MyTask task = new MyTask();
        task.execute(n);
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
