package loaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nodes.Node;

public class DatabaseLoader {
	
	//"org.gjt.mm.mysql.Driver"
    String myDriver;
    
    //"jdbc:mysql://localhost/test"
    String myUrl;
    
    List<Node> nodeList;
    int nextNodeID = 1;
    
    public DatabaseLoader(String inDriver, String inUrl)
    {
    	myDriver = inDriver;
    	myUrl = inUrl;
    	nodeList = new ArrayList<Node>();
    }
    
    public void LoadData(String inSqlQuery)
    {
    	
    	String tempName = "";
    	String tempValue = "";
    	Node tempNode;
    	int idx = 0;
    	
    	try
    	{
	    	myDriver = "org.gjt.mm.mysql.Driver";
	        myUrl = "jdbc:mysql://localhost/test";
	        Class.forName(myDriver);
	        Connection conn = DriverManager.getConnection(myUrl, "root", "");
	        
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(inSqlQuery);
	        
	        while (rs.next())
	        {
	        	tempNode = new Node(nextNodeID);
	        	for(idx = 0; idx < rs.getMetaData().getColumnCount(); idx++)
	        	{
	        		tempName = rs.getMetaData().getColumnName(idx);
	        		tempValue = rs.getString(idx);
	        		tempNode.Add(tempName, tempValue);
	        	}
	        	nodeList.add(tempNode);
	        	nextNodeID++;
	        }
	        st.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    }
	
}
