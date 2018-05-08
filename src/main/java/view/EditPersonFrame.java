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

public class EditPersonFrame extends JFrame{
	
	private JPanel panel;
	private JLabel nameLabel;
	private JLabel cnpLabel;
	private JTextField nameText;
	private JTextField cnpText;
	private JButton modify;
	
	public EditPersonFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(900,100,300,300); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("Add new person");
		nameLabel = new JLabel("New Name: ");
		nameLabel.setBounds(10,10, 100, 30);
		panel.add(nameLabel);
		nameText = new JTextField();
		nameText.setBounds(100,10,100,30);
		panel.add(nameText);
		cnpLabel = new JLabel("New CNP:");
		cnpLabel.setBounds(10,50,100,30);
		panel.add(cnpLabel);
		cnpText = new JTextField();
		cnpText.setBounds(100,50,100,30);
		panel.add(cnpText);
		modify = new JButton("Modify");
		modify.setBounds(10, 90, 100, 30);
		panel.add(modify);
		this.add(panel);
		this.setVisible(false);
	}
	
	public String getName() {
		return nameText.getText();
	}
	
	public String getCNP() {
		return cnpText.getText();
	}
	
	public void modifyPressed(ActionListener e) {
		modify.addActionListener(e);
	}
	
	public JFrame getFrame() {
		return this;
	}

}
