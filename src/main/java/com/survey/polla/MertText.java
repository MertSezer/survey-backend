package com.survey.polla;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MertText {
    public static String hash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "password";
        String hash = hash(password);
        System.out.println("BCrypt Hash: " + hash);

    }
}
