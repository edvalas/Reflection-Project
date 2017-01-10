package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Class used to create a swing Jtable,
 * using data from a map of classes
 */
//Class to create a swing table from a map and column array
public class CreateTable{
	//Map which will be populated in the constructor
	private Map<String, Metric> table = new HashMap<String, Metric>();
	
	//constructor, which takes a map as parameter
	public CreateTable(Map<String, Metric> map){
		//map from parameter is assigned to member var table
		this.table = map;
		//call method to convert table(hashmap) to 2d array
		toTableArray();
		//create the table
		makeTable();
	}
	
	//method to make table model from map
	public TableModel toTableModel(){
		//http://stackoverflow.com/questions/2257309/how-to-use-hashmap-with-jtable
	    
		//create defaulttablemodel obj with 4 values
		DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Key", "In", "Out", "Stability" }, 0
	    );
		//loop over the map entries and populate the model
	    for (Map.Entry<String, Metric> entry : table.entrySet()) {
	        model.addRow(new Object[] {entry.getKey(), entry.getValue().getInDegree(), entry.getValue().getOutDegree(), entry.getValue().getStability()});
	    }
	    return model;
	}

	//method to make 2d array from map
	public Object[][] toTableArray(){
		//http://stackoverflow.com/questions/2265266/convert-hash-map-to-2d-array
		
		//create 2d obj array with size of map for rows and 4 columns
		Object[][] arr = new Object[table.size()][4];
		//i to loop over the rows
		int i = 0;
		//for in loop over map entries to populate 2d array
		for (Map.Entry<String, Metric> entry : table.entrySet()){
			arr[i][0] = entry.getKey();
		    arr[i][1] = entry.getValue().getInDegree();
		    arr[i][2] = entry.getValue().getOutDegree();
		    arr[i][3] = entry.getValue().getStability();
		    //increment i for next row
		    i++;
		}
		
		return arr;
	}
	
	//method to create a swing table
	public void makeTable(){
		//http://stackoverflow.com/questions/13935934/java-jtable-column-headers-not-showing
		
		//string array for the column names, used with 2d array ONLY to create table
		String[] columns = {"Class name", "inDegree", "outDegree", "Stability"};
		
		//create table using the TABLE MODEL which has the data
		JTable jtable = new JTable(toTableModel());
		
		//create table by passing in the 2D OBJECT ARRAY of info and columns array for header names
		//JTable jtable = new JTable(toTableArray(), columns);
		
		//for resizing column when resizing window
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//set first column to be wider as class names can be long
		jtable.getColumnModel().getColumn(0).setPreferredWidth(210);
		//create a panel	
	    JPanel jpanel = new JPanel();
	    //add the table to a panel by passing it to a new Pane to let it handle the column names
	    jpanel.add(new JScrollPane(jtable));
	    //make new frame to open when program runs
	    JFrame frame = new JFrame();
	    //add our table to the frame for display
	    frame.add(jpanel);
	    //size of the window
	    frame.setSize(720,500);
	    //make it visible
	    frame.setVisible(true);
	}
}