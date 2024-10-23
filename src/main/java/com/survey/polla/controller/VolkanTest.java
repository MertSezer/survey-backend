package com.survey.polla.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VolkanTest {
    public static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static void main(String[] args) {

        String password = "KVMNk*64"; // return true
        String password2 = "KVMN64"; // return false
        boolean p1 = isValid(password);
        boolean p2 = isValid(password2);

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);

    }

    public static boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

