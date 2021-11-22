package com.patronage;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class WordsCounter {
    DataReader reader = new DataReader();
    DataWriter writer = new DataWriter();
    private Map<String, Integer> sortedWords = new LinkedHashMap<>();

    public WordsCounter() {
    }

    public Map<String, Integer> countWords(File file) {
        List<String> wordList = getWordList(reader.readFile(file));
        Set<String> uniqueWordsArray = getUniqueWords(wordList);
        Map<String, Integer> wordCounter = getCountedWords(wordList, uniqueWordsArray);

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

    private List<String> getWordList(String text) {
        String[] words = text.split(" ");
        List<String> wordList = new ArrayList<>();
        for (String word : words) {
            String newWord = word
                    .replace(".", "")
                    .replace(",", "")
                    .replace("(", "")
                    .replace(")", "")
                    .replace("–", "")
                    .trim()
                    .toLowerCase();
            if (!word.equals("")) {
                wordList.add(newWord);
            }
        }
        return wordList;
    }
}
