package com.eclipsetotale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public class DatabaseRequest {

	ResultSet execute(String iQuery, String iColumn, Vector oResVector) {

		String column;
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    con = DriverManager.getConnection("jdbc:mysql:///test", "root", "password");

//		    if(!con.isClosed())
//		    	System.out.println("Successfully connected to MySQL server using TCP/IP...");

		    } catch(Exception e) {
		      System.err.println("Exception: " + e.getMessage() + " " + e.getClass());
		    } finally {

		    try {
		        stmt = con.createStatement();
		        rs = stmt.executeQuery(iQuery);
		        
				while (rs.next()) {
					column = rs.getString(iColumn);
					oResVector.add(column);
				}
		    }
		    catch (SQLException ex){
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
		    finally {
		        // it is a good idea to release
		        // resources in a finally{} block
		        // in reverse-order of their creation
		        // if they are no-longer needed

		    	if (rs != null) {
		            try {
		            	rs.close();
		            } catch (SQLException sqlEx) { } // ignore 

		            rs = null;
		        }
		    	
		        if (stmt != null) {
		            try {
		                stmt.close();
		            } catch (SQLException sqlEx) { } // ignore

		            stmt = null;
		        }
		    }

		    try {
		    	if(con != null)
			    	con.close();
			} catch(SQLException e) {}
		}
		    
		return rs;
	}
	
}
