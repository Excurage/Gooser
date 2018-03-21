package nodes;

import java.util.HashMap;
import java.util.Map;

public class Node {
	
	private Map<String, String> mapNameValues;
	private int nodeID;
	
	public Node(int inNodeID){
		mapNameValues = new HashMap<String, String>();
		nodeID = inNodeID;
	}
	
	public Map<String, String> GetMapNameValues()
	{
		return mapNameValues;
	}
	
	public int GetNodeID()
	{
		return nodeID;
	}
	
	public void Add(String inName, String inValue)
	{
		mapNameValues.put(inName, inValue);
	}

	
}
