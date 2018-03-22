package loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nodes.Node;

public class CsvLoader extends Loader {
	
	static final String CSV_SEPARATOR = ",";
	
	String fileName;
	List<Node> nodeList;
	BufferedReader buffReader;
	int nextNodeID = 1;
	
	public CsvLoader(String inFileName)
	{
		fileName = inFileName;
		nodeList = new ArrayList<Node>();
	}
	
	public void LoadData()
	{
		
		String newLine = "";
		String[] tempValueArray;
		String[] nameRow;
		int idx = 0;
		Node tempNode;
		String tempName = "";
		String tempValue = "";
		
		try
		{
			buffReader = new BufferedReader(new FileReader(fileName));
			nameRow = buffReader.readLine().split(CSV_SEPARATOR);
			while ((newLine = buffReader.readLine()) != null)
			{
				tempValueArray = newLine.split(CSV_SEPARATOR);
				tempNode = new Node(nextNodeID);
				for(idx = 0; idx < nameRow.length; idx++)
				{
					tempName = nameRow[idx];
					tempValue = tempValueArray[idx];
					tempNode.Add(tempName, tempValue);
				}
				nodeList.add(tempNode);
				nextNodeID++;
			}
		}
		catch(FileNotFoundException fnfEx)
		{
			System.out.println(fnfEx.getMessage());
		}
		catch(IOException ioEx)
		{
			System.out.println(ioEx.getMessage());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public List<Node> getNodeList()
	{
		return nodeList;
	}
	
	
	
}
