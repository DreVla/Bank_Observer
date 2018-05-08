/**
vlad
May 5, 2018

*/

package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;
import view.AddMoneyFrame;
import view.AddPersonFrame;
import view.DeleteAccountFrame;
import view.EditPersonFrame;
import view.ExtractMoneyFrame;
import view.Gui;
import view.NewAccountFrame;
import view.PersonsFrame;


public class Controller {
	private Gui gui;
	private PersonsFrame personsFrame;
	private AddPersonFrame addPersonFrame;
	private EditPersonFrame editPersonFrame;
	private NewAccountFrame newAccount;
	private DeleteAccountFrame deleteAccount;
	private AddMoneyFrame addMoney;
	private ExtractMoneyFrame extractMoney;
	private Bank bank;
	private int id = 0;
	private Account acc;
	
	public Controller() {
		gui = new Gui();
		personsFrame = new PersonsFrame();
		addPersonFrame = new AddPersonFrame();
		editPersonFrame = new EditPersonFrame();
		newAccount = new NewAccountFrame();
		deleteAccount = new DeleteAccountFrame();
		addMoney = new AddMoneyFrame();
		extractMoney = new ExtractMoneyFrame();
		bank = new Bank();
		HashMap<Person, ArrayList<Account>> map = bank.getBank();
		bank.readAccounts();
		gui.setTable(gui.fillTable(bank));
		
		// vizualizare persoanelor si operatiile pe ele
		gui.viewPersonsPressed(e->{
			personsFrame.getFrame().setVisible(true);
			bank.readAccounts();
			personsFrame.setTable(personsFrame.fillTable(bank));
		});
		
		personsFrame.update(e->{
			bank.readAccounts();
			personsFrame.setTable(personsFrame.fillTable(bank));
		});
		
		personsFrame.addPressed(e->{
			addPersonFrame.getFrame().setVisible(true);
		});
		
		personsFrame.deletePressed(e->{
			JTable table = personsFrame.getTable();
			if(table.getSelectedRow() != -1) {
				String name = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				String cnp = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				Person pers = new Person(cnp, name);
				System.out.println(pers.toString());
				bank.removePerson(pers);
				bank.writeAccounts();
				bank.readAccounts();
				personsFrame.setTable(personsFrame.fillTable(bank));
			}
			
		});
		
		addPersonFrame.addPressed(e->{
			Person pers = new Person(addPersonFrame.getCNP(), addPersonFrame.getName());
			if(bank.exists(pers) == false) {
				bank.addPerson(pers);
			} else {
				 JOptionPane.showMessageDialog(null,"Person exists","Error",JOptionPane.ERROR_MESSAGE);     

			}
			addPersonFrame.dispose();
			bank.writeAccounts();
			bank.readAccounts();
			personsFrame.setTable(personsFrame.fillTable(bank));
		});
		
		personsFrame.editPressed(e->{
			editPersonFrame.getFrame().setVisible(true);
		});
		
		editPersonFrame.modifyPressed(e->{
			String oldCnp = personsFrame.getTable().getModel().getValueAt(personsFrame.getTable().getSelectedRow(), 1).toString();
			Person pers = new Person(editPersonFrame.getCNP(), editPersonFrame.getName());
			bank.editPerson(oldCnp, pers);
			bank.writeAccounts();
			editPersonFrame.dispose();
			bank.readAccounts();
			personsFrame.setTable(personsFrame.fillTable(bank));
		});
		
		// aici se termina cu persoanele
		gui.addNewAccountPressed(e->{
			newAccount.getNewAccFrame().setVisible(true);
		});
		
		gui.deleteAccountPressed(e->{
			JTable table = gui.getTable();
			if(table.getSelectedRow() != -1) {
				String name = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				String cnp = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				int accId = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
				double money = Double.parseDouble(table.getModel().getValueAt(table.getSelectedColumn(), 5).toString());
				int period = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
				Person pers = new Person(cnp, name);
				Account acc = null;
				if(table.getModel().getValueAt(table.getSelectedColumn(), 3).toString().equals("Saving Account")) {
					acc = new SavingAccount(accId, period, money);
					System.out.println("X1");
				} else {
					acc = new SpendingAccount(accId, money);
					System.out.println("X2");
				}
				
				bank.removeAccount(pers, acc);
				bank.writeAccounts();
				bank.readAccounts();
				gui.setTable(gui.fillTable(bank));
			}
		});
		
		gui.addMoneyPressed(e->{
			addMoney.getFrame().setVisible(true);
		});
		
		gui.extractMoneyPressed(e->{
			extractMoney.getFrame().setVisible(true);
		});
		
		gui.update(e->{
			bank.readAccounts();
			gui.setTable(gui.fillTable(bank));
		});
		
		newAccount.addPressed(e->{
			
			Person pers = new Person(newAccount.getCNP(), newAccount.getName());
			System.out.println(pers.toString());
			if(newAccount.savingAccount() == true) {
				acc = new SavingAccount(id, Integer.parseInt(newAccount.getPeriod()), 0);
				System.out.println("Saving account");
				id++;
			}
			if(newAccount.spendingAccount() == true) {
				acc = new SpendingAccount(id, 0);
				System.out.println("Spending Account");
				id++;
			}
			acc.addObserver(pers);
			bank.addAccount(pers, acc);
			bank.writeAccounts();
			bank.readAccounts();
			gui.setTable(gui.fillTable(bank));
			newAccount.dispose();
		});
		
		
		addMoney.addPressed(e->{
			JTable table = gui.getTable();
			Double amountToAdd = Double.parseDouble(addMoney.getMoney());
			int accId = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
			String cnp = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
			bank.deposit(cnp, accId, amountToAdd);
			bank.writeAccounts();
			addMoney.dispose();
			bank.readAccounts();
			gui.setTable(gui.fillTable(bank));
		});
		
		extractMoney.withdrawPressed(e->{
			JTable table = gui.getTable();
			Double amountToExtract = Double.parseDouble(extractMoney.getMoney());
			int accId = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
			String cnp = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
			System.out.println(amountToExtract);
			System.out.println(accId);
			bank.withdraw(cnp, accId, amountToExtract);
			bank.writeAccounts();
			extractMoney.dispose();
			bank.readAccounts();
			gui.setTable(gui.fillTable(bank));
		});
	}
	
}
