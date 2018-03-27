package nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	
	private List<Node> nodeList;
	private Map<Node, Node> nodeLinks;
	private Node mostUsed;
	
	public Graph()
	{
		nodeList = new ArrayList<Node>();
		nodeLinks = new HashMap<Node,Node>();
	}
	
	public Graph(List<Node> inNodeList)
	{
		nodeList = inNodeList;
		nodeLinks = new HashMap<Node,Node>();
	}
	
	public void CreateLinks()
	{
		for(Node node1 : nodeList)
		{
			Set<String> keyset1 = node1.GetMapNameValues().keySet();
			for(Node node2 : nodeList)
			{
				Set<String> keyset2 = node2.GetMapNameValues().keySet();
				for(String key1 : keyset1)
				{
					for(String key2 : keyset2)
					{
						if(key1 == key2)
						{
							if(node1.GetMapNameValues().get(key1) ==
							   node2.GetMapNameValues().get(key2))
							{
								nodeLinks.put(node1, node2);
							}
						}
					}
				}
			}
		}
		SetMostUsed();
	}
	
	public void SetMostUsed()
	{
		mostUsed = nodeList.get(0);
	}
	
	public static Map<String,String> GetLinkedInfo(Node inNode1, Node inNode2)
	{
		return new HashMap<String,String>();
	}
	
}
