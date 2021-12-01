import com.patronage.DataReader;
import com.patronage.DataWriter;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        class TestFileWriter {

            @Test
            void testSaveNewFile() throws IOException {
                //Given
                FileWriter fileWriterMock = mock(FileWriter.class);

                DataWriter writer = new DataWriter(fileWriterMock);
                Map<String, Integer> wordsFrequency = new HashMap<>();
                wordsFrequency.put("word", 5);
                wordsFrequency.put("is", 3);
                wordsFrequency.put("here", 1);

                //When
                writer.saveDataInTxt(wordsFrequency);

                //Then
            }

        }
    }
}
