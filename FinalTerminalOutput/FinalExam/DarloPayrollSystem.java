import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.*;

public class DarloPayrollSystem extends JFrame implements ActionListener {
	
	
	public JTextField eventsDisplay, employeeID, employeeName, hourlyRate, hoursWorked, grossPay, taxRate, taxDeduction, netPay;
	public JTextArea recordDisplay;
	public String previousEmployeeID, previousEmployeeName, previousHourlyRate, previousHoursWorked, previousTaxRate;
	String filename = "PayrollDatafile.txt";
	
	
	public DarloPayrollSystem() {
		
		setTitle("Payroll System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(680, 545);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER)); 
		
		
		JPanel eventsPanel = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel(new BorderLayout());
		JPanel calculationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel outputPanel = new JPanel(new BorderLayout());
		JPanel recordsPanel = new JPanel(new BorderLayout());
		
		
		inputPanel.setBorder(new LineBorder(Color.BLACK, 1));
		outputPanel.setBorder(new LineBorder(Color.BLACK, 1));
		recordsPanel.setBorder(new LineBorder(Color.BLACK, 1));

		
		eventsDisplay = new JTextField("");
		eventsDisplay.setEditable(false);
		eventsDisplay.setHorizontalAlignment(JTextField.CENTER);
		eventsDisplay.setPreferredSize(new Dimension(getWidth() - 25, 25)); 
		
		eventsPanel.add(eventsDisplay);
		add(eventsPanel);
		
		
		JPanel slimPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel textGroup1 = new JPanel(new GridLayout(2,4,0,0));
		JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		String[] inputTexts = {
			"Employee ID", "Employee Name", "Hourly Rate (₱)", "Hours Worked (Daily)"
		};
		
		
		for (String text : inputTexts) {
			JLabel label = new JLabel(text);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			textGroup1.add(label);
		}

		employeeID = new JTextField("");
		employeeName = new JTextField(""); 
		hourlyRate = new JTextField("");
		hoursWorked = new JTextField("");
		
		
		JTextField[] textFields = {
			employeeID, 
			employeeName, 
			hourlyRate, 
			hoursWorked
		};
		
		
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setPreferredSize(new Dimension(150, 25)); 
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			panel.add(textFields[i]);
			textGroup1.add(panel);
		}
		
		
		inputPanel.add(textGroup1, BorderLayout.NORTH);
		inputPanel.add(buttonPanel1, BorderLayout.SOUTH);
		
		
		slimPanel1.add(inputPanel);
		add(slimPanel1);
		
		
		JPanel slimPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton calculateButton = new JButton("Calculate"); 
		calculateButton.addActionListener(this);
		calculateButton.setFocusPainted(false);
		
		
		calculationPanel.add(calculateButton);
		slimPanel2.add(calculationPanel);
		add(slimPanel2);
		
	
		JPanel slimPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel textGroup2 = new JPanel(new GridLayout(2,6));
		JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		String[] inputTexts2 = {
			"Gross Pay (₱)",
			"Tax Rate (%)",
			"Tax Deduction",
			"Net Pay"
		};
		
		
		for (String text : inputTexts2) {
			JLabel label = new JLabel(text);
			label.setHorizontalAlignment(JLabel.CENTER);  
			label.setVerticalAlignment(JLabel.CENTER);   
			textGroup2.add(label);
		}
		
		grossPay = new JTextField("");
		taxRate = new JTextField("");
		taxDeduction = new JTextField("");
		netPay = new JTextField("");
		
		
		JTextField[] textFields2 = {
			grossPay,
			taxRate,
			taxDeduction,
			netPay
		};
		
		
		for (int i = 0; i < textFields2.length; i++) {
			textFields2[i].setPreferredSize(new Dimension(150, 25)); 
			textFields2[i].setHorizontalAlignment(JTextField.CENTER); 
			textFields2[i].setEditable(false);
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			panel.add(textFields2[i]);
			textGroup2.add(panel);
		}
		
		taxRate.setEditable(true);
		
		
		JButton saveEmployeeRecord = new JButton("Save Employee Record");
		saveEmployeeRecord.addActionListener(this);
		saveEmployeeRecord.setFocusPainted(false); 
		buttonPanel2.add(saveEmployeeRecord);
		
		
		outputPanel.add(textGroup2, BorderLayout.NORTH);
		outputPanel.add(buttonPanel2, BorderLayout.SOUTH);
		
		
		slimPanel3.add(outputPanel);
		add(slimPanel3);
		
		recordDisplay = new JTextArea("", 12, 58); 
		recordDisplay.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(recordDisplay);
		recordsPanel.add(scrollPane, BorderLayout.CENTER);
		add(recordsPanel);
		
		
		
		setVisible(true);
		
		
		try {
			refreshData();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		
		
		if (!inputNumberFieldsValid()) {
			return;
		}
		
		switch (command) {
			case "Add/Update Employee Record":
				
				break;
				
			case "Calculate":
				if (!taxRateValid()) {
					return;
				}
				
				
				calculateRecord(Double.parseDouble(hourlyRate.getText()), Double.parseDouble(hoursWorked.getText()));
				eventsDisplay.setText("Calculations Complete");
				break;
				
			case "Save Employee Record":
				if (valuesChanged()) {
					return;
				}
				
				
				if (!taxRateValid()) { 
					return;
				}
				
				if (netPay.getText() == "") { 
					eventsDisplay.setText("Invalid Calculations");
					break;
				}
				
				
				String record = (
						employeeID.getText() + ", " +
						employeeName.getText() + ", " +
						hourlyRate.getText() + ", " +
						hoursWorked.getText() + ", " +
						grossPay.getText() + ", " +
						taxRate.getText() + ", " +
						taxDeduction.getText() + ", " +
						netPay.getText()
				);
				
				eventsDisplay.setText("Employee Record Added/Updated");
				try {
					
					System.out.println("save to file successful");
					recordData(record);
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
				
				break;
		}
	}
	
	public boolean inputNumberFieldsValid() {
		
		JTextField[] inputFields = {employeeID, employeeName, hourlyRate, hoursWorked};
		JTextField[] numberFields = {employeeID, hourlyRate, hoursWorked};
		
		
		for (JTextField inputField : inputFields) {
			if (inputField.getText().length() == 0) {
				eventsDisplay.setText("Empty Field(s)");
				return false;
			}
		}
		
		boolean hasDecimalPoint = false;
		
		for (int i = 0; i < numberFields.length; i++) {
			
			for (char c : numberFields[i].getText().toCharArray()) {
				
				if (Character.isDigit(c)) {
					continue;
				}
				
				
				if (c == '.' && i == 1) {
					
					if (hasDecimalPoint) {
						eventsDisplay.setText("Hourly Rate has more than one decimal point");
						return false;
					}
					else {
						hasDecimalPoint = true;
						continue;
					}
				}
				
				
				switch (i) {
					case 0: 
						eventsDisplay.setText("Invalid Employee ID Input");
						return false;
					
					case 1: 
						eventsDisplay.setText("Invalid Hourly Rate Input");
						return false;
					
					case 2: 
						eventsDisplay.setText("Invalid Hours Worked Input");
						return false;
				}
			}
			
			
			for (char c : employeeName.getText().toCharArray()) {
				
				if (Character.isLetter(c) || c == ' ') {
					continue;
				}
				
				else {
					eventsDisplay.setText("Invalid Employee Name");
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean taxRateValid() { 
		if (taxRate.getText().length() == 0) {
			eventsDisplay.setText("Invalid Tax Rate");
			
			
			taxDeduction.setText("");
			netPay.setText("");
			return false;
		}
		
		boolean hasDecimalPoint = false;
	
		for (char c : taxRate.getText().toCharArray()) {
			
			if (c == '.') {
				
				if (hasDecimalPoint) {
					eventsDisplay.setText("Tax Rate has more than one decimal point");
					return false;
				}
				else {
					hasDecimalPoint = true;
					continue;
				}
			}
		
			else if (Character.isDigit(c)) {
				continue;
			}
			else {
				eventsDisplay.setText("Invalid Tax Rate");

				
				taxDeduction.setText("");
				netPay.setText("");
				return false;
			}
		}
		
		
		double taxRateNum = Double.parseDouble(taxRate.getText());
		if (taxRateNum < 0 || taxRateNum > 100) {
			eventsDisplay.setText("Tax Rate must be between 0 and 100");

			
			taxDeduction.setText("");
			netPay.setText("");
			return false;
		}

		return true;
	}
	
	public void calculateRecord(double hourlyRateParam, double hoursWorkedParam) {
		
		double grossPayNum = hourlyRateParam * hoursWorkedParam;
		grossPay.setText(String.valueOf(grossPayNum));
		
		
		double taxRateDouble = Double.parseDouble(taxRate.getText()) / 100;
		
		double taxRateNum = (grossPayNum * taxRateDouble);
		taxDeduction.setText(String.valueOf(taxRateNum));
		
		
		double netPayNum = grossPayNum - taxRateNum;
		netPay.setText(String.valueOf(netPayNum));
		
		
		previousEmployeeID = employeeID.getText();
		previousEmployeeName = employeeName.getText();
		previousHourlyRate = hourlyRate.getText();
		previousHoursWorked = hoursWorked.getText();
		previousTaxRate = taxRate.getText();
	}
	
	public boolean valuesChanged() {
		
		if (!employeeID.getText().equals(previousEmployeeID)) {
			eventsDisplay.setText("Employee ID changed - repeat calculation.");
			return true;
		}
		else if (!employeeName.getText().equals(previousEmployeeName)) {
			eventsDisplay.setText("Employee Name changed - repeat calculation.");
			return true;
		}
		else if (!hourlyRate.getText().equals(previousHourlyRate)) {
			eventsDisplay.setText("Hourly Rate changed - repeat calculation.");
			return true;
		}
		else if (!hoursWorked.getText().equals(previousHoursWorked)) {
			eventsDisplay.setText("Hours Worked changed - repeat calculation.");
			return true;
		}
		else if (!taxRate.getText().equals(previousTaxRate)) {
			eventsDisplay.setText("Tax Rate changed - repeat calculation.");
			return true;
		}
		
		
		return false;
	}
	
	public void refreshData() throws IOException {
		File file = new File(filename);
		
		if (!file.exists()) {
			return;
		}
		
	
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String line;
		String history = "";
		
		while ((line = reader.readLine()) != null) {
		    history += line + System.lineSeparator();
		}
		
		reader.close();
		recordDisplay.setText("Employee ID, Employee Name, Hourly Rate, Hours Worked, Gross Pay (₱), Tax Rate (%), Tax Deduction, Net Pay\n" + history);
	}
	
	public void recordData(String record) throws IOException {
		String line;
		String records = "";
		String[] splitLineRecord;
		String[] splitNewRecord;
		
		File file = new File(filename);		
		if (file.exists()) {
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			while ((line = reader.readLine()) != null) {
				
				splitLineRecord = line.split(",");
				splitNewRecord = record.split(",");
			
				if (splitLineRecord[0].equals(splitNewRecord[0])) {
					eventsDisplay.setText("Existing ID. Record Updated");
					continue;
				}
				
				records += line + System.lineSeparator();
			}
			
			records += record;
			reader.close();
		}
		else {
			records = record;
		}
		
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(records);
		
		writer.close();
		
		refreshData();
	}
	
	public static void main(String[] args) {
		new DarloPayrollSystem();
	}
}
