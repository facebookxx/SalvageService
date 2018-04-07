package tanat.beans;

public class Ship {
	private int id;
	private String name;
	private int numPas;
	private boolean location;
	
	public Ship(int id, String name, int numPas, boolean location) {
	       this.id = id;
	       this.name = name;
	       this.numPas = numPas;
	       this.location = location;
	   }
	
	public Ship() {

	   }
	
	public int getId() {
		return id;
	}
		 
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
		 
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumPas() {
		return numPas;
	}
		 
	public void setNumPas(int numPas) {
		this.numPas = numPas;
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
