package com.patronage;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class WordsFrequencyApp {
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
    }

    private String getFilePath() {
        System.out.println("Podaj ścieżkę do pliku: ");
        return scanner.nextLine();
    }

}
