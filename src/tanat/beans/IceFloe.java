package tanat.beans;

public class IceFloe {
	private int id;
	private String name;
	private int size;
	private int numberOfFishermen;
	
	public IceFloe(int id, String name, int size, int numberOfFishermen) {
	       this.id = id;
	       this.name = name;
	       this.size = size;
	       this.numberOfFishermen = numberOfFishermen;
	   }
	
	public IceFloe(String name, int size, int numberOfFishermen) {
	       this.name = name;
	       this.size = size;
	       this.numberOfFishermen = numberOfFishermen;
	}

	public void IseFloe() {

	}
	
	public int getId() {
		return id;
	}
		 
	public void setIdIceFloe(int id) {
		this.id = id;
	}
	
	public int getSize() {
		return size;
	}
		 
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
		 
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfFishermen() {
		return numberOfFishermen;
	}
		 
	public void setNumberOfFishermen(int numberOfFishermen) {
		this.numberOfFishermen = numberOfFishermen;
	}
	
}
