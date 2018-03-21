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
    
    public DatabaseLoader(String inDriver, String inUrl)
    {
    	myDriver = inDriver;
    	myUrl = inUrl;
    	nodeList = new ArrayList<Node>();
    }
    
    public void LoadData(String inSqlQuery)
    {
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
	        	String s = rs.getString("test_data");
	        }
	        st.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    }
	
}
