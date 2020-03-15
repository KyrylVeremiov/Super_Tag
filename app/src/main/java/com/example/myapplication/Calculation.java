package com.example.myapplication;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Calculation {
    private static String TAG="MainActivity";
    private static int[][] field= new int[4][4];
    private static int zero=0;
    private static long hash=0, M=1000000007,p=1;
    static int[][] getField(){
        return field;
    }
    static int getZero(){
        return zero;
    }
    static boolean winCheck() {
        p=1;
        long tmp_hash=0;
        for(int i=0;i<16;i++){
            p*=97;
            p%=M;
            tmp_hash+=(field[i/4][i%4])*p%M;
            tmp_hash%=M;
        }
        Log.d(TAG,"hash: "+ tmp_hash);
        return (tmp_hash==hash);
    }
    static int changePosition(int n, int a) {
        int I = 0, J = 0;
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
        int c=-1;
        boolean t=false;
        if (I > 0 && field[I - 1][J] == 0) {
            Log.d(TAG, "case: 0");
            c=(I-1)*4+J+1;
            t=true;
            field[I - 1][J] = a;
        } else if (J < 3 && field[I][J + 1] == 0) {
            c=I*4+J+2;
            if(c==16)c=0;
            Log.d(TAG, "case: 1");
            t=true;
            field[I][J+1] = a;
        } else if (I < 3 && field[I + 1][J] == 0) {
            c=(I+1)*4+J+1;
            if(c==16)c=0;
            Log.d(TAG, "case: 2");
            t=true;
            field[I + 1][J] =  a;
        } else if (J > 0 && field[I][J - 1] == 0) {
            c=I*4+J;
            Log.d(TAG, "case: 3");
            t=true;
            field[I][J-1] = a;
        }
        if(t) {
            field[I][J] = 0;
            zero=n;
        }
        Log.d(TAG, Arrays.deepToString(field));
        return c;
    }

    static void start(List<Integer> tmp) {
        p = 97;
        hash = 0;
        for (int i = 1; i < 16; i++) {
            p *= 97;
            p %= M;
            hash += i * p % M;
            hash %= M;
        }
        for (int i = 0; i < 15; i++) {
            field[i / 4][i % 4] = tmp.get(i);
        }
        field[3][3]=0;
    }
    public void main(){

    }
}
