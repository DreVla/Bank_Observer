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

public class DeleteAccountFrame extends JFrame {

	private JPanel panel;
	private JButton delete;
	private JLabel cnpLabel;
	private JTextField cnpText;
	private JLabel accountIdLabel;
	private JTextField accountIdText;
	
	public DeleteAccountFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100,100,300,300); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("Delete account");
		cnpLabel = new JLabel("CNP: ");
		cnpLabel.setBounds(10,10,120,30);
		panel.add(cnpLabel);
		cnpText = new JTextField();
		cnpText.setBounds(100,10,100,30);
		panel.add(cnpText);
		accountIdLabel = new JLabel("Account ID: ");
		accountIdLabel.setBounds(10,50,120,30);
		panel.add(accountIdLabel);
		accountIdText = new JTextField();
		accountIdText.setBounds(100, 50, 100, 30);
		panel.add(accountIdText);
		delete = new JButton("Delete");
		delete.setBounds(10, 90, 100, 30);
		panel.add(delete);
		
		this.add(panel);
		this.setVisible(false);
		
	}
	
	public String getId() {
		return accountIdText.getText();
	}
	
	public String getCnp() {
		return cnpText.getText();
	}
	
	public void deletePressed(ActionListener e) {
		delete.addActionListener(e);
	}
	
	public JFrame getFrame() {
		return this;
	}
}
