package com.patronage;

import java.util.*;
import java.util.stream.Collectors;

public class TextConverter {

    public TextConverter() {
    }

    public List<String> extractWords(String text) {
        String[] words = text.split(" ");

        return Arrays.stream(words)
                .map(word -> word.toLowerCase().replaceAll("[\\W]", ""))
                .filter(word -> !word.equals(""))
                .collect(Collectors.toList());
    }
}
