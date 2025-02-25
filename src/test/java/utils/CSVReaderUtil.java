package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {

    public static List<String[]> getTestData(String filePath) {
        List<String[]> testData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            testData = reader.readAll();
            testData.remove(0); // Remove header row
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return testData;
    }
}
