package org.bus;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AddBooking {
	public void execute(int bId,int custId,int busno,int seatFrom,int seatTo,int status) {
		try(Connection con=DbConnection.getConnection()){
			String q="insert into bus values(?,?,?,?,?,?)";
			PreparedStatement psmt=con.prepareStatement(q);
			psmt.setInt(1,bId);
			psmt.setInt(2,custId);
			psmt.setInt(3,busno);
			psmt.setInt(4,seatFrom);
			psmt.setInt(5,seatTo);
			psmt.setInt(6,status);
			psmt.executeUpdate();
			System.out.println("Inserted....");
		}
		
	
		catch(Exception e) {
			System.out.println("Error is:"+e.getMessage());
			
		}

}



}
