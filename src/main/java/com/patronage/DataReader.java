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
            convertToString(stringBuilder, reader);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private void convertToString(StringBuilder stringBuilder, BufferedReader reader) throws IOException {
        String nextLine = null;
        while ((nextLine = reader.readLine()) != null) {
            stringBuilder.append(nextLine + " ");
        }
    }


}
