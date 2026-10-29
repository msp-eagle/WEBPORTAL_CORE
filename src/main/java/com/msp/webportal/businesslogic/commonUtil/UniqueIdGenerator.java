package com.msp.webportal.businesslogic.commonUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UniqueIdGenerator {

    private final static String CRMS_ID = "CRMS";
    private final static String HTPEN = "-";
    private final static  String randomString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static  String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String Small_chars = "abcdefghijklmnopqrstuvwxyz";
    public static String getId() {
        String values = randomString + Capital_chars + Small_chars + Capital_chars ;
        Random rndm_method = new Random();
        char[] password = new char[30];
        for (int i = 0; i < 30; i++) {
            password[i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return String.valueOf(password);
    }

    public static String generateID(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String currentTime = localDateTime.format(DateTimeFormatter.ofPattern("ddMMyyHHmmss"));
        StringBuilder generatedID = new StringBuilder(CRMS_ID + HTPEN + currentTime + HTPEN);
        int min = 1, max = 52;
        for (int i=0; i <=5;i++){
            generatedID.append(randomString.charAt((int) Math.floor(Math.random() * (max - min + 1) + min)));
        }
        return generatedID.toString();
    }
    public static String generateRandomTransactionId(){
        Random random = new Random();
        // Generate a 10-digit random number
        long randomNumber = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        // Convert the random number to a string
        String randomString = String.valueOf(randomNumber);
        return randomString;
    }

}
