package bankWIthDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Bank implements BankInterFace {

	@Override
	public void addCustomer() {
		try {
			DBuser.addCustomer();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}		
	}

	@Override
	public void topNcustomers() {
		try {
			DBuser.topNcustomers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewCustomers() {
		try {
			DBuser.viewAllCustomers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void viewAllTransactions() {
		try {
			DBuser.viewAllTransactions();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	protected static boolean adminLogin() {
		System.out.println("Enter the UserName");
		Scanner sc = new Scanner(System.in);
		String uNameString = sc.next();
		System.out.println("Enter the Password");
		String passWord = sc.next();
		if (uNameString.equals("admin") && passWord.equals("admin")) {
			return true;
		}
		return false;
	}
	
	protected static void bankerUI() throws InterruptedException{
		System.out.println("Redirecting to Banker's Page, Please wait!!!");
//		Thread.sleep(1500);
		System.out.println("Welcome to the Banker's Portal");
		Scanner sc = new Scanner(System.in);
		int option = 0;
		Bank bank1 = new Bank();
		do {
			System.out.println("\nPlease select the desired option "
					+ "\n1.Add New Customer"
					+ "\n2.View Top N Customers"
					+ "\n3.View all Customers"
					+ "\n4.View all Transactions");
			option = sc.nextInt();


			switch (option) {
			case 1:
				bank1.addCustomer();
				break;

			case 2:
				bank1.topNcustomers();
				break;
				
			case 3:
				bank1.viewCustomers();
				break;
				
			case 4:
				bank1.viewAllTransactions();
				break;

			default:
				break;
			}

		} while (option >= 1 && option<=4);
		System.out.println("Thanks, Visit Again!!!");

	}
	

}
