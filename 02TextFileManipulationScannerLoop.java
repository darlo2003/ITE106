import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFileManipulationScannerLoop {
    public static void main(String[] args) {
        String inputFilePath = "input.txt"; 
        String outputFilePath = "output.txt"; 

        try {
            
            Scanner scanner = new Scanner(new File(inputFilePath));
            FileWriter writer = new FileWriter(outputFilePath);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                String manipulatedLine = line.toUpperCase();
                
                writer.write(manipulatedLine + System.lineSeparator());
            }

            scanner.close();
            writer.close();
            System.out.println("File manipulation complete. Check output.txt.");
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}
