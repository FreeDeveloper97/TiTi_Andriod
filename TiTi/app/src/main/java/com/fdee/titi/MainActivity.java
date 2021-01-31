package com.fdee.titi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dinuscxj.progressbar.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    int time = 0;
    RelativeLayout backgroundView;
    CircleProgressBar Graph;
    Button TEST;
    Button STOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundView = findViewById(R.id.backgroundView);
        Graph = findViewById(R.id.graph);
        TEST = findViewById(R.id.TEST);
        STOP = findViewById(R.id.STOP);
        int BLACK = ContextCompat.getColor(this, R.color.BLACK);
        int BLUE = ContextCompat.getColor(this, R.color.BLUE);

        Graph.setMax(60);

        Graph.setProgress(time);
        Graph.setProgressFormatter((progress, max) -> {
            return getTimeText(progress);
        });

        TEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upTime();
                setStartColor();
            }
        });

        STOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStopColor();
            }
        });

    }

    private String getTimeText(int time) {
        int S = time%60;
        int H = time/3600;
        int M = time/60 - H*60;
        String HH = Integer.toString(H);
        String MM = (M < 10) ? "0" + Integer.toString(M) : Integer.toString(M);
        String SS = (S < 10) ? "0" + Integer.toString(S) : Integer.toString(S);
        return HH+":"+MM+":"+SS;
    }

    private void upTime() {
        time += 1;
        Graph.setProgress(time);
    }

    private void setStartColor() {
        int BLACK = ContextCompat.getColor(this, R.color.BLACK);
        int BLUE = ContextCompat.getColor(this, R.color.BLUE);
        Graph.setProgressStartColor(BLUE);
        Graph.setProgressEndColor(BLUE);
        Graph.setProgressTextColor(BLUE);
        backgroundView.setBackgroundColor(BLACK);
    }

    private void setStopColor() {
        int WHITE = ContextCompat.getColor(this, R.color.WHITE);
        int BLUE = ContextCompat.getColor(this, R.color.BLUE);
        Graph.setProgressStartColor(WHITE);
        Graph.setProgressEndColor(WHITE);
        Graph.setProgressTextColor(WHITE);
        backgroundView.setBackgroundColor(BLUE);
    }
}