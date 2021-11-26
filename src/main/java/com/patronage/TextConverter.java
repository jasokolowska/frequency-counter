package com.patronage;

import java.util.*;

public class TextConverter {
    private List<String> words = new LinkedList<>();
    Map<String, Integer> vocabulary = new HashMap<>();

    public TextConverter() {
    }

    public void extractWords(String text) {
        String[] words = text.split(" ");

        Arrays.stream(words)
                .map(word -> word.toLowerCase().replaceAll("[\\W]", ""))
                .filter(word -> !word.equals(""))
                .forEach(word -> this.words.add(word));
    }

    public List<String> getWords() {
        return words;
    }
}
