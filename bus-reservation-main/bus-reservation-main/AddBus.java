package org.bus;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.Timestamp;



	public class AddBus {
		public void execute(int busno,String bname,String routefrom,String routeto,String bType,Timestamp departure,Timestamp arrival,int totalSeats,int availSeats,int fare) {
			try(Connection con=DbConnection.getConnection()){
				String q="insert into bus values(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement psmt=con.prepareStatement(q);
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
				psmt.executeUpdate();
				System.out.println("Inserted....");
			}
			
		
			catch(Exception e) {
				System.out.println("Error is:"+e.getMessage());
				
			}

	}
	}



