// Code adapted from Matt Giuca's(

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalc extends JFrame {
	public static final int Width = 300;
	public static final int Height = 225;
	
	private TextField field;
	private double total;
	
	class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			if (command.equals("Reset")) {
				total = 0;
				field.setText("0.00");
			} else {
				try {
					double x = validate(field.getText());
					if (command.equals("+")) {
						total += x;
					} else {
						total -= x;
					}
					field.setText(Double.toString(total));
				} catch (NumberFormatException e) {
					field.setText("Invalid number");
				}
			}
		}

		private double validate(String s) {
			double x = Double.parseDouble(s.trim());
			return x;
		}
	}
	
	public SimpleCalc() {
		setSize(Width, Height);
		setTitle("Simple Calculator");
		setLayout(new BorderLayout());
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.LIGHT_GRAY);
		
		Font buttonFont = new Font("Arial", Font.PLAIN, 20);
		
		field = new TextField(20);
		field.setFont(buttonFont);
		textPanel.add(field);
		add(textPanel, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		Listener listener = new Listener();
		
		JButton plus = new JButton("+");
		plus.setBackground(Color.CYAN);
		plus.setPreferredSize(new Dimension(120, 50));
		plus.addActionListener(listener);
		plus.setFont(buttonFont);
		buttonPanel.add(plus);

		JButton minus = new JButton("-");
		minus.setBackground(Color.YELLOW);
		minus.setPreferredSize(new Dimension(120, 50));
		minus.addActionListener(listener);
		minus.setFont(buttonFont);
		buttonPanel.add(minus);

		JButton reset = new JButton("Reset");
		reset.setBackground(Color.MAGENTA);
		reset.setPreferredSize(new Dimension(120, 50));
		reset.addActionListener(listener);
		reset.setFont(buttonFont);
		buttonPanel.add(reset);
		
		add(buttonPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new SimpleCalc().setVisible(true);
	}
}
