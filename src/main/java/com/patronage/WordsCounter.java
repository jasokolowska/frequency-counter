package com.patronage;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class WordsCounter {
    DataReader reader = new DataReader();
    DataWriter writer = new DataWriter();
    TextConverter converter = new TextConverter();
    private Map<String, Integer> sortedWords = new LinkedHashMap<>();

    public WordsCounter() {
    }

    public Map<String, Integer> countWords(File file) {
        converter.extractWords(reader.readFile(file));

        Map<String, Integer> wordCounter = getCountedWords();

        writer.saveDataInNewFile(file.getName(), sortCountedWords(wordCounter));

        return sortCountedWords(wordCounter);
    }

    private Map<String, Integer> sortCountedWords(Map<String, Integer> wordCounter) {
        return wordCounter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, Integer> getCountedWords() {
        Map<String, Integer> vocabulary = new HashMap<>();

        converter.getWords().stream()
                .forEach(word -> vocabulary.put(word, vocabulary.getOrDefault(word, 0) + 1));

        return vocabulary;
    }
}
