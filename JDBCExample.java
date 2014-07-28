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
 
		try { 
			c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "Password2013");
			stmt = c.createStatement();
			String sql = "select * from myschema.tb_test";
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
	            int id = rs.getInt("id");
	            String  name = rs.getString("name");
	            int age  = rs.getInt("age");
	            
	            System.out.println( "ID = " + id );
	            System.out.println( "NAME = " + name );
	            System.out.println( "AGE = " + age );
	            
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
