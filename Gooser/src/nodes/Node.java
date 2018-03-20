package nodes;

public class Node {
	
	private static int nextNodeID = 0;
	
	private String nodeName;
	private String nodeValue;
	private int nodeID;
	
	public Node(){
		nodeName = "";
		nodeValue = "";
		nodeID = getNextNodeID();
	}
	
	public Node(String inNodeName, String inNodeValue){
		nodeName = inNodeName;
		nodeValue = inNodeValue;
		nodeID = getNextNodeID();
	}
	
	public String getNodeName() {
		return nodeName;
	}
	
	public void setNodeName(String newNodeName) {
		nodeName = newNodeName;
	}
	
	public String getNodeValue() {
		return nodeValue;
	}
	
	public void setNodeValue(String newNodeValue) {
		nodeValue = newNodeValue;
	}
	
	public int getNextNodeID() {
		if(nextNodeID < Integer.MAX_VALUE)
		{
			nextNodeID++;
			return nextNodeID;
		}
		else 
		{
			return 0;
		}
	}
	
	public void resetNextNodeID(){
		nextNodeID = 0;
	}
	
	public int getNodeID() {
		return nodeID;
	}
	
}
