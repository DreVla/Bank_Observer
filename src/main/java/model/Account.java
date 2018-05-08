/**
vlad
Apr 24, 2018

*/

package model;

import java.util.Observable;
//Observable -- any object whose state may be of interest, and in whom another object may register an interest
// adica vrem sa il observam si in caz ca se modifica anuntam
/**implementeaza java.io.Serializable pentru ca serializam si desearealizam bank care contine account
*/
public abstract class Account extends Observable implements java.io.Serializable{

	//protected Person holder;
	protected int id;
	protected double money;
	protected String accName;
	protected double interest;
	protected int period;
	
	
	public Account() {
		
	}
	
	public boolean equalss(Account a) {
		if(this.id == a.getId() && 
				this.money == a.getMoney() && 
				this.accName.equals(a.getAccName()) && 
				this.interest == a.getInterest() && 
				this.period == a.getPeriod()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public double getInterest() {
		return interest;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

//	public Person getHolder() {
//		return holder;
//	}
//
//	public void setHolder(Person person) {
//		this.holder = person;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
		setChanged();
		notifyObservers(this);
		
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
	
	public String toString() {
		return this.id + " is a " + this.accName + " with " + this.money;
	}
	
	protected abstract void depositMoney(double amount);
	protected abstract double extractMoney(double amount);
	protected abstract boolean isWellFormed();
	
	
}
