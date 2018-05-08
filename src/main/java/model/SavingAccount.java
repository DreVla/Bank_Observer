/**
vlad
Apr 24, 2018

*/

package model;

public class SavingAccount extends Account{

	
	
	public SavingAccount(int id, int period, double money) {
		//super.holder = person;
		super.id = id;
		super.money = money;
		super.setAccName("Saving Account");
		super.interest = 2;
		super.period = period;
	}
	
	@Override
	protected void depositMoney(double amount) {
		if(amount < 1000) {
			System.out.println("Cannot deposit less than 1000");
		} else {
			setMoney(getMoney() + amount + ((interest/100)*amount)*period);
			setChanged();
			//notifyObservers("Deposited" + amount);
		}
	}
	
	@Override
	protected double extractMoney(double amount) {
		if(money < amount) {
			System.out.println("Not enough money in account");
			return 0;
		} else if (amount < 1000){
			System.out.println("Cannot extract less than 1000");
			return 0;
		} else {
			setMoney(getMoney() - amount);
			setChanged();
			//notifyObservers("Withdraw" + amount);
			return money;
		}
	}
	
	@Override
	protected boolean isWellFormed() {
		if( id < 0 || this.getAccName() != "Saving Account")
			return false;
		else return true;
	}
}
