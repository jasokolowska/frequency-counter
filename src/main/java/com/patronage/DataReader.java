package com.patronage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    public DataReader() {
    }

    public String readFile(File file) {

            StringBuilder stringBuilder = new StringBuilder();

            try (
                    FileReader fileReader = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fileReader);
            ) {
                String nextLine = null;
                while ((nextLine = reader.readLine()) != null) {
                    stringBuilder.append(nextLine + " ");
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
    }

    public boolean validateFileSize(File file) {
        if (getFileSizeMegaBytes(file) < 5) {
            return true;
        }
        return false;
    }

    private double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }

}
