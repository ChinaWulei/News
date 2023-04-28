package com.example.Utils;

import java.util.Random;

public class GenericCode6 {

    public static String genericCode6() {
        Random randObj = new Random();
        return Integer.toString(100000 + randObj.nextInt(900000));
    }

}
