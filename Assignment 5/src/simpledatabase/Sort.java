package simpledatabase;
import java.util.*;
import java.lang.*;

import simpledatabase.Type.DataTypes;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	
	
	Tuple tupleA, tupleB;
	int newIndex = 0;
	int index = 0;
	int position;
	
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
	}
	
	
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if(index == 0){
			tupleB = child.next();
			while(tupleB != null){
				Tuple tuple3 = new Tuple(tupleB.getAttributeList());
				tuplesResult.add(tuple3);
				tupleB = child.next();
			}
			
			Type variableType = null;
			
			for(int i = 0; i < tuplesResult.get(0).getAttributeList().size(); i++){
				if (tuplesResult.get(0).getAttributeList().get(i).getAttributeName().equals(orderPredicate)){
					position = i;
					variableType = tuplesResult.get(0).getAttributeList().get(i).getAttributeType();
				}
			}
			
			
			// Sort the tuple arrayList according to the above criteria
			if(variableType.type.equals(DataTypes.INTEGER) || variableType.type.equals(DataTypes.DOUBLE) 
					|| variableType.type.equals(DataTypes.LONG) || variableType.type.equals(DataTypes.SHORT)
					|| variableType.type.equals(DataTypes.FLOAT)){
				for(int i = 0; i < tuplesResult.size(); i++){
					for(int j = 0; j < tuplesResult.size()-1; j++)
						if(Integer.parseInt(tuplesResult.get(j).getAttributeList().get(position).getAttributeValue().toString()) 
								> Integer.parseInt(tuplesResult.get(j+1).getAttributeList().get(position).getAttributeValue().toString()))
							Collections.swap(tuplesResult, j, j+1);
				}
				
			}else{
				for(int i = 0; i < tuplesResult.size(); i++){
					for(int j = 0; j < tuplesResult.size()-1; j++)
						if(tuplesResult.get(j).getAttributeList().get(position).getAttributeValue().toString().compareTo( 
								tuplesResult.get(j+1).getAttributeList().get(position).getAttributeValue().toString()) > 0)
							Collections.swap(tuplesResult, j, j+1);
				}
			}
			index++;
		}
		if(this.newIndex < tuplesResult.size()){
			tupleA = tuplesResult.get(this.newIndex);
			this.newIndex++;
			return tupleA;
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}