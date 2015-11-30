package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple tuple;
		tuple = leftChild.next();
        while(tuple != null) {
            tuples1.add(tuple);
            tuple = leftChild.next();
        }
        
        Tuple tuple2 = rightChild.next();
        if(tuple2 != null) {
            for(int i = 0; i < tuples1.size(); i++) {
                if(tuple2.getAttributeList().get(2).attributeValue.equals(tuples1.get(i).getAttributeList().get(0).attributeValue)) {
                    tuple2.getAttributeList().addAll(tuples1.get(i).getAttributeList());
                }
            }
            return tuple2;
        }
        return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}