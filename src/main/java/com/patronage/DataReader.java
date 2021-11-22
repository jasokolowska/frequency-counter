package com.patronage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    public DataReader() {
    }

    public String readFile(File file) {
        FileReader fileReader = null;
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String nextLine = null;
            int lines = 0;
            while ((nextLine = reader.readLine()) != null) {
                stringBuffer.append(" " + nextLine);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

}
