package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG="MainActivity";
    static Button[] buttons;
    static boolean rotation=false;
    @SuppressLint("SetTextI18n")
    public void winCheck(){
        if(Calculation.winCheck()){
            Log.d(TAG, "again");
            for(int i=0;i<16;i++)
                buttons[i].setVisibility(View.GONE);
            findViewById(R.id.win).setVisibility(View.VISIBLE);
            findViewById(R.id.again).setVisibility(View.VISIBLE);
        }
    }

    public void changePosition(View view) {
        int n = Integer.parseInt(getResources().getResourceEntryName(view.getId()).substring(3));
        int a= Integer.parseInt((String) buttons[n].getText());
        int c=Calculation.changePosition(n,a);
        if(c!=-1){
            buttons[c].setVisibility(View.VISIBLE);
            buttons[c].setText(buttons[n].getText());
            buttons[n].setText("0");
            buttons[n].setVisibility(View.INVISIBLE);
            winCheck();
        }
    }

    @SuppressLint("SetTextI18n")
    public void restart(View view){
        Log.d(TAG,"restart");
        buttons = new Button[]{
                findViewById(R.id.num0),
                findViewById(R.id.num1),
                findViewById(R.id.num2),
                findViewById(R.id.num3),
                findViewById(R.id.num4),
                findViewById(R.id.num5),
                findViewById(R.id.num6),
                findViewById(R.id.num7),
                findViewById(R.id.num8),
                findViewById(R.id.num9),
                findViewById(R.id.num10),
                findViewById(R.id.num11),
                findViewById(R.id.num12),
                findViewById(R.id.num13),
                findViewById(R.id.num14),
                findViewById(R.id.num15)
        };
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)
            findViewById(R.id.title).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.title).setVisibility(View.GONE);
        if(!rotation){
            List<Integer> tmp = new ArrayList<Integer>();
            for (int i = 0; i < 15; i++) {
                tmp.add(i + 1);
            }
            Collections.shuffle(tmp);
            buttons[0].setText("0");
            findViewById(R.id.win).setVisibility(View.GONE);
            findViewById(R.id.again).setVisibility(View.GONE);
            for (int i = 0; i < 16; i++) {
                buttons[i].setVisibility(View.VISIBLE);
            }
            for (int i = 1; i < 16; i++) {
                buttons[i].setText(tmp.get(i - 1).toString());
            }
            Calculation.start(tmp);
        }
        else{
            for (int i = 0; i < 15; i++) {
                buttons[i+1].setText(Integer.valueOf(Calculation.getField()[i/4][i%4]).toString());
            }
            buttons[0].setText(Integer.valueOf(Calculation.getField()[3][3]).toString());
        }
        buttons[Calculation.getZero()].setVisibility(View.INVISIBLE);
        rotation=false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restart(findViewById(R.id.again));
    }

    protected void onDestroy() {
        super.onDestroy();
        rotation=true;
        Log.d(TAG, "onDestroy");
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume ");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
}