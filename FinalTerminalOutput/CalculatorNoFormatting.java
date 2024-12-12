import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CalculatorNiRodNoFormatting extends JFrame implements ActionListener {
	
	//===================Text Displays======================//
	private JTextField textDisplay;
	private JTextArea historyDisplay;
	//===================Functionality Variables======================//
	private double input1, input2, resultingValue;
	private String operator;
	private boolean done;
	
	public CalculatorNiRodNoFormatting() {
		//===================GUI Main Frame======================//
		setTitle("Kalkyuleytor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLayout(new BorderLayout());
		//===================GUI Upper Text Display======================//
		textDisplay = new JTextField();
		textDisplay.setEditable(false);
		add(textDisplay, BorderLayout.NORTH);
		//===================GUI Buttons ======================//
		JPanel buttonGroup = new JPanel();
		buttonGroup.setLayout(new GridLayout(5, 4));
		
		String[] buttons = {
			"7", "8", "9", "/",
			"4", "5", "6", "*",
			"1", "2", "3", "-",
			"0", ".", "=", "+",
			"C"
		};
		
		for (String button : buttons) {
			JButton b = new JButton(button);
			b.addActionListener(this);
			buttonGroup.add(b);
		}
		
		add(buttonGroup, BorderLayout.CENTER);
		//===================GUI Left History Text Area======================//
		JPanel history = new JPanel();
		history.setLayout(new BorderLayout());
		
		historyDisplay = new JTextArea(10, 20);
		historyDisplay.setEditable(false);
		historyDisplay.setText("History:\n");
	    
		JScrollPane scrollPane = new JScrollPane(historyDisplay);
		history.add(scrollPane, BorderLayout.CENTER);
        
		add(history, BorderLayout.WEST);
		//===================GUI Frame Visibility======================//
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String calcuInput = e.getActionCommand();
		
		//===================If There is No Input And The First Input is 0======================//
		if (textDisplay.getText().length() <= 0 && calcuInput.equals("0")) {
			return;
		}
		
		//===================Reset Text Area And Values of Input1 And Input2 Variables======================//
		if (calcuInput.equals("C")) {
			input1 = 0;
			input2 = 0;
			textDisplay.setText("");
		}
		//===================If Input is Digit or Dot (.)======================//
		else if (Character.isDigit(calcuInput.charAt(0)) || calcuInput.equals(".")) {
			//===================To Avoid Writing The Next Input Into The Previous Output======================//
			if (done) {
				textDisplay.setText("");
				done = false;
			}
			
			//===================If input is Dot (.) but there is already a Dot======================//
			if (calcuInput.equals(".") && textDisplay.getText().contains(".")) {
				return;
			}
			
			//===================Add the NEW Number or Dot (.) Input Into The Previous======================//
			textDisplay.setText(textDisplay.getText() + calcuInput);
		}
		//===================If Input Is Equal Sign (=)======================//
		else if (calcuInput.equals("=")) {
			input2 = Double.parseDouble(textDisplay.getText());
			calculate();
			textDisplay.setText(String.valueOf(resultingValue));
			
			done = true;
			
			//===================Add to History======================//
			try {
				String history = historyRecorder(
						String.valueOf(input1) + " " +
						operator + " " +
						String.valueOf(input2) + " = " +
						String.valueOf(resultingValue)
						);
				historyDisplay.setText("History:\n" + history);
			}
			catch (IOException e1) {
				e1.printStackTrace();																																																																																																																							                                                                                                                                                                                                                                                                                                                                                          			// Gawa ni Mark Vincent D. Lanada BSCS2A
			}
		}
		//===================If Input is NOT A Digit, Dot (.) or Equal Sign (=)======================//
		else {
			operator = calcuInput;
			input1 = Double.parseDouble(textDisplay.getText());
			textDisplay.setText("");
			
			done = false;
		}
	}
	
	public void calculate() {
		switch (operator) {
			case "+":
				resultingValue = input1 + input2;
				break;
			case "-":
				resultingValue = input1 - input2;
				break;
			case "*":
				resultingValue = input1 * input2;
				break;
			case "/":
				resultingValue = input1 / input2;
				break;
		}
	}

	public String historyRecorder(String record) throws IOException {
		//===================Reading FROM File======================//
		BufferedReader reader = new BufferedReader(new FileReader("calculator_history.txt"));
		
		String line;
		String history = "";
		
		while ((line = reader.readLine()) != null) {
		    history += line + System.lineSeparator();
		}
		
		history += record;
		
		//===================Writing INTO File======================//
		BufferedWriter writer = new BufferedWriter(new FileWriter("calculator_history.txt"));
		writer.write(history);
		
		reader.close();
		writer.close();
		
		return history;
	}
	
	public static void main(String[] args) throws IOException {
		//===================To Clear The Contents of The History File======================//
		BufferedWriter writer = new BufferedWriter(new FileWriter("calculator_history.txt"));
		writer.write("");
		writer.close();
		
		new CalculatorNiRodNoFormatting();
	}
}
