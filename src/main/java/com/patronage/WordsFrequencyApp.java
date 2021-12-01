package com.patronage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordsFrequencyApp {

    private Scanner scanner = new Scanner(System.in);
    private WordsCounter wordsCounter = new WordsCounter();
    private DataReader reader = new DataReader();
    private DataWriter writer;
    private TextConverter converter = new TextConverter();
    private String text;
    private File file;

    public WordsFrequencyApp() {
    }

    public void run() {
        boolean quit = false;

        while (!quit) {
            printInstruction();
            String option = scanner.nextLine();

            switch (option) {
                case "T":
                    fileAnalysis();
                    break;
                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, spróbuj jeszcze raz.");
            }
        }
        System.out.println("Do widzenia!");
    }

    private void fileAnalysis() {
        if (checkFile()) {
            List<String> words = converter.extractWords(text);
            Map<String, Integer> sortedWords = wordsCounter.sortCountedWords(words);
            saveToFile(sortedWords);
            printTable(sortedWords);
        } else {
            System.out.println("Nieprawdiłowy rozmiar pliku");
        }
    }

    private void saveToFile(Map<String, Integer> sortedWords) {
        try (
                FileWriter fileWriter = new FileWriter("counted_" + file.getName())
        ){
            writer = new DataWriter(fileWriter);
            writer.saveDataInTxt(sortedWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkFile() {
        file = new File(getFilePath());

        if (isFileSizeCorrect(file)) {
            text = reader.readFile(file);
            return true;
        }
        return false;
    }

    private void printInstruction() {
        System.out.println("W celu wykonania analizy tekstu wybranego pliku (.txt) " +
                "wpisz odpowiednią literę:\n" +
                "\tT -> Analiza pliku\n" +
                "\tQ -> Wyjście z aplikacji\n" +
                "Wybór: ");
    }

    private void printTable(Map<String, Integer> sortedWords) {
        System.out.println(String.format("%15s %5s %5s", "Item", "|", "Qty"));
        System.out.println(String.format("%s", "-----------------------------"));
        sortedWords.entrySet().stream()
                .forEach(entry -> System.out.println(
                        String.format("%15s %5s %5s", entry.getKey(), "|", entry.getValue())));
    }

    private String getFilePath() {
        System.out.println("Podaj ścieżkę do pliku: ");
        return scanner.nextLine();
    }

    public boolean isFileSizeCorrect(File file) {
        if (getFileSizeMegaBytes(file) < 5) {
            return true;
        }
        return false;
    }

    private double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }
}
