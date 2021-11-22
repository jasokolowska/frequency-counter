package com.patronage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Development\\Projects\\frequency-counter\\src\\main\\resources\\intive_patronage.txt");

        DataReader reader = new DataReader();
        String text = reader.readFile(file);

        WordsCounter wordsCounter = new WordsCounter();
        wordsCounter.countWords(text);
    }

}
