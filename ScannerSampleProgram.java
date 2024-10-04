import java.util.Scanner;

public class ScannerSampleProgram
{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter your age: ");
    int age = input.nextInt();
    System.out.println("Your age is: " + age);
    
    System.out.print("Enter your name: ");
    String age = input.next();
    System.out.println("Your name is: " + name);
  }
}
