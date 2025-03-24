package org.bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class BookingSystem {
	public static void viewBooking() {
		try(Connection con=DbConnection.getConnection()){
			Statement smt=con.createStatement();
			String q="select * from booking";
			ResultSet rs=smt.executeQuery(q);
			System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-10s%n","BusId","CustId","BusNo","SeatFrom","SeatTo","Status","SeatsBooked");
			boolean hasData=false;
			while(rs.next()) {
				hasData=true;
				System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-10s%n",
				rs.getInt("bid"),
				rs.getInt("custId"),
				rs.getInt("busno"),
				rs.getInt("seatfrom"),
				rs.getInt("seatto"),
				rs.getInt("status"),
				rs.getInt("seatsBooked"));
				
			}
			if(!hasData) {
				System.out.println("No Bookings...");
				
			}
			
		}catch(Exception e) {
			System.out.println("Error in"+e.getMessage());
			
			
		}
		}
	public static void addBooking() {
		Scanner sc=new Scanner(System.in);
		try(Connection con=DbConnection.getConnection()){
			System.out.println("Enter the BusId:");
			int bid=sc.nextInt();
			System.out.println("Enter the Customer Id:");
			int custid=sc.nextInt();
			System.out.println("Enter the Bus no:");
			int busno=sc.nextInt();
			System.out.println("Enter the Seat from:");
			int seatfrom=sc.nextInt();
			System.out.println("Enter the Seat to:");
			int seatto=sc.nextInt();
			System.out.println("Enter the Status:");
			int status=sc.nextInt();
			System.out.println("Enter the Seats Booked:");
			int seatsBooked=sc.nextInt();
			
			String q="insert into booking(bid,custid,busno,seatfrom,seatto,status,seatsBooked)"+"values(?,?,?,?,?,?,?)";
				
		try(PreparedStatement psmt=con.prepareStatement(q)){
			psmt.setInt(1,bid);
			psmt.setInt(2,custid);
			psmt.setInt(3,busno);
			psmt.setInt(4,seatfrom);
			psmt.setInt(5,seatto);
			psmt.setInt(6,status);
			psmt.setInt(7,seatsBooked);
			int inserted=psmt.executeUpdate();
			if(inserted>0) {
			System.out.println("Booked succesfully...\nHappy Journey...");
		}
			else {
				System.out.println("Failed to Book...\nTry again...");
			}
		}
		
		}
		catch(Exception e) {
			System.out.println("Error is:"+e.getMessage());
			
		}
	}
	public static void deleteBooking() {
		try(Connection con=DbConnection.getConnection()){
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the Customer Id to cancel the booking");
			int busId=sc.nextInt();
			String q="delete from booking where bId=?";
			PreparedStatement psmt=con.prepareStatement(q);
			psmt.setInt(1, busId);
			int res=psmt.executeUpdate();
			if(res>0) {
				System.out.println("Deleted successfully...");
			}
			else {
				System.out.println("No Booking for this "+busId);
			}
			
			
		}
		catch(Exception e) {
			System.out.println("Error is:"+e.getMessage());
			
		}
	}
	
	

	
		


}
