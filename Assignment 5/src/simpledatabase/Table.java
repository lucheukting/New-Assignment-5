package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	public int index = 0;
	
	public String line, attributeLine, dataTypeLine;
	public ArrayList<String> storeRecord = new ArrayList<String>();
	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		if(!getAttribute){
			try {
				while ((line = br.readLine()) != null) {
					if(this.index == 0){
						attributeLine = line;
					}else if(this.index == 1){
						dataTypeLine = line;
					}else{
						storeRecord.add(line);
					}
					this.index++;
				}
				getAttribute = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.index = 0;
		}
	    if(this.index < storeRecord.size()){
	    	this.tuple = new Tuple(attributeLine, dataTypeLine, storeRecord.get(index));
	    	tuple.setAttributeName();
	    	tuple.setAttributeType();
	    	tuple.setAttributeValue();
	    	this.index++;
	    	return this.tuple;
	    }
	    this.index = 0;
		return null;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}