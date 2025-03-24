package org.bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class CustomerManagement {
	
	public static void viewCustomers() {
		try(Connection con=DbConnection.getConnection()){
			Statement smt=con.createStatement();
			String q="select * from customer";
			ResultSet rs=smt.executeQuery(q);
			System.out.printf("%-10s%-20s%-15s%-10s%-10s%-15s%-10s%n","custId","Username","Password","FirstName","LastName","Address","Mobile");
			if(rs.next()) {
				System.out.printf("%-10s%-20s%-15s%-10s%-10s%-15s%-10s%n",
				rs.getInt("custId"),
				rs.getString("Username"),
				rs.getString("Password"),
				rs.getString("FirstName"),
				rs.getString("LastName"),
				rs.getString("Address"),
				rs.getLong("Mobile"));
				
			}
			else {
				System.out.println("No Customers...");
				
			}
			
		}catch(Exception e) {
			System.out.println("Error in"+e.getMessage());
			
			
		}
		}
	public static void addCustomers() {
		Scanner sc=new Scanner(System.in);
		try(Connection con=DbConnection.getConnection()){
			System.out.println("Enter the Customer Id:");
			int custid=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the UserName:");
			String user=sc.nextLine();
			System.out.println("Enter the Password:");
			String pass=sc.nextLine();
			System.out.println("Enter the FirstName:");
			String firstname=sc.nextLine();
			System.out.println("Enter the LastName:");
			String lastname=sc.nextLine();
			System.out.println("Enter the Address:");
			String address=sc.nextLine();
			System.out.println("Enter the Mobile number:");
			Long mobile=sc.nextLong();
			
			String q="insert into customer(custid,username,password,firstname,lastname,address,mobile)"+"values(?,?,?,?,?,?,?)";
				
		try(PreparedStatement psmt=con.prepareStatement(q)){
			psmt.setInt(1,custid);
			psmt.setString(2,user);
			psmt.setString(3,pass);
			psmt.setString(4,firstname);
			psmt.setString(5,lastname);
			psmt.setString(6,address);
			psmt.setLong(7,mobile);
			int inserted=psmt.executeUpdate();
			if(inserted>0) {
			System.out.println("New bus inserted succesfully...");
		}
			else {
				System.out.println("Failed to add bus...");
			}
		}
		
		}
		catch(Exception e) {
			System.out.println("Error is:"+e.getMessage());
			
		}
	}
	

	
		


}
