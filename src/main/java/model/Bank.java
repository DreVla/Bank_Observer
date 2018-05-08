/**
vlad
May 3, 2018

*/

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bank implements BankProc, java.io.Serializable {
	
	protected HashMap <Person, ArrayList<Account>> bank;
	
	public Bank(){
		bank = new HashMap<Person, ArrayList<Account>>();
	}
	
	@Override
	public void readAccounts() {
		
		ObjectInputStream input;
		try {
			FileInputStream file = new FileInputStream("F:\\Work\\Facultate\\anu2\\sem2\\PT2018\\pt2018_30422_dreghici_popa_vlad_assignment_4\\src\\main\\java\\model\\data.ser");
			input = new ObjectInputStream(file);
			bank = (HashMap<Person, ArrayList<Account>>) input.readObject();
			input.close();
			file.close();
			System.out.println("Deserialized");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeAccounts() {
		
		FileOutputStream file;
		ObjectOutputStream output;
		try {
			file = new FileOutputStream("F:\\Work\\Facultate\\anu2\\sem2\\PT2018\\pt2018_30422_dreghici_popa_vlad_assignment_4\\src\\main\\java\\model\\data.ser");
			output = new ObjectOutputStream(file);
			output.writeObject(bank);
			output.close();
			file.close();
			System.out.println("Serialized");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAccount(Person pers, Account acc) {
		
		assert isWellFormed();
		assert acc.isWellFormed();
		assert pers.isWellFormed();
		
		int accounts = 0;
		int newSize = 1;
		boolean check = false;
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(pers.getCnp())
					&& entry.getKey().getName().equals(pers.getName())) {
				check = true;
				accounts = entry.getValue().size();
				entry.getValue().add(acc);
				newSize = entry.getValue().size();
			}
		}
		if(check == false){
			ArrayList<Account> list = new ArrayList<Account>();
			list.add(acc);
			bank.put(pers, list);
		}
		
		acc.addObserver(pers); // add observer for the account
		
		assert accounts + 1 == newSize;
	}

	@Override
	public void removeAccount(Person pers, Account acc) {
		
		assert isWellFormed();
		assert acc.isWellFormed();
		assert pers.isWellFormed();
		assert containsPerson(pers);
		
		int oldSize = 0;
		int newSize = 0;
		boolean check = false;
		ArrayList<Account> list = new ArrayList<Account>();
		Account toRemove = null;
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(pers.getCnp())
					&& entry.getKey().getName().equals(pers.getName())) {
				System.out.println(entry.getKey().toString());
				list = entry.getValue();
				for(Account a: list) {
					if(a.getId() == acc.getId()) {
						System.out.println(acc.toString());
						toRemove = a;
						check = true;	
					}
				}
			}
		}
		if(check == false) {
			System.out.println("Account does not exist");
		} else {
			oldSize = list.size();
			list.remove(toRemove);
			newSize = list.size();
		}
		System.out.println("Old size " + oldSize + " New size " + newSize);
		
		assert oldSize - 1 == newSize;
	}
	
	public Account getAccount(int id, String cnp) {
		HashMap<Person, ArrayList<Account>> map = bank;
		Account acc = null;
		for(Map.Entry<Person, ArrayList<Account>> entry : map.entrySet()) {
			if(entry.getKey().getCnp().equals(cnp)) {
				ArrayList<Account> list = new ArrayList<Account>();
				list = entry.getValue();
				for(int i = 0 ; i < list.size(); i++) {
					if(id == list.get(i).getId()) {
						acc = list.get(i);
					}
				}
			}
		}
		acc.isWellFormed();
		return acc;
	}
	
	@Override
	public void deposit(String cnp, int id, double money) {
		
		assert isWellFormed();
		assert cnp!=null;
		assert id >= 0;
		assert money > 0;
		
		double oldAmount = 0;
		Account accToDeposit = null;
		Person persToDepost = null;
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(cnp)) {
				ArrayList<Account> list = new ArrayList<Account>();
				list = entry.getValue();
				//System.out.println("X");
				for(Account a : list) {
					if(a.getId() == id) {
						persToDepost = entry.getKey();
						oldAmount = a.getMoney();
						accToDeposit = a;
					}
				}
			}
		}
		accToDeposit.depositMoney(money);
		//accToDeposit.notifyObservers(accToDeposit);
		if(accToDeposit.getAccName().equals("Spending account")){
			assert oldAmount + money == accToDeposit.getMoney();
		} else if (accToDeposit.getAccName().equals("Saving Account")) {
			assert oldAmount + money + accToDeposit.getPeriod()*(accToDeposit.getInterest()/100)*money == accToDeposit.getMoney();
		}
	}
	
	@Override
	public void withdraw(String cnp, int id, double money) {
		
		assert isWellFormed();
		assert cnp!=null;
		assert id >= 0;
		assert money > 0;
		
		double oldAmount = 0;
		Account acc = null;
		double check = 0;
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(cnp)) {
				ArrayList<Account> list = new ArrayList<Account>();
				list = entry.getValue();
				Iterator<Account> it = list.iterator();
				while(it.hasNext()) {
					Account aux = it.next();
					if(aux.getId() == id) {
						acc = aux;
						oldAmount = aux.getMoney();
						check = aux.extractMoney(money);	
					}
				}
			}
		}
		
		assert oldAmount - money == acc.getMoney();
		
	}

	@Override
	public void addPerson(Person pers) {
		
		assert isWellFormed();
		assert pers.isWellFormed();
		assert !bank.containsKey(pers);
		
		boolean check = false;
		int persons = bank.size();
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(pers.getCnp())) {
				System.out.println("Person exists");
				check = true;
			}			
		}
		if(check == false) {
			ArrayList<Account> accountsForPers = new ArrayList<Account>();
			bank.put(pers, accountsForPers);
			
		}
		assert bank.containsKey(pers);
		assert persons + 1 == bank.size();
	}

	@Override
	public void removePerson(Person pers) {
		
		assert isWellFormed();
		assert pers.isWellFormed();
		assert containsPerson(pers);
		
		Person toRemove = null;
		int keys = bank.size();
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(pers.getCnp())) {
				toRemove = entry.getKey();
				System.out.println("am  sters");
			}
		}
		bank.remove(toRemove);
		
		assert keys-1 == bank.size();
	}
	
	@Override
	public void editPerson(String oldCnp,Person pers) {
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(oldCnp)) {
				entry.getKey().setCnp(pers.getCnp());
				entry.getKey().setName(pers.getName());
			}
		}
	}
	
	public boolean exists(Person pers) {
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(pers.getCnp())
					&& entry.getKey().getName().equals(pers.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public HashMap getBank() {
		return this.bank;
	}
	
	public boolean isWellFormed() {
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getValue() == null)
				return false;
		}
		return true;
	}
	
	public boolean containsPerson(Person p) {
		for(Map.Entry<Person, ArrayList<Account>> entry : bank.entrySet()) {
			if(entry.getKey().getCnp().equals(p.getCnp())
					&& entry.getKey().getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}
	

	
}
