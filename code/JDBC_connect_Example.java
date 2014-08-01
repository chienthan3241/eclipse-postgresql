package abc;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] argv) {
		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------"); 
		Connection c = null;
	    Statement stmt = null;
		try { 
			Class.forName("org.postgresql.Driver");			
		} catch (ClassNotFoundException e) { 
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0); 
		}
 
		try { 	/*connection string: "jdbc:postgresql://host:port/dbname", "username", "password"*/
			c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/detektordaten_hessen", "postgres", "Password2013");
			stmt = c.createStatement();
			String sql = "select max(speed_pkw) as max_speed_pkw from public.mdp where concentration <>-1";
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
	            int max_speed_pkw = rs.getInt("max_speed_pkw");   
	            System.out.println( "Max Speed PKW : " + max_speed_pkw );
	            System.out.println();
	         }			
	         rs.close();
	         stmt.close();
	         c.close();
		} catch (SQLException e) { 
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return; 
		}		
	}
}