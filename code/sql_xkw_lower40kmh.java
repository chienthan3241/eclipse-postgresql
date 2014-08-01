package testSQL;

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;//to import this muss add download .jar file(from https://code.google.com/p/json-simple/downloads/detail?name=json-simple-1.1.1.jar&can=2&q=) to JAVA build PATH 
import org.json.simple.JSONObject;
import org.json.simple.JSONAware;

public class sql_xkw_lower40kmh {

	public static void main(String[] args) {		
		Connection c = null;
	    Statement stmt = null;
		/**
		 * CSV Files 
		 */
	    try { 	/*connection string: "jdbc:postgresql://host:port/dbname", "username", "password"*/
			//connect to DB
	    		c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
			stmt = c.createStatement();
			//Query all Data items with speed_pkw < 40kmh and speed_lkw < 40kmh for two Induktivschleife(2000000,2000161) and time no limit 
			String sql = "SELECT * FROM mdp WHERE (flow_lkw > 0 AND speed_lkw < 40 ) AND (flow_pkw > 0 AND speed_pkw < 40 ) AND site in (2000000,2000161) ORDER BY site asc, tsp asc ";
			System.out.println("SQL executing......");
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("done!");
			// write to csv file
			try
			{
				System.out.println("Begin write to csv...");
			    FileWriter writer = new FileWriter("lower40kmh.csv");
			    while ( rs.next() ) {
		            String site 		= rs.getString("site");
		            String tsp 			= rs.getString("tsp");
		            String flow_lkw 	= rs.getString("flow_lkw");
		            String speed_lkw 	= rs.getString("speed_lkw");
		            String flow_pkw 	= rs.getString("flow_pkw");
		            String speed_pkw 	= rs.getString("speed_pkw");
		            String flow_all 	= rs.getString("flow_all");
		            String speed_all 	= rs.getString("speed_all");
		            String concentration 	= rs.getString("concentration");
		            writer.append(site);
				    writer.append(';');
				    writer.append(tsp);
				    writer.append(';');
				    writer.append(flow_lkw);
				    writer.append(';');
				    writer.append(speed_lkw);
				    writer.append(';');
				    writer.append(flow_pkw);
				    writer.append(';');
				    writer.append(speed_pkw);
				    writer.append(';');
				    writer.append(flow_all);
				    writer.append(';');
				    writer.append(speed_all);
				    writer.append(';');
				    writer.append(concentration);
				    writer.append(';');
				    writer.append('\n');
		         }		  
		 
			    writer.flush();
			    writer.close();
			    System.out.println("done!");
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			}
			rs.close();
	        stmt.close();
	        c.close();
		} catch (SQLException e) { 
			System.out.println("SQL Failed!");
			e.printStackTrace();
			return; 
		} 
	    
	    /**
		 * JSON Files 
		 */
	    try { 	/*connection string: "jdbc:postgresql://host:port/dbname", "username", "password"*/
			//connect to DB
			c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
			stmt = c.createStatement();
			//Query all Data items with speed_pkw < 15kmh and speed_lkw < 15kmh for all Induktivschleife and time no limit 
			String sql = "SELECT * FROM mdp WHERE (flow_lkw > 0 AND speed_lkw < 15 ) AND (flow_pkw > 0 AND speed_pkw < 15 ) ORDER BY site asc, tsp asc ";
			System.out.println("SQL executing......");
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("done!");
			// write to JSON file
			try
			{
				System.out.println("Begin write to json...");
				JSONArray list = new JSONArray();				
			    FileWriter writer = new FileWriter("lower15kmh.json");
			    while ( rs.next() ) {
		            String site 		= rs.getString("site");
		            String tsp 			= rs.getString("tsp");
		            String flow_lkw 	= rs.getString("flow_lkw");
		            String speed_lkw 	= rs.getString("speed_lkw");
		            String flow_pkw 	= rs.getString("flow_pkw");
		            String speed_pkw 	= rs.getString("speed_pkw");
		            String flow_all 	= rs.getString("flow_all");
		            String speed_all 	= rs.getString("speed_all");
		            String concentration 	= rs.getString("concentration");
		            list.add(new Res_item(site,tsp,flow_lkw,speed_lkw,flow_pkw,speed_pkw,flow_all,speed_all,concentration));
		         }		  
			    writer.append(list.toJSONString());
			    writer.flush();
			    writer.close();
			    System.out.println("done!");
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			}
			rs.close();
	        stmt.close();
	        c.close();
		} catch (SQLException e) { 
			System.out.println("SQL Failed!");
			e.printStackTrace();
			return; 
		} 
	}	

}

// use this class to write json file
class Res_item implements JSONAware{
    private String site;
    private String tsp;
    private String flow_lkw;
    private String speed_lkw;
    private String flow_pkw;
    private String speed_pkw;
    private String flow_all;
    private String speed_all;
    private String concentration;
    
    public Res_item(String site, String tsp, String flow_lkw, String speed_lkw, String flow_pkw, String speed_pkw, String flow_all, String speed_all, String concentration){
            this.site = site;
            this.tsp = tsp;
            this.flow_lkw = flow_lkw;
            this.speed_lkw = speed_lkw;
            this.flow_pkw = flow_pkw;
            this.speed_pkw = speed_pkw;
            this.flow_all = flow_all;
            this.speed_all = speed_all;
            this.concentration = concentration;
    }
    
    public String toJSONString(){
            StringBuffer sb = new StringBuffer();
            
            sb.append("{");
            
            sb.append(JSONObject.escape("site"));
            sb.append(":");
            sb.append("\"" + JSONObject.escape(site) + "\"");
            
            sb.append(",");
            
            sb.append(JSONObject.escape("tsp"));
            sb.append(":");
            sb.append(tsp);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("flow_lkw"));
            sb.append(":");
            sb.append(flow_lkw);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("speed_lkw"));
            sb.append(":");
            sb.append(speed_lkw);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("flow_pkw"));
            sb.append(":");
            sb.append(flow_pkw);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("speed_pkw"));
            sb.append(":");
            sb.append(speed_pkw);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("flow_all"));
            sb.append(":");
            sb.append(flow_all);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("speed_all"));
            sb.append(":");
            sb.append(speed_all);
            
            sb.append(",");
            
            sb.append(JSONObject.escape("concentration"));
            sb.append(":");
            sb.append(concentration);
            
            sb.append("}");
            
            return sb.toString();
    }
}
