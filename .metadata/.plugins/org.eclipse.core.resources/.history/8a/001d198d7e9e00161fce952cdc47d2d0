// Code adapted from Matt Giuca's

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calc extends JFrame {
	public static final int Width = 800;
	public static final int Height = 300;
	
	private JTextField operand;
	private JTextField result;

	// Displays an error message
	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	// This class is for the number buttons. It takes the number on the button,
	// and adds it to the operand text field.
	class InputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = operand.getText();
			// We want to strip leading zeroes
			if (text.equals("0")) {
				text = "";
			}
			operand.setText(text + e.getActionCommand());
		}
	}
	
	// This class is a base class for operation buttons. It grabs the numbers
	// and performs some operation on them, then saves the result.
	abstract class OperatorListener implements ActionListener {
		protected abstract double operate(double x, double y);
		
		@Override
		public void actionPerformed(ActionEvent e) {
			double op, res;
			// Grab the numbers from the text fields
			try {
				op = Double.parseDouble(operand.getText());
			} catch (NumberFormatException ex) {
				showError("Error parsing operand text: not a number");
				operand.setText("0");
				return;
			}
			try {
				res = Double.parseDouble(result.getText());
			} catch (NumberFormatException ex) {
				showError("Error parsing result text: not a number");
				result.setText("0");
				return;
			}
			
			res = operate(res, op);
			operand.setText("0");
			result.setText(Double.toString(res));
		}
	}
	
	// Clears operand
	class ClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			operand.setText("0");			
		}
	}
	
	// Resets both text fields
	class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			operand.setText("0");
			result.setText("0");
		}
	}
	
	public Calc() {
		// Set window settings
		setSize(Width, Height);
		setTitle("Calculator");
		
		// Create the program's main panel
		JPanel panel = new JPanel();
		add(panel);
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(5);
		layout.setVgap(5);
		panel.setLayout(layout);
		
		// Create the inside left panel		
		JPanel leftPanel = new JPanel();
		panel.add(leftPanel);
		layout = new GridLayout(2, 2);
		layout.setHgap(5);
		layout.setVgap(5);
		leftPanel.setLayout(layout);
		
		// Create the inside right panel		
		JPanel rightPanel = new JPanel();
		panel.add(rightPanel);
		layout = new GridLayout(4, 5);
		layout.setHgap(5);
		layout.setVgap(5);
		rightPanel.setLayout(layout);
		
		// Create the text fields
		leftPanel.add(new JLabel("Operand:"));
		operand = new JTextField(5);
		operand.setText("0");
		leftPanel.add(operand);
		
		leftPanel.add(new JLabel("Result:"));
		result = new JTextField(5);
		result.setText("0");
		leftPanel.add(result);
		
		// Create the first row of number buttons
		JButton button = new JButton("7");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton("8");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton("9");
		button.addActionListener(new InputListener());
		// Insert some separators
		rightPanel.add(new JLabel(""));
		rightPanel.add(new JLabel(""));
		
		// Create the next row of number buttons
		rightPanel.add(button);
		button = new JButton("4");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton("5");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton("6");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		
		// Create the first two operator buttons. We do this
		// using anonymous derived classes.
		button = new JButton("*");
		button.addActionListener(new OperatorListener() {
			@Override
			protected double operate(double x, double y) {
				return x * y;
			}
		});
		rightPanel.add(button);
		button = new JButton("/");
		button.addActionListener(new OperatorListener() {
			@Override
			protected double operate(double x, double y) {
				if (y == 0) {
					showError("Division by zero.");
					return 0;
				}
				return x / y;
			}
		});
		rightPanel.add(button);
		
		// Create the next row of number buttons
		button = new JButton("1");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton("2");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton("3");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		
		// Create the final operator buttons
		button = new JButton("+");
		button.addActionListener(new OperatorListener() {
			@Override
			protected double operate(double x, double y) {
				return x + y;
			}
		});
		rightPanel.add(button);
		button = new JButton("-");
		button.addActionListener(new OperatorListener() {
			@Override
			protected double operate(double x, double y) {
				return x - y;
			}
		});
		rightPanel.add(button);
		
		// Finish off the number buttons
		button = new JButton("0");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		button = new JButton(".");
		button.addActionListener(new InputListener());
		rightPanel.add(button);
		
		// Add another separator
		rightPanel.add(new JLabel(""));
		
		// Create buttons to clear and reset
		button = new JButton("CLR");
		button.addActionListener(new ClearListener());
		rightPanel.add(button);
		button = new JButton("RE");
		button.addActionListener(new ResetListener());
		rightPanel.add(button);
	}
	
	public static void main(String[] args) {
		Calc calc = new Calc();
		calc.setVisible(true);
	}
}