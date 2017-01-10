package ie.gmit.sw;
/**
 * Interface containing a method 
 * for calculation of class stability for a given class
 */
//calculation of class stability(Robert C. Martin)
public interface Metricable {
	public void calcStability(int in, int out);
}