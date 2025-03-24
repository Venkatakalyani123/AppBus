package org.bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;


public class BusReservationSystem {
	private static final String admin_user="admin";
	private static final String admin_pass="adminrs1";
		
	
		public static void viewBus() {
		try(Connection con=DbConnection.getConnection()){
			Statement smt=con.createStatement();
			String q="select * from bus";
			ResultSet rs=smt.executeQuery(q);
			System.out.printf("%-10s%-20s%-15s%-10s%-10s%-15s%-30s%-35s%-15s%-10s%n","Busno","Busname","Routefrom","Routeto","BusType","Departure","Arrival","Totalseats","AvailableSeats","Fare");
			boolean hasData=false;
			while(rs.next()) {
				hasData=true;
				System.out.printf("%-10s%-20s%-15s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n",
				rs.getInt("busno"),
				rs.getString("bname"),
				rs.getString("routefrom"),
				rs.getString("routeto"),
				rs.getString("bType"),
				rs.getTimestamp("departure"),
				rs.getTimestamp("arrival"),
				rs.getInt("totalseats"),
				rs.getInt("availseats"),
				rs.getInt("fare"));
				
			}
			if(!hasData) {
				System.out.println("Buses not yet added...");
				
			}
			
		}catch(Exception e) {
			System.out.println("Error in"+e.getMessage());
			
			
		}
		}
		public static void addBus() {
			Scanner sc=new Scanner(System.in);
			try(Connection con=DbConnection.getConnection()){
				System.out.println("Enter the BusNo:");
				int busno=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the BusName:");
				String bname=sc.nextLine();
				System.out.println("Enter the Route From:");
				String routefrom=sc.nextLine();
				System.out.println("Enter the Route To:");
				String routeto=sc.nextLine();
				System.out.println("Enter the Bus Type:");
				String bType=sc.nextLine();
				System.out.println("Enter the Departure Time(yyyy-mm-dd hh:mm:ss):");
				String departureInput=sc.nextLine();
				System.out.println("Enter the Arrival Time(yyyy-mm-dd hh:mm:ss):");
				String arrivalInput=sc.nextLine();
				
				Timestamp departure=Timestamp.valueOf(departureInput);
				Timestamp arrival=Timestamp.valueOf(arrivalInput);
				
				System.out.println("Enter the Total Seats:");
				int totalSeats=sc.nextInt();
				System.out.println("Enter the Available Seats:");
				int availSeats=sc.nextInt();
				System.out.println("Enter the Bus Fare:");
				int fare=sc.nextInt();
				
				String q="insert into bus(busno, bname,routefrom,routeto,bType,departure,arrival,totalSeats,availSeats,fare)"+"values(?,?,?,?,?,?,?,?,?,?)";
					
			try(PreparedStatement psmt=con.prepareStatement(q)){
				psmt.setInt(1,busno);
				psmt.setString(2,bname);
				psmt.setString(3,routefrom);
				psmt.setString(4,routeto);
				psmt.setString(5,bType);
				psmt.setTimestamp(6,departure);
				psmt.setTimestamp(7,arrival);
				psmt.setInt(8,totalSeats);
				psmt.setInt(9,availSeats);
				psmt.setInt(10,fare);
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
		public static void updateBus() {
			try(Connection con=DbConnection.getConnection()){
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the bus no to be updated:");
				int busno=sc.nextInt();
				
				String q="select b.totalSeats,(select seatsbooked from booking where busno=?)as seatsBooked from bus b where b.busno=?";
				try(PreparedStatement psmt=con.prepareStatement(q)){;
				psmt.setInt(1,busno);
				psmt.setInt(2,busno);
				try(ResultSet rs=psmt.executeQuery()){
					boolean hasData=false;
				while(rs.next()) {
					hasData=true;
					int totalseats=rs.getInt("totalSeats");
					int seatsBooked=rs.getInt("seatsBooked");
					int availseats=totalseats-seatsBooked;
					
					String query="update bus set availseats=? where busno=?";
					PreparedStatement uPsmt=con.prepareStatement(query);
					uPsmt.setInt(1,availseats);
					uPsmt.setInt(2,busno);
					int res=uPsmt.executeUpdate();
					if(res>0) {
						System.out.println("Updated successfully...");
					}else {
						System.out.println("No rows updated...");
					}
				}
				if(!hasData) {
					System.out.println("The bus no not found for "+busno);
				}
				}
				}
				
				
			}catch(Exception e) {
				System.out.println("Error in:"+e.getMessage());
			}
			
		}

		public static void deleteBus() { 
			try(Connection con=DbConnection.getConnection()){
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the Bus no to be deleted");
				int busno=sc.nextInt();
				String q="delete from bus where busno=?";
				PreparedStatement psmt=con.prepareStatement(q);
				psmt.setInt(1, busno);
				int res=psmt.executeUpdate();
				if(res>0) {
					System.out.println("Deleted successfully...");
				}
				else {
					System.out.println("The Bus no not found for "+busno);
				}
				
			} catch (SQLException e) {
				System.out.println("Error in:"+e.getMessage());
			}
		
}	
}



