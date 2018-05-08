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

public class PersonsFrame extends JFrame{
	
	private JPanel panel;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JButton addPerson;
	private JButton deletePerson;
	private JButton update;
	private JButton editPerson;
	private Vector<String> colNames = new Vector<String>();
	
	public PersonsFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(900,100,800,600); 
		panel = new JPanel(); 
		panel.setLayout(null); 
		Font arialFont = new Font("Arial", Font.BOLD, 15);
		this.setTitle("Bank");
		table = new JTable(model);
		table.setBounds(180,10,600,530);
		scroll = new JScrollPane(table);
		scroll.setBounds(180,10,600,530);
		panel.add(scroll);
		addPerson = new JButton("Add Person");
		addPerson.setBounds(10,10,140,30);
		panel.add(addPerson);
		deletePerson = new JButton("Delete Person");
		deletePerson.setBounds(10,50,140,30);
		panel.add(deletePerson);
		editPerson = new JButton("Edit Person");
		editPerson.setBounds(10,90,140,30);
		panel.add(editPerson);
		update = new JButton("Update");
		update.setBounds(10,130,140,30);
		panel.add(update);
		
		
		
		this.add(panel);
		this.setVisible(false);
	}
	
	public DefaultTableModel fillTable(Bank bank) {
		//int i = 0;
		colNames.clear();
		HashMap<Person, ArrayList<Account>> map = bank.getBank();
		//Object[][] data = new Object[map.size()][6];
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		colNames.add("Name");
		colNames.add("CNP");
		colNames.add("Accounts");;
		
		for(Map.Entry<Person, ArrayList<Account>> entry : map.entrySet()) {
			ArrayList<Account> list = new ArrayList<Account>();
			list = entry.getValue();
				
				Vector<String> row = new Vector<String>();
				row.add(entry.getKey().getName());
				row.add(entry.getKey().getCnp());
				row.add(Integer.toString(list.size()));
				
				data.add(row);
		}
		return new DefaultTableModel(data, colNames);
	}
	
	public void setTable(DefaultTableModel model) {
		this.table.setModel(model);
	}
	
	public JTable getTable() {
		return this.table;
	}

	public JFrame getFrame() {
		return this;
	}
	
	public void addPressed(ActionListener e) {
		addPerson.addActionListener(e);
	}
	
	public void deletePressed(ActionListener e) {
		deletePerson.addActionListener(e);
	}
	
	public void editPressed(ActionListener e) {
		editPerson.addActionListener(e);
	}
	
	public void update(ActionListener e) {
		update.addActionListener(e);
	}
}
