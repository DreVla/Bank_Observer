/**
vlad
May 5, 2018

*/

package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExtractMoneyFrame extends JFrame{

	private JPanel panel;
	private JButton withdraw;
	private JLabel amount;
	private JTextField amountText;
	
	public ExtractMoneyFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100,100,300,300); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("Add money");
	
		amount = new JLabel("Amount: ");
		amount.setBounds(10,10,100,30);
		panel.add(amount);
		amountText = new JTextField();
		amountText.setBounds(80,10,100,30);
		panel.add(amountText);
		withdraw = new JButton("Withdraw money");
		withdraw.setBounds(10,50,150,30);
		panel.add(withdraw);
		
		this.add(panel);
		this.setVisible(false);
	}
	
	public String getMoney() {
		return amountText.getText();
	}
	
	public void withdrawPressed(ActionListener e) {
		withdraw.addActionListener(e);
	}
	
	public JFrame getFrame() {
		return this;
	}
}

