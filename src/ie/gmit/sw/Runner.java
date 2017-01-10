package ie.gmit.sw;

/**
 * Runner class
 */
public class Runner{
	public static void main(String[] args){
		//create new MetricImpl obj and pass in the name of the jar file to be analyzed
		//which is to be included in the project solution!!!
		MetricImpl metric = new MetricImpl("string-service.jar");
		//call parse method to parse the jar file for all classes
		//and add them to the map
		metric.parse();
		//call calculate stability method which calculates in & out degrees for each class
		//on the map and then calculates stability for each class
		metric.calcStability();
	}
}