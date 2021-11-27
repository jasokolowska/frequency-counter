import com.patronage.DataReader;
import com.patronage.DataWriter;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WordsFrequencyAppTestSuite {

    static class TestFileReader {

        @Test
        void testReadFile() {
            //Given
            DataReader reader = new DataReader();
            File file = new File("C:\\Development\\Projects\\frequency-counter\\src\\test\\resources\\my_test.txt");

            //When
            String result = reader.readFile(file);

            //Then
            assertEquals(result, "my text ");
        }

        @Test
        void testAbove5MBFile() {
            //Given
            DataReader reader = new DataReader();
            File file = new File("C:\\Development\\Projects\\frequency-counter\\src\\test\\resources\\test_file_5MB.txt");

            //When
            boolean result = reader.validateFileSize(file);

            //Then
            assertFalse(result);
        }
    }





    class TestFileWriter {

        @Test
        void testSaveNewFile() {
            //Given
            DataWriter writer = new DataWriter();
            Map<String, Integer> wordsFrequency = new HashMap<>();
            wordsFrequency.put("word", 5);
            wordsFrequency.put("is", 3);
            wordsFrequency.put("here", 1);

            //When
            writer.saveDataInNewFile("saved_file.txt", wordsFrequency);

            //Then

        }
    }
}
