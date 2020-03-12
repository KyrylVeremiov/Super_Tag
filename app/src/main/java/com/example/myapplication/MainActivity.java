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
    static int[][] field= new int[4][4];
    static int zero=0;
    static Button[] buttons;
    static long hash=0, M=1000000007,p=1;
    static boolean rotation=false;
    @SuppressLint("SetTextI18n")
    public void winCheck(){
        p=1;
        long tmp_hash=0;
        for(int i=0;i<16;i++){
            p*=97;
            p%=M;
            tmp_hash+=(Integer.parseInt((String) buttons[i].getText()))*p%M;
            tmp_hash%=M;
        }
        Log.d(TAG,"hash: "+ tmp_hash);
        if(tmp_hash==hash){
            Log.d(TAG, "again");
            for(int i=0;i<16;i++)
                buttons[i].setVisibility(View.GONE);
            findViewById(R.id.win).setVisibility(View.VISIBLE);
            findViewById(R.id.again).setVisibility(View.VISIBLE);
        }
        Log.d(TAG,": "+ hash);
    }

    public void changePosition(View view) {
        int I = 0, J = 0;
        int n = Integer.parseInt(getResources().getResourceEntryName(view.getId()).substring(3));
        Log.d(TAG, "cliced" + n);
        if(n==0){
            I=3;
            J=3;
        }
        else{
            I=n/4;
            J=n%4-1;
            if(J<0){
                I--;
                J=3;
            }
        }

        Log.d(TAG, I+" "+J);
        int c=0;
        boolean t=false;
        if (I > 0 && field[I - 1][J] == 0) {
            Log.d(TAG, "case: 0");
            c=(I-1)*4+J+1;
            t=true;
            field[I - 1][J] =  Integer.parseInt((String) buttons[n].getText());
        } else if (J < 3 && field[I][J + 1] == 0) {
            c=I*4+J+2;
            if(c==16)c=0;
            Log.d(TAG, "case: 1");
            t=true;
            field[I][J+1] = Integer.parseInt((String) buttons[n].getText());
        } else if (I < 3 && field[I + 1][J] == 0) {
            c=(I+1)*4+J+1;
            if(c==16)c=0;
            Log.d(TAG, "case: 2");
            t=true;
            field[I + 1][J] =  Integer.parseInt((String) buttons[n].getText());
    } else if (J > 0 && field[I][J - 1] == 0) {
            c=I*4+J;
            Log.d(TAG, "case: 3");
            t=true;
            field[I][J-1] =  Integer.parseInt((String) buttons[n].getText());
        }
        if(t){
            field[I][J]=0;
            zero=n;
            buttons[c].setVisibility(View.VISIBLE);
            buttons[c].setText(buttons[n].getText());
            buttons[n].setText("0");
            buttons[n].setVisibility(View.INVISIBLE);
            winCheck();
        }
        Log.d(TAG,Arrays.deepToString(field));
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
            p = 97;
            hash = 0;
            for (int i = 1; i < 16; i++) {
                buttons[i].setVisibility(View.VISIBLE);
                p *= 97;
                p %= M;
                hash += i * p % M;
                hash %= M;
            }
            for (int i = 1; i < 16; i++) {
                buttons[i].setText(tmp.get(i - 1).toString());
            }
            for (int i = 0; i < 15; i++) {
                field[i / 4][i % 4] = Integer.parseInt((String) buttons[i+1].getText());
            }
            field[3][3]=Integer.parseInt((String) buttons[0].getText());
        }
        else{
            for (int i = 0; i < 15; i++) {
                buttons[i+1].setText(Integer.valueOf(field[i/4][i%4]).toString());
            }
            buttons[0].setText(Integer.valueOf(field[3][3]).toString());
        }
        buttons[zero].setVisibility(View.INVISIBLE);
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