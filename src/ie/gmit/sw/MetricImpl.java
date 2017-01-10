package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Main class that uses other classes
 * to parse, calculate and create a table from results
 */

public class MetricImpl{
	//map to store classes from jars and metric obj used for stability calculation
	private Map<String, Metric> table = new HashMap<String, Metric>();
	//string for name of jar file to be analyzed
	private String nameOfFile;
	//Reflection obj used to calculate in and out degrees
	private Reflection r;
	//CreateTable obj used to create table
	private CreateTable ct;
	
	//constructor taking in file name
	public MetricImpl(String name){
		this.nameOfFile = name;
	}
	
	//parse the jar
	public void parse(){
		//read in the jar
		JarInputStream in;
		try{
			//new jar input file input and file
			in = new JarInputStream(new FileInputStream(new File((nameOfFile))));
			//get next class in the jar file
			JarEntry next = in.getNextJarEntry();
			//while all classes are not processed
			while (next != null){
				//take out sub strings from class names we don't want
				if (next.getName().endsWith(".class")){
					String name = next.getName().replaceAll("/", "\\.");
					name = name.replaceAll(".class", "");
					if (!name.contains("$")){
						name.substring(0, name.length() - ".class".length());
					}
					//System.out.println(name);
					//add to the map the name of class as key and empty Metric obj as value
					table.put(name, new Metric());
			 }
			//get the next entry in the jar file
			next = in.getNextJarEntry();
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}		
	}

	//calculate stability method
	public void calcStability(){
        try {
            //get handle on jar file
            File file = new File(nameOfFile);

            //make urls to classes in jar file for class loader
            URL url = file.toURI().toURL();
            //add all urls to url array
            URL[] urlArr = new URL[]{url};

            //ClassLoader to load classes
            ClassLoader cl = new URLClassLoader(urlArr);

            //for in loop for each key on the map
            for (String key : table.keySet()){
                //get handle on each class with the class loader
                Class cls = Class.forName(key, false, cl);
                
                //call to calcMetric method to check the In and Out degrees of each class
                calcDegrees(cls);               
            }
            
            for (String key : table.keySet()){
            	//calculate stability, get a class using the key loop control var,
                //and call calcStability on the Metric object for the key
                //retrieve in & out degrees from the map for that metric object using the key.
                table.get(key).calcStability(table.get(key).getInDegree(), table.get(key).getOutDegree());
            }
            //after all processing is done
            //create a CreateTable class obj and pass in the populated map
            //which will then convert the map to a 2d array or table model and create a table
            ct = new CreateTable(table);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
	
	//method to calculate in & out degrees for a class
	public void calcDegrees(Class cls){
		//delegate all the work to reflection class
		//check constructors, interfaces,methods,vars for dependencies
		r = new Reflection(table);
		r.getConstructors(cls);
		r.getInterfaces(cls);
		r.getMethods(cls);
		r.getVars(cls);
		r.updateOutDegree(cls);
    }
}