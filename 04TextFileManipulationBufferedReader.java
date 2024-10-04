import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileManipulationBufferedReader {
    public static void main(String[] args) {

        String inputFilePath = "input.txt";  
        String outputFilePath = "output.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            
            String line;
            StringBuilder manipulatedContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                
                String manipulatedLine = new StringBuilder(line).reverse().toString();
                manipulatedContent.append(manipulatedLine).append(System.lineSeparator());
            }

            writer.write(manipulatedContent.toString());
            System.out.println("File manipulation complete. Check output.txt.");
        } catch (IOException e) {
            System.err.println("Error during file operations: " + e.getMessage());
        }
    }
}
