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
        int res = factorial(n);
        tvOutput.append(res + "\n");
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
