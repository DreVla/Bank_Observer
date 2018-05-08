/**
vlad
May 6, 2018

*/

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class BankTest {
	Bank bank = new Bank();
	Person pers1 = new Person("1", "Dreghici");
	Person pers2 = new Person("2", "Russu");
	Person pers3 = new Person("3", "Cata");
	Person pers4 = new Person("4", "Brasoveanu");
	Person pers5 = new Person("5", "Salau");
	Account spendingAccountDre = new SpendingAccount(1, 1000);
	Account savingAccountDre = new SavingAccount(2, 12, 3000);
	Account savingAccountRus = new SavingAccount(3, 10, 4000);
	Account spendingAccountCata = new SpendingAccount(4, 321);
	
	@Test 
	public void testAddAccounts() {
		bank.addAccount(pers1, spendingAccountDre);
		bank.addAccount(pers1, savingAccountDre);
		bank.addAccount(pers2, savingAccountRus);
		bank.addAccount(pers3, spendingAccountCata);
	}
	
	@Test
	public void testRemoveAccount() {
		testAddAccounts();
		bank.removeAccount(pers1, spendingAccountDre);
		bank.removeAccount(pers2, savingAccountRus);
	}
	
	@Test
	public void testAddPerson() {
		bank.addPerson(pers4);
		bank.addPerson(pers5);
	}
	
	@Test 
	public void testRemovePerson() {
		testAddAccounts();
		bank.removePerson(pers1);
	}
	
	@Test
	public void testDepositMoney() {
		testAddAccounts();
		bank.deposit("1", 1, 100);
		bank.deposit("3", 4, 123);
		bank.deposit("2", 3, 1234);
		bank.deposit("1", 2, 1000);
	}
	
	@Test
	public void testWithdrawMoney() {
		testAddAccounts();
		bank.withdraw("1", 1, 20);
		bank.withdraw("3", 4, 10);
		bank.withdraw("2", 3, 3000);
		bank.withdraw("1", 2, 1000);
	}
}
