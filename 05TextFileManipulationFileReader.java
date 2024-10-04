import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class TextFileManipulationFileReader {
    public static void main(String[] args) {
        
        String inputFilePath = "input.txt";  
        String outputFilePath = "output.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
         
            while ((line = reader.readLine()) != null) {
         
                String manipulatedLine = line.toLowerCase();
                
                writer.write(manipulatedLine);
                writer.newLine(); 
            }

            System.out.println("File manipulation complete. Check output.txt.");
        } catch (IOException e) {
            System.err.println("Error during file operations: " + e.getMessage());
        }
    }
}
