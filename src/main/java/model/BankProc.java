/**
vlad
Apr 24, 2018

*/

package model;

public interface BankProc {

	/** 
	 * @pre		pers.isWellFormed()
	 * @pre 	bank.isWellFormed()
	 * @pre		!bank.containsKey(pers)
	 * @post	bank.size() == @pre.size() + 1
	 */
	public void addPerson(Person pers);
	
	/** 
	 * @pre 	pers.isWellFormed()
	 * @pre 	bank.isWellFormed()
	 * @pre		bank.containsKey(pers)
	 * @post	bank.size() == @pre.size() - 1;
	 */
	public void removePerson(Person pers);
	
	/**
	 * @pre pers.isWellFormed()
	 * @pre oldCnp != null
	 * 
	 * @post oldCnp != newCnp
	 * @post oldName != newName
	 */
	public void editPerson(String oldCnp,Person pers);
	
	/** 
	 * @pre pers.isWellFormed() == true
	 * @pre acc.isWellFormed() == true
	 * @pre bank.isWellFormed()
	 * 
	 * @post bank.get(pers).getValue().size == @pre.size() + 1
	 */
	public void addAccount(Person pers, Account acc);
	
	/** 
	 * @pre pers.isWellFormed() == true
	 * @pre acc.isWellFormed() == true
	 * @pre bank.isEmpty() == false
	 * 
	 * @pre bank.get(pers).getValue() != null
	 * 
	 * @post bank.get(pers).getValue().size = @pre.size() - 1
	 */
	public void removeAccount(Person pers, Account acc);
	
	/** 
	 * @pre bank!=null
	 * @post bank.isEmpty() == false
	 */
	public void readAccounts();
	
	/** 
	 * @pre  bank!= null
	 * @post 
	 */
	public void writeAccounts();
	
	/**
	 * @pre cnp!=null
	 * @pre id > 0
	 * @pre money > 0
	 * @post oldAmount + money = currentAmount
	 */
	public void deposit(String cnp, int id, double money);
	
	/**
	 * @pre cnp!=null
	 * @pre id > 0
	 * @pre money > 0
	 * @post oldAmount - money = currentAmount
	 */
	public void withdraw(String cnp, int id, double money);
	
}
