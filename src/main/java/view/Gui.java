/**
vlad
May 5, 2018

*/

package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bank;
import model.Person;

public class Gui extends JFrame{
	
	private JPanel panel;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JButton viewPersons;
	private JButton addNewAccount;
	private JButton deleteAccount;
	private JButton update;
	private JButton addMoney;
	private JButton extractMoney;
	private Vector<String> colNames = new Vector<String>();
	
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100,100,800,600); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("Bank");
		table = new JTable(model);
		table.setBounds(180,10,600,530);
		scroll = new JScrollPane(table);
		scroll.setBounds(180,10,600,530);
		panel.add(scroll);
		viewPersons = new JButton("View Persons");
		viewPersons.setBounds(10,10,140,30);
		panel.add(viewPersons);
		addNewAccount = new JButton("Add new account");
		addNewAccount.setBounds(10,50,140,30);
		panel.add(addNewAccount);
		deleteAccount = new JButton("Delete Account");
		deleteAccount.setBounds(10,90,140,30);
		panel.add(deleteAccount);
		addMoney = new JButton("Add money");
		addMoney.setBounds(10,130,140,30);
		panel.add(addMoney);
		extractMoney = new JButton("Extract money");
		extractMoney.setBounds(10,170,140,30);
		panel.add(extractMoney);
		
		
		update = new JButton("Update accounts");
		update.setBounds(10, 210, 140, 30);
		panel.add(update);
		
		
		
		this.add(panel);
		this.setVisible(true);
	}
	
	public DefaultTableModel fillTable(Bank bank) {
		//int i = 0;
		colNames.clear();
		HashMap<Person, ArrayList<Account>> map = bank.getBank();
		//Object[][] data = new Object[map.size()][6];
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		colNames.add("Name");
		colNames.add("CNP");
		colNames.add("Account ID");
		colNames.add("Type");
		colNames.add("Period");
		colNames.add("Money");
		colNames.add("Interest");
		
		for(Map.Entry<Person, ArrayList<Account>> entry : map.entrySet()) {
			ArrayList<Account> list = new ArrayList<Account>();
			list = entry.getValue();
			for(int i = 0 ; i < list.size(); i++) {
				Person pers = entry.getKey();
				Vector<String> row = new Vector<String>();
				row.add(pers.getName());
				row.add(pers.getCnp());
				row.add(Integer.toString(list.get(i).getId()));
				row.add(list.get(i).getAccName());
				row.add(Integer.toString(list.get(i).getPeriod()));
				row.add(Double.toString(list.get(i).getMoney()));
				row.add(Double.toString(list.get(i).getInterest()));
				data.add(row);
			}	
		}
		return new DefaultTableModel(data, colNames);
	}
	
	public JTable getTable() {
		return this.table;
	}
	
	public void setTable(DefaultTableModel model) {
		this.table.setModel(model);
	}

	public JFrame getGuiFrame() {
		return this;
	}
	
	public void viewPersonsPressed(ActionListener e) {
		viewPersons.addActionListener(e);
	}
	
	public void addNewAccountPressed(ActionListener e) {
		addNewAccount.addActionListener(e);
	}
	
	public void deleteAccountPressed(ActionListener e) {
		deleteAccount.addActionListener(e);
	}
	
	public void addMoneyPressed(ActionListener e) {
		addMoney.addActionListener(e);
	}
	
	public void extractMoneyPressed(ActionListener e) {
		extractMoney.addActionListener(e);
	}
	
	public void update(ActionListener e) {
		update.addActionListener(e);
	}
}
