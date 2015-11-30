package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();
	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple tuple = child.next();
		
		
		if(child.from.equals(whereTablePredicate)){
			while(tuple != null){
				for(int i = 0; i < child.getAttributeList().size(); i++){
		    		if(child.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate)
		    				&& child.getAttributeList().get(i).getAttributeValue().equals(whereValuePredicate)){
		    			attributeList = child.getAttributeList();
		    			return tuple;
		    		}
		    	}
				return this.next();
			}
		}else{
			return child.next();
		}
		
		return null;
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return(child.getAttributeList());
	}

	
}