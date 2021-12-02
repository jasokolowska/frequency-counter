package com.patronage;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.Map;

public class DataWriter {

    private File file;

    public DataWriter(File file) {
        this.file = file;
    }

    public void saveDataInTxt(Map<String, Integer> sortedWords) {
        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);
        ) {
            for (Map.Entry<String, Integer> entry : sortedWords.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku ");
        }
    }

    public void saveDataInExcel(Map<String, Integer> sortedWords) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowNbr = 0;

        for (Map.Entry<String, Integer> entry : sortedWords.entrySet()) {
            Row row = sheet.createRow(rowNbr);
            writeWord(entry, row);
            rowNbr++;
        }
        String filePath = file.getAbsolutePath();
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeWord(Map.Entry<String, Integer> entry, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(entry.getKey());

        cell = row.createCell(1);
        cell.setCellValue(entry.getValue());
    }
}
