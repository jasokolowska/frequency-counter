package com.patronage;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordsFrequencyApp {

    private final Scanner scanner = new Scanner(System.in);
    private final WordsCounter wordsCounter = new WordsCounter();
    private final DataReader reader = new DataReader();
    private final TextConverter converter = new TextConverter();
    private String text;

    public WordsFrequencyApp() {
    }

    public void run() {
        boolean quit = false;

        while (!quit) {
            printInstruction();
            String option = scanner.nextLine();
            switch (option) {
                    case "T":
                        if (checkFile()) {
                            saveToTxt(saveFile());
                        }
                        break;
                    case "E":
                        if (checkFile()) {
                            saveToExcel(saveFile());
                        }
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

    private Map<String, Integer> getFileAnalysis() {
        List<String> words = converter.extractWords(text);
        Map<String, Integer> sortedWords = wordsCounter.sortCountedWords(words);
        printTable(sortedWords);
        return sortedWords;
    }

    private void saveToExcel(File fileToSave) {
        DataWriter writer = new DataWriter(fileToSave);
        writer.saveDataInExcel(getFileAnalysis());
    }

    private void saveToTxt(File fileToSave) {
        DataWriter writer = new DataWriter(fileToSave);
        writer.saveDataInTxt(getFileAnalysis());
    }

    private boolean checkFile() {
        File file = getFile();

        if (isFileSizeCorrect(file)) {
            text = reader.readFile(file);
            return true;
        }
        System.out.println("Nieprawidłowy rozmiar pliku");
        return false;
    }

    private void printInstruction() {
        System.out.println("W celu wykonania analizy tekstu wybranego pliku (.txt) " +
                "wpisz odpowiednią literę:\n" +
                "\tT -> Analiza pliku i zapis do pliku tekstowego (.txt)\n" +
                "\tE -> Analiza pliku i zapis do arkusza kalkulacyjnego (.xls/.xlsx)\n" +
                "\tQ -> Wyjście z aplikacji\n" +
                "Wybór: ");
    }

    private void printTable(Map<String, Integer> sortedWords) {
        System.out.printf("%15s %5s %5s%n", "Item", "|", "Qty");
        System.out.printf("%s%n", "-----------------------------");
        sortedWords.forEach((key, value) -> System.out.printf("%15s %5s %5s%n", key, "|", value));
    }

    private File getFile() {
        System.out.println("Podaj ścieżkę do odczytu pliku: ");
        return new File(scanner.nextLine());
    }

    private File saveFile() {
        System.out.println("Podaj ścieżkę do zapisu pliku (wraz z nazwą oraz rozszerzeniem .xls/.xlsx): ");
        return new File(scanner.nextLine());
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
