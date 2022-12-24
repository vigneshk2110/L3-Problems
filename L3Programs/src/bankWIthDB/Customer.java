package bankWIthDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends DBuser implements CustomerInterface {

//	private int custID = 0;
//	private int AccNo = 0;
//	private double AccBal = 10000;
//	private String accName;
//	private String accPwd1;
//	private String accPwd2;
//	private String accPwd3;
	private static DatabaseConnection dbc = new DatabaseConnection();
	private static Customer cstmr = new Customer();


//	protected Customer(int custID, int accNo, double accBal, String accName, String accPwd1, String accPwd2,
//			String accPwd3) {
//		super();
//		this.custID = custID;
//		AccNo = accNo;
//		AccBal = accBal;
//		this.accName = accName;
//		this.accPwd1 = accPwd1;
//		this.accPwd2 = accPwd2;
//		this.accPwd3 = accPwd3;
//	}

	protected Customer() {

	}

	public static int customerLogin() throws ClassNotFoundException, SQLException {
		System.out.println("Enter the CustID");
		Scanner sc = new Scanner(System.in);
		int custID = sc.nextInt();

		System.out.println("Enter the Password");
		String passWord = sc.next();		

		return dbc.customerLogin(custID, passWord,sc);

	}

	protected static void customerUI(int cusID) throws InterruptedException, ClassNotFoundException, SQLException {
		System.out.println("Redirecting to Customer's Page, Please wait!!!");
		Thread.sleep(1500);
		System.out.println("Welcome to the Customer's Portal");
		Scanner sc = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("Please select the desired option "
					+ "\n1.Check Balance"
					+ "\n2.Deposit"
					+ "\n3.Withdraw"
					+ "\n4.Money Transfer"
					+ "\n5.View Transactions"
					+ "\n6.Change Account Password");
			option = sc.nextInt();

				switch (option) {
				case 1:
					cstmr.checkBalance(cusID);					
					break;

				case 2:
					cstmr.deposit(cusID);
					break;

				case 3:
					cstmr.withDraw(cusID);						
					break;

				case 4:		
					cstmr.transfer(cusID);
					break;

				case 5:
					cstmr.viewTransactionHistory(cusID);
					
					break;

				case 6:
					cstmr.changeNewPassword(cusID);
					break;
				default:
					break;
				}
		}
		while (option >= 1 && option<=6 );
		System.out.println("Thanks, Visit Again!!!");
	}

	@Override
	public void checkBalance(int custID) {
		try {
			int balance = dbc.getBalance(custID);
			System.out.println("Account Balance - Rs." + balance + "\n");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void transfer(int cusID) {
		try {
			dbc.transfer(cusID);
			int balance3 = dbc.getBalance(cusID);
			System.out.println("New Balance - Rs." + balance3 + "\n" );
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void viewTransactionHistory(int custID) {
		try {
			dbc.viewTransaction(custID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(int custID) {
		try {
			dbc.depositBank(custID);
			int	balance1 = dbc.getBalance(custID);
			System.out.println("Successfully Deposited. New Balance - Rs." + balance1 + "\n" );
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void withDraw(int custID) {
		try {
			dbc.withDrawBank(custID);
			int balance2 = dbc.getBalance(custID);
			System.out.println("New Balance after Withdrawl - Rs." + balance2 + "\n" );
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void changeNewPassword(int custID) {
		try {
			dbc.changePassword(custID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}	





