package com.patronage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextConverter {
    private List<String> words = new LinkedList<>();

    public TextConverter() {
    }

    public void extractWords(String text) {
        String[] words = text.split(" ");

        Arrays.stream(words)
                .filter(word -> word.length() > 1)
                .forEach(word -> this.words.add(word.toLowerCase()));
    }

    public List<String> getWords() {
        return words;
    }
}
