package com.patronage;

import java.util.*;
import java.util.stream.Collectors;

public class WordsCounter {

    private Map<String, Integer> sortedWords = new LinkedHashMap<>();

    public WordsCounter() {
    }

    public Map<String, Integer> sortCountedWords(List<String> words) {
        Map<String, Integer> wordCounter = getCountedWords(words);

        return wordCounter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, Integer> getCountedWords(List<String> words) {
        Map<String, Integer> vocabulary = new HashMap<>();

        words.forEach(word -> vocabulary.put(word, vocabulary.getOrDefault(word, 0) + 1));

        return vocabulary;
    }

    public Map<String, Integer> getSortedWords() {
        return sortedWords;
    }
}
