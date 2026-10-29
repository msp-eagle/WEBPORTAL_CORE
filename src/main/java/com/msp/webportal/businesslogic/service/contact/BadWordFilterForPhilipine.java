package com.msp.webportal.businesslogic.service.contact;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BadWordFilterForPhilipine {
    private static int largestWordLength = 0;

    private static Map<String, String[]> allBadWords = new HashMap<String, String[]>();


    public static String getCensoredText(final String input) {
        loadBadWords();
        if (input == null) {
            return "";
        }

        else {
            String modifiedInput = input;

            String inputToReturn = null;


            modifiedInput = modifiedInput.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
                    .replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");

            modifiedInput = modifiedInput.toLowerCase().replaceAll("[^a-zA-Z]", "");

            ArrayList<String> badWordsFound = new ArrayList<>();

            for (int start = 0; start < modifiedInput.length(); start++) {

                for (int offset = 1; offset < (modifiedInput.length() + 1 - start) && offset < largestWordLength; offset++) {
                    String wordToCheck = modifiedInput.substring(start, start + offset);
                    if (allBadWords.containsKey(wordToCheck)) {
                        String[] ignoreCheck = allBadWords.get(wordToCheck);
                        boolean ignore = false;
                        for (int stringIndex = 0; stringIndex < ignoreCheck.length; stringIndex++) {
                            if (modifiedInput.contains(ignoreCheck[stringIndex])) {
                                ignore = true;
                                break;
                            }
                        }
                        if (!ignore) {
                            badWordsFound.add(wordToCheck);
                        }
                    }
                }
            }

            inputToReturn = input;
            for (String swearWord : badWordsFound) {
                char[] charsStars = new char[swearWord.length()];
                Arrays.fill(charsStars, '*');
                final String stars = new String(charsStars);

                inputToReturn = inputToReturn.replaceAll("(?i)" + swearWord, stars);
            }

            return inputToReturn;
        } // end getCensoredText

    }


//    private static void loadBadWords() {
//        int readCounter = 0;
////        System.out.println("test :"+BadWordFilterForPhilipine.class.getClassLoader().getResource("WEB_config.conf").getPath());
////        ClassPathResource classPathResource = new ClassPathResource("WEB_config.conf");
////        System.out.println("test1 :"+ classPathResource.getPath());
//        try {
////            String filepath = ClassLoader.getSystemResource("wf.csv").getPath();
//            ClassPathResource classPathResource = new ClassPathResource("wf.csv");
//            String filepath = classPathResource.getFile().getPath();
//
//            FileReader fr = new FileReader(filepath);
//            BufferedReader reader = new BufferedReader(fr);
//            System.out.println("filepath :"+filepath);
//            String currentLine = "";
//            while ((currentLine = reader.readLine()) != null) {
//                readCounter++;
//                String[] content = null;
//                try {
//                    if (1 == readCounter) {
//                        continue;
//                    }
//
//                    content = currentLine.split(",");
//                    if (content.length == 0) {
//                        continue;
//                    }
//
//                    final String word = content[0];
//
//                    if (word.startsWith("-----")) {
//                        continue;
//                    }
//
//                    if (word.length() > largestWordLength) {
//                        largestWordLength = word.length();
//                    }
//
//                    String[] ignore_in_combination_with_words = new String[] {};
//                    if (content.length > 1) {
//                        ignore_in_combination_with_words = content[1].split("_");
//                    }
//
//                    allBadWords.put(word.replaceAll(" ", "").toLowerCase(), ignore_in_combination_with_words);
//                } catch (Exception except) {
//                }
//            }
//        } catch (IOException except) {
//        }
//    }

    private static void loadBadWords() {
        int readCounter = 0;
        try {
            ClassPathResource resource = new ClassPathResource("wf.csv");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    readCounter++;
                    String[] content = currentLine.split(",");
                    if (content.length == 0 || readCounter == 1) {
                        continue;
                    }

                    final String word = content[0];
                    if (word.startsWith("-----")) {
                        continue;
                    }

                    if (word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }

                    String[] ignore_in_combination_with_words = content.length > 1 ? content[1].split("_") : new String[]{};
                    allBadWords.put(word.replaceAll(" ", "").toLowerCase(), ignore_in_combination_with_words);
                }
            }
        } catch (IOException except) {
        }
    }


}
