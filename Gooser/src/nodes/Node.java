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

	public String ToString()
	{
		String retStr = "===START NODE" + nodeID + "===";
		int idx = 0;
		String tempValue = "";
		for(String key : mapNameValues.keySet())
		{
			tempValue = mapNameValues.get(key);
			retStr = retStr + key + " : " + tempValue + "\n";
		}
		retStr = retStr + "===END NODE" + nodeID + "===\n";
		return retStr;
	}
	
}
