package tanat.beans;

public class Fishermen {
	private String name;
	private String surname;
	int numSave;
	private boolean location;
	
	public Fishermen(String name, String surname, int numSave, boolean location) {
	       this.name = name;
	       this.surname = surname;
	       this.numSave = numSave;
	       this.location = location;
	   }
	
	public Fishermen() {

	   }

	public Fishermen(String name, int numSave) {
		this.name = name;
	    this.numSave = numSave;

	}

	public String getName() {
		return name;
	}
		 
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
		 
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getNumSave() {
		 return numSave;
	}
	
	public void setNumPas(int numSave) {
		this.numSave = numSave;
	}
	
	public String getLocation() {
		if (location == true) {
			return "В море";
		} else {
		return "На суше";
		}
	}
		 
	public void setLocation(boolean location) {
		this.location = location;
	}
	
}
