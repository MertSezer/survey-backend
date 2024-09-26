package com.survey.polla.controller;

public class VolkanTest {
    public static void main(String[] args) {
        String password = "KVMN*64"; // return true
        String password2 = "KVMN64"; // return false
        boolean p1 = isValid(password);
        boolean p2 = isValid(password2);

        System.out.println("P1: "+ p1);
        System.out.println("P2: "+ p2);

    }
    public static boolean isValid(String password){
        // Write here only.
        return false;
    }
}

