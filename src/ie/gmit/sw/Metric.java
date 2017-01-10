package ie.gmit.sw;

/**
 * Class used to hold information of a 
 * calculation of stability for a given class
 */

//class metric to hold information for calculation of stability
public class Metric implements Metricable{
	//member vars
	private int inDegree;
	private int outDegree;
	private float stability;
	
	//constructor
	public Metric(){

	}
	
	//overloaded constructor
	public Metric(int inDegree, int outDegree) {
		super();
		this.inDegree = inDegree;
		this.outDegree = outDegree;
	}

	//Gets to retrieve member vars
	public int getInDegree() {
		return inDegree;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public float getStability() {
		return stability;
	}
	
	//Sets to set new values for member vars
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	public void setStability(float stability) {
		this.stability = stability;
	}
	
	//calculation of stability using the formula from implementing the interface
	public void calcStability(int in, int out){
		this.stability = ((float)out / ((float)in + (float)out));
	}
}