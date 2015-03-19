package com.jwt.struts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegisterDAO {
	
	//System.out.println("Oracle Connect START.");
    Connection conn = null;
    String url = "jdbc:oracle:thin:@Shashi-PC:1521:XE";
    String driver = "oracle.jdbc.OracleDriver";
    String userName1 = "scott";
    String password1 = "tiger";
    int status = 0;
	public void insertData(String firstName, String lastName, String userName,
			String password, String email, String phone) throws Exception {

        
        ResultSet rs = null;
        String uID = null;

		try {

			try {
				Class.forName(driver).newInstance();
		        conn = DriverManager.getConnection(url,userName1,password1);
		 
		        Statement stmt = conn.createStatement();
		        
		        stmt.executeUpdate("INSERT INTO Registration " + 
		               "VALUES (userId_seq.nextval,'"+ firstName + "','"+lastName+"','"+email+"',"+phone+",'"+ "kansas"+"',to_date('10-dec-1990','dd-mon-yyyy'),'y'"+")"); 
		        conn.commit();
		        rs = stmt.executeQuery("SELECT userId FROM Registration where mobile = "+ phone +"");
		        while(rs.next()){
		        	uID = rs.getString("userId");	
		        }
		        System.out.println(""+uID);
		        conn.commit();
		        stmt.executeUpdate("INSERT INTO Authentication " + 
			           "VALUES ('"+ uID +"','"+ userName + "','"+ password +"')");
		        
		        //stmt.executeUpdate("INSERT INTO Registration " + 
			      //         "VALUES ('1001','"+ firstName + "','"+lastName+"','"+email+"',"+phone+",'"+ "kansas"+",sysdate,'y'"+")"); 
		        
		       // System.out.println("INSERT INTO Registration " + 
		               // "VALUES ('"+ 1001 +"','"+ firstName + "','"+lastName+"','"+email+"',"+phone+",'"+ "kansas"+"',to_char('10-dec-1990','dd-mon-yyyy'),'yes'"+")");
				//System.out.println("1 row affected" + value);
			} catch (SQLException ex) {
				System.out.println("SQL statement is not executed! " + ex);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public int authenticateUser(String userName,String password) throws Exception {

        
        ResultSet rs1 = null;
        ResultSet rs2 = null;

		try {

			try {
				Class.forName(driver).newInstance();
				System.out.println("in DAO");
		        conn = DriverManager.getConnection(url,userName1,password1);
		        String userName1 = null;
		        String password1 = null;
		        Statement stmt = conn.createStatement();
		        rs1 = stmt.executeQuery("SELECT userName FROM Authentication where userName='"+ userName +"'");
		        conn.commit();
		        rs2 = stmt.executeQuery("SELECT password FROM Authentication where password='"+ password +"'");
		        conn.commit();
		        while(rs1.next()){
		        	userName1 = rs1.getString("userName");
		        	System.out.println(userName1);
		        }
		        while(rs2.next()){
		        	password1 = rs2.getString("password");
		        }
		        if(userName1 == userName && password1 == password){
		        	status = 1;
		        }
		        else{
		        	status = 0;
		        }
		      //  stmt.executeUpdate("INSERT INTO Registration " + 
		        //       "VALUES ('"+ 1001 +"','"+ firstName + "','"+lastName+"','"+email+"',"+phone+",'"+ "kansas"+"',to_date('10-dec-1990','dd-mon-yyyy'),'y'"+")"); 
		        
		     
			} catch (SQLException ex) {
				System.out.println("SQL statement is not executed!" + ex);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	
}
