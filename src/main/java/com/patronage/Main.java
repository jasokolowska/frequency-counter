package com.patronage;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************** FREQUENCY COUNTER ***********************");
        System.out.println("Podaj ścieżkę do pliku: ");
        String path = scanner.nextLine();

        File file = new File(path);

        WordsCounter wordsCounter = new WordsCounter();
        Map<String, Integer> countedWords = wordsCounter.countWords(file);
        System.out.println(countedWords);
    }

}
