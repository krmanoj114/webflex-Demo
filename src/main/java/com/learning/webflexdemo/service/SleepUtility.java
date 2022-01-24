package com.learning.webflexdemo.service;

public class SleepUtility {

    public static void sleepSecond(int second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }

    }
}
