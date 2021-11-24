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
        List<String> words = converter.getWords();
        Set<String> uniqueWordsArray = getUniqueWords(words);
        Map<String, Integer> wordCounter = getCountedWords(words, uniqueWordsArray);

        writer.saveDataInNewFile(file.getName(), sortCountedWords(wordCounter));

        return sortCountedWords(wordCounter);
    }

    private Map<String, Integer> sortCountedWords(Map<String, Integer> wordCounter) {
        return wordCounter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, Integer> getCountedWords(List<String> wordList, Set<String> uniqueWordsArray) {
        Map<String, Integer> wordCounter = new HashMap<>();

        for (String s : uniqueWordsArray) {
            int counter = 0;
            for (String s1 : wordList) {
                if (s.equals(s1)) {
                    counter++;
                }
            }
            wordCounter.put(s, counter);
        }
        return wordCounter;
    }

    private Set<String> getUniqueWords(List<String> wordList) {
        Set<String> uniqueWordsArray = new HashSet<>();
        for (String word : wordList) {
            uniqueWordsArray.add(word);
        }
        return uniqueWordsArray;
    }
}
