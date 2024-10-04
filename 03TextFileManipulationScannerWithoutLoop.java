import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFileManipulationScannerWithoutLoop {
    public static void main(String[] args) {
      
        String inputFilePath = "input.txt";  
        String outputFilePath = "output.txt"; 

        try {
        
            Scanner scanner = new Scanner(new File(inputFilePath));
            StringBuilder fileContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append(System.lineSeparator());
            }

            scanner.close();

            String manipulatedContent = fileContent.toString().toUpperCase();

            FileWriter writer = new FileWriter(outputFilePath);
            writer.write(manipulatedContent);
            writer.close();

            System.out.println("File manipulation complete. Check output.txt.");
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}
