package com.erayo.myjoketellingjavalib;

import java.util.Random;

public class MyClass {

    private static String[] jokes = {"joke1", "joke2", "joke3", "joke4", "joke5",
                                     "joke6", "joke7", "joke8", "joke9", "joke10",
                                     "joke11", "joke12", "joke13", "joke14", "joke15"};

    public static String sendAJoke(){
        Random r = new Random();
        return jokes[r.nextInt(jokes.length)];
    }
}
