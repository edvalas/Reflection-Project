package ie.gmit.sw;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which uses the reflection package,
 * to analyze classes for In and Out degrees by
 * checking for dependencies
 */

//reflection class which checks classes for dependencies
public class Reflection{
	//Map which will be populated in the constructor
	private Map<String, Metric> table = new HashMap<String, Metric>();
	private int outdegree;
	
	public Reflection(Map<String, Metric> map){
		//map from parameter is assigned to member var table
		this.table = map;
		outdegree = 0;
	}
	
	//method to check constructor parameters for dependencies
	public void getConstructors(Class cls){
		//Retrieve constructor list for the class
        Constructor[] constructorArr = cls.getConstructors();
        //array for constructor parameters
        Class[] constructorParameters;

        //for in loop to populate constructorParameters array for each constructor of the class
        for(Constructor element : constructorArr){
        	//retrieve parameter types(what is passed to the constructor)
        	constructorParameters = element.getParameterTypes();
        	//loop over parameter array and check if any are on the map for dependency
            for(Class parameter : constructorParameters){
                if(table.containsKey(parameter.getName())){
                	//if parameter type is on a map eg. a class obj passed as parameter => dependency
                    //increment in and out degrees
                    outdegree++;

                    //create metric obj to retrieve in degree for that key on the map
                    Metric m = table.get(parameter.getName());
                    //increment in degree
                    m.setInDegree(m.getInDegree() + 1);
                }
            }//inner parameter loop
        }
	}
	
	//method to check interface implementation for dependencies
	public void getInterfaces(Class cls){
		//Retrieve the interface list for the class
        Class[] interfaceArr = cls.getInterfaces();
        //for in loop for each interface in the interfaces array
        for(Class element : interfaceArr){
            if(table.containsKey(element.getName())){
                //if the interface name is on the map as key => dependency
            	//increment in and out degrees
                outdegree++;

                //create metric obj to retrieve in degree for that key on the map
                Metric m = table.get(element.getName());
                //increment in degree
                m.setInDegree(m.getInDegree() + 1);
            }
        }
	}
	
	//method to check member variables of classes for dependencies
	public void getVars(Class cls){
		//Retrieve member variables for the class
        Field[] vars = cls.getFields();
        //for in loop for each member variable in vars array
        for(Field element : vars){
            if(table.containsKey(element.getName())){
            	//if member variable is on the map as a key => dependency
                //increment in and out degrees
                outdegree++;

                //create metric obj to retrieve in degree for that key on the map
                Metric m = table.get(element.getName());
                //increment in degree
                m.setInDegree(m.getInDegree() + 1);
            }
        }
	}
	
	//method to check method parameters and return types for dependencies
	public void getMethods(Class cls){
		//Retrieve methods of the class
        Method[] methods = cls.getMethods();
        //Method parameter array
        Class[] methodParameters;

        //for in loop over the method array to check parameters and return types
        for(Method element : methods){
        	//Retrieve the method return type for each method
            Class rt = element.getReturnType();
            
            if(table.containsKey(rt.getName())){
            	//if the return type is of type of a class which is on the map as a key => dependency
                // increment in and out degrees
                outdegree++;

                //create metric obj to retrieve in degree for that key on the map
                Metric m = table.get(rt.getName());
                //increment in degree
                m.setInDegree(m.getInDegree() + 1);
            }
            
            //Retrieve parameter types for the method
            methodParameters = element.getParameterTypes();
            //for in loop for each method parameter in the parameter array
            for(Class methodparameter : methodParameters){
                if(table.containsKey(methodparameter.getName())){
                	//if method parameter type is a key on the map => dependency
                    //increment in and out degrees
                    outdegree++;

                    //create metric obj to retrieve in degree for that key on the map
                    Metric m = table.get(methodparameter.getName());
                    //increment in degree
                    m.setInDegree(m.getInDegree() + 1);
                }
            }//inner method parameter loop
        }
	}
	
	//update map with out degree
	public void updateOutDegree(Class cls){
		//finally for the class, set its out degree
        table.get(cls.getName()).setOutDegree(outdegree);
	}
}