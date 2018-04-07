package tanat.beans;

public class See {
	private String date;
	private int numIceFloe;
	private int numShip;
	private int numFishermen;
	private int numFishIce;
	
	public See(int numIceFloe,int numShip,int numFishermen, int numFishIce) {
	       this.numIceFloe = numIceFloe;
	       this.numShip = numShip;
	       this.numFishermen = numFishermen;
	       this.numFishIce = numFishIce;
	   }
	
	public See(String date, int numIceFloe,int numShip,int numFishermen, int numFishIce) {
			this.setDate(date);
			this.numIceFloe = numIceFloe;
			this.numShip = numShip;
			this.numFishermen = numFishermen;
			this.numFishIce = numFishIce;
	   }
	
	public See() {

	   }
	
	public int getNumIceFloe() {
		return numIceFloe;
	}
		 
	public void setNumIceFloe(int numIceFloe) {
		this.numIceFloe = numIceFloe;
	}
	
	public int getNumShip() {
		return numShip;
	}
		 
	public void setNumShip(int numShip) {
		this.numShip = numShip;
	}
	
	public int getNumFishermen() {
		return numFishermen;
	}
		 
	public void setNumFishermen(int numFishermen) {
		this.numFishermen = numFishermen;
	}
	
	public int getNumFishIce() {
		return numFishIce;
	}
		 
	public void setNumFishIce(int numFishIce) {
		this.numFishIce = numFishIce;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
