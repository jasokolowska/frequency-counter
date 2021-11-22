package com.patronage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DataWriter {

    public DataWriter() {
    }

    public void saveDataInNewFile(String fileName, Map<String, Integer> sortedWords) {
        try (
                var fileWriter = new FileWriter("counted_" + fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            for (Map.Entry<String, Integer> entry : sortedWords.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }
    }
}
