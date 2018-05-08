/**
vlad
Apr 24, 2018

*/

package model;

public class SpendingAccount extends Account{

	public SpendingAccount(int id, double money) {
		//super.holder = person;
		super.id = id;
		super.money = money;
		super.setAccName("Spending Account");
		super.period = 0;
		super.interest = 0;
	}
	
	@Override
	protected void depositMoney(double amount) {
		setMoney(getMoney() + amount);
		setChanged();
		//notifyObservers("Deposited" + amount);
	}
	
	@Override
	protected double extractMoney(double amount) {
		
		if(money < amount) {
			System.out.println("Not enough money in account");
			return 0;
		} else {
			setMoney(getMoney() - amount);
			setChanged();
			//notifyObservers("Extracted" + amount);
			return money;
		}
	}
	
	@Override
	protected boolean isWellFormed() {
		if( id < 0 || this.getAccName() != "Spending Account")
			return false;
		else return true;
	}
}
