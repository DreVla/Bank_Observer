/**
vlad
Apr 24, 2018

*/

package model;

import java.util.Observable;
import java.util.Observer;
//Observer -- any object that wishes to be notified when the state of another object changes
// adica persoana este observatorul
/**implementeaza java.io.Serializable pentru ca serializam si desearealizam bank care contine persoane
*/
public class Person implements Observer, java.io.Serializable{

	private String cnp;
	private String name;
	
	public Person(String cnp, String name) {
		this.cnp = cnp;
		this.name = name;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isWellFormed() {
		if(this.name == null || cnp == null) 
			return false;
		else return true;
	}
	
	public String toString() {
		return this.name + " has " + this.cnp;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println(this.name + " modified account "+((Account) arg0).getId()+"! New balance is: " + ((Account) arg1).getMoney()) ;
		
	}
}
