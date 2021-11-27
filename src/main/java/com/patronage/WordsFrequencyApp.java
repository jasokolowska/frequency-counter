package com.patronage;

import java.awt.*;
import java.io.Console;
import java.io.File;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordsFrequencyApp {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BACKGROUND = "\u001B[46m";

    private Scanner scanner = new Scanner(System.in);
    WordsCounter wordsCounter = new WordsCounter();
    DataReader reader = new DataReader();
    DataWriter writer = new DataWriter();
    TextConverter converter = new TextConverter();

    public WordsFrequencyApp() {
    }

    public void run() {
        File file = new File(getFilePath());

        converter.extractWords(reader.readFile(file));

        wordsCounter.sortCountedWords(converter.getWords());

        writer.saveDataInNewFile(file.getName(), wordsCounter.getSortedWords());

        printTable();
    }

    private void printTable() {
        System.out.println(String.format("%15s %5s %5s", "Item", "|", "Qty"));
        System.out.println(String.format("%s", "-----------------------------"));
        wordsCounter.getSortedWords().entrySet().stream()
                .forEach(entry -> System.out.println(
                        String.format("%15s %5s %5s", entry.getKey(), "|", entry.getValue())));
        System.out.println(ANSI_RESET);
    }

    private String getFilePath() {
        System.out.println("Podaj ścieżkę do pliku: ");
        return scanner.nextLine();
    }
}
