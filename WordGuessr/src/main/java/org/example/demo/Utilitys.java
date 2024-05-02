package org.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilitys {

    public static String readFile(String filename) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }
    public static int IsAValidWord(String input, String[] possibleWords) {
        if (input.length() != 5) {
            return 1;
        }
        for (String string : possibleWords) {
            if (string.equals(input)) {
                return 0;
            }
        }
        return 2;
    }
}

