/**
vlad
May 5, 2018

*/

package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NewAccountFrame extends JFrame{
	
	private JPanel panel;
	private JLabel nameLabel;
	private JLabel cnpLabel;
	private JTextField nameText;
	private JTextField cnpText;
	private JLabel period;
	private JTextField periodText;
	private JRadioButton savingAccount;
	private JRadioButton spendingAccount;
	private JButton addNewAccount;
	private ButtonGroup group;
	
	
	public NewAccountFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100,100,300,300); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("Add new account");
		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(10,10, 100, 30);
		panel.add(nameLabel);
		nameText = new JTextField();
		nameText.setBounds(50,10,100,30);
		panel.add(nameText);
		cnpLabel = new JLabel("CNP:");
		cnpLabel.setBounds(10,50,100,30);
		panel.add(cnpLabel);
		cnpText = new JTextField();
		cnpText.setBounds(50,50,100,30);
		panel.add(cnpText);
		savingAccount = new JRadioButton("Saving Account");
		savingAccount.setBounds(10,120,150,30);
		panel.add(savingAccount);
		spendingAccount = new JRadioButton("Spending Account");
		spendingAccount.setBounds(10, 90, 150 ,30);
		panel.add(spendingAccount);
		group = new ButtonGroup();
		group.add(savingAccount);
		group.add(spendingAccount);
		period = new JLabel("Period: ");
		period.setBounds(10, 160, 100, 30);
		panel.add(period);
		periodText = new JTextField();
		periodText.setBounds(50,160,100,30);
		panel.add(periodText);
		addNewAccount = new JButton("Add");
		addNewAccount.setBounds(10, 200, 100, 30);
		panel.add(addNewAccount);
		this.add(panel);
		this.setVisible(false);
		
	}
	
	public boolean savingAccount() {
		return savingAccount.isSelected();
	}
	
	public boolean spendingAccount() {
		return spendingAccount.isSelected();
	}
	
	public String getName() {
		return nameText.getText();
	}
	
	public String getPeriod() {
		return periodText.getText();
	}
	
	public String getCNP() {
		return cnpText.getText();
	}
	
	public void addPressed(ActionListener e) {
		addNewAccount.addActionListener(e);
	}
	
	public JFrame getNewAccFrame() {
		return this;
	}
}
