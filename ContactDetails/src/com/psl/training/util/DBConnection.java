package com.psl.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.psl.training.model.Contact;


public class DBConnection {
	static Connection con = null;

	 DBConnection() {
		super();
	}
	// "jdbc:oracle:thin:@//localhost:1521/XE "
	static String driverClass = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	static String username = "contactdb";
	static String password = "contactdb";
	public static Connection getConnection() {
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connection created");
		return con;
	}
	public static void createTables() 	{	
			try {
				Statement stmt=con.createStatement();
				//stmt.execute("CREATE USER contactdb IDENTIFIED BY contactdb");
				//stmt.execute("GRANT dba TO contactdb");
				//stmt.execute("create table contact_tbl(contactId number(10) primary key,contactName varchar(30), contactEmail varchar(40),contactList varchar(100))");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void insertData() {
			try {
				PreparedStatement pstmt = con.prepareStatement("insert into contact_tbl values (?,?,?,?)");
				pstmt.setInt(1, 100);
				pstmt.setString(2, "Geeta");
				pstmt.setString(3,"geet@gmail.com");
				pstmt.setString(4, "23434,3445,34345");
				pstmt.executeUpdate();
			
				pstmt.setInt(1, 101);
				pstmt.setString(2, "Radha");
				pstmt.setString(3,"rad@rediffmail.com");
				pstmt.setString(4,null);
				pstmt.executeUpdate();
				
				pstmt.setInt(1, 102);
				pstmt.setString(2, "Geeta");
				pstmt.setString(3,"geet@gmail.com");
				pstmt.setString(4,"345345");
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static Set<Contact> getContactFromDB(){
		Set<Contact> contactSet= new HashSet();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from contact_tbl");
			while (rs.next()) {
		        int id = rs.getInt("CONTACTID");
		        String name = rs.getString("CONTACTNAME");
		        String email = rs.getString("CONTACTEMAIL");
		        String list = rs.getString("CONTACTLIST");
		        //System.out.println(id + ", " + name + ", " + email +","+list);
		        List<String> lst=new ArrayList();
		        if(list!=null) {
		        	lst=Arrays.asList(list.split(","));
		        }
		        Contact c = new Contact(id,name,email,lst);
		        contactSet.add(c);
		      }
			System.out.println(contactSet.toString());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return contactSet;
	}
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

