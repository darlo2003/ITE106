import java.util.Scanner;
import java.util.InputMismatchException;

public class StudentGradeCalculator {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int studentCount; // used to keep track of how many students there are 
		int currentStudent = 0; // used to keep track of which student the following information is for/from
		
		System.out.println("How many students are in the class?");
		studentCount = IntInput(false); // use method to avoid error
		input.nextLine(); // to clear the newline character left in the buffer
		
		// create arrays to hold student information which size is equal to the number of students
		String[] name = new String[studentCount];
		char[] letterGrade = new char[studentCount];
		int[] quiz1Grade = new int[studentCount];
		int[] quiz2Grade = new int[studentCount];
		int[] homework1Grade = new int[studentCount];
		int[] homework2Grade = new int[studentCount];
		int[] examGrade = new int[studentCount];
		int[] averageGrade = new int[studentCount];
		
		// take inputs name, quiz 1 and 2 grade, homework 1 and 2 grade, and exam grade
		for (; currentStudent < studentCount; currentStudent++) {
			System.out.printf("Student %d Name? ", currentStudent + 1);
			name[currentStudent] = StringInput();
			
			System.out.printf("%s Quiz 1 Grade: ", name[currentStudent]);
			quiz1Grade[currentStudent] = IntInput(true);
			System.out.printf("%s Quiz 2 Grade: ", name[currentStudent]);
			quiz2Grade[currentStudent] = IntInput(true);
			
			System.out.printf("%s Homework 1 Grade: ", name[currentStudent]);
			homework1Grade[currentStudent] = IntInput(true);
			System.out.printf("%s Homework 2 Grade: ", name[currentStudent]);
			homework2Grade[currentStudent] = IntInput(true);
			
			System.out.printf("%s Exam Grade: ", name[currentStudent]);
			examGrade[currentStudent] = IntInput(true);
			input.nextLine();
			
			averageGrade[currentStudent] = (
					quiz1Grade[currentStudent] +
					quiz2Grade[currentStudent] +
					homework1Grade[currentStudent] +
					homework2Grade[currentStudent] +
					examGrade[currentStudent]
					) / 5;
			
			if (averageGrade[currentStudent] > 94) {
				letterGrade[currentStudent] = 'A';
			}
			else if (averageGrade[currentStudent] > 89) {
				letterGrade[currentStudent] = 'B';
			}
			else if (averageGrade[currentStudent] > 84) {
				letterGrade[currentStudent] = 'C';
			}
			else if (averageGrade[currentStudent] > 79) {
				letterGrade[currentStudent] = 'D';
			}
			else if (averageGrade[currentStudent] > 74) {
				letterGrade[currentStudent] = 'E';
			}
			else {
				letterGrade[currentStudent] = 'F';
			}
		}
		
		for (currentStudent = 0; currentStudent < studentCount; currentStudent++) {
			System.out.printf("%s - AVERAGE GRADE: %d - %s\n", name[currentStudent], averageGrade[currentStudent], letterGrade[currentStudent]);
		}
	}
	
	public static int IntInput(boolean isGrade) {
		int numberInput; // return value
		
		while (true) {
			try {
				numberInput = input.nextInt();
				if (numberInput <= 0) { // invalid input
					System.out.println("Invalid Input - Zero or Negative");
				}
				else if (isGrade && (numberInput < 60 || numberInput > 100)) { // invalid input
					System.out.println("Invalid Input - Grade must be (60 - 100)");
				}
				else { // valid input
					return numberInput;
				}
			}
			catch (InputMismatchException e) { // invalid input
				// e.printStackTrace(); // Prints the exception details to the console
				System.out.println("Invalid Input - Non-Numeric");
			}
			input.nextLine();
		}
	}
	
	public static String StringInput() {
		String nameInput; // return value
		
		while (true) {
			nameInput = input.nextLine();
			boolean validInput = true;
			
			/* toCharArray, a method of the String Class
			 * Converts the String input into character array
			 * Iterate on the character array using 'c' to traverse
			 */
			for (char c : nameInput.toCharArray()) {
				if (!Character.isLetter(c)) { // check if current character is NOT a letter
					System.out.println("Invalid Input - Numeric Character");
					validInput = false; // to not return a value and repeat the loop
					break;
				}
			}
			if (validInput) { // stops loop and returns valid input
				return nameInput;
			}
		}
	}
}
