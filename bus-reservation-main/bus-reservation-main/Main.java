package org.bus;

import java.util.Scanner;

public class Main {
	private static final String admin_user="admin";
	private static final String admin_pass="adminrs1";
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("Welcome to AA Travels");
			System.out.println("1.Bus Management Menu");
			System.out.println("2.Booking Management Menu");
			System.out.println("3.Customer Management Menu");
			System.out.println("4.Exit");
			
			int c=sc.nextInt();
			switch(c) {
			case 1:
				busManagementMenu(sc);
				break;
			case 2:
				bookingManagementMenu(sc);
				break;
			case 3:
				customerManagementMenu(sc);
				break;
			case 4:
				System.out.println("Thank you visit again!!!");
				sc.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice,please try again...");
			}
		}
	}
	public static boolean adminAuthentication() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Admin Username:");
		String username=sc.nextLine();
		System.out.println("Enter the Admin Password:");
		String password=sc.nextLine();
		return admin_user.equals(username)&&admin_pass.equals(admin_pass);
	}
	
	public static void busManagementMenu(Scanner sc) {
		while(true) {
			System.out.println("Bus Management");
			System.out.println("1.View Buses");
			System.out.println("2.Add buses(For admins use)");
			System.out.println("3.Update buses(For admins use)");
			System.out.println("4.Delete buses(For admins use)");
			System.out.println("5.Go Back To Main Menu");
			int c=sc.nextInt();
			switch(c) {
			case 1:
			     BusReservationSystem.viewBus();
			     break;
			case 2:
				if(adminAuthentication())
				 BusReservationSystem.addBus();
				else
				System.out.println("Check your username and password...");
				 break;
			case 3:
				if(adminAuthentication())
                  BusReservationSystem.updateBus();
				else
				System.out.println("Check your username and password...");
				break;
			case 4:
				if(adminAuthentication())
				BusReservationSystem.deleteBus();
			case 5:
				return;
			default:
				System.out.println("Invalid choice");
			    	 
			   }
		}
	}
	public static void bookingManagementMenu(Scanner sc) {
		while(true) {
			System.out.println("Booking Management");
			System.out.println("1.Add Booking");
			System.out.println("2.View Booking Details");
			System.out.println("3.Delete Booking");
			System.out.println("4.Go Back To Main Menu");
			int c=sc.nextInt();
			switch(c) {
			case 1:
				BookingSystem.addBooking();
				break;
			case 2:
				BookingSystem.viewBooking();
				break;
			case 3:
				BookingSystem.deleteBooking();
				break;
			case 4:
				return ;
			default:
				System.out.println("Invalid choice");
			}
		}
	}
	
	public static void customerManagementMenu(Scanner sc) {
		while(true) {
			System.out.println("Customer Management");
			System.out.println("1.Add customer Details");
			System.out.println("2.View customer Details");
			System.out.println("3.Go Back To Main Menu");
			int c=sc.nextInt();
			switch(c) {
			case 1:
				CustomerManagement.addCustomers();
				break;
			case 2:
				CustomerManagement.viewCustomers();
				break;
			case 3:
				return ;
			default:
				System.out.println("Invalid choice");
			}
		}
	}


}
