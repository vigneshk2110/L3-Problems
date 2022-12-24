package bankWIthDB;

import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {

	protected static Connection con;
	protected static  ResultSet rs;
	protected static Statement st;
	protected static PreparedStatement preSt;
	protected static CallableStatement callSt;
	protected static String query = "";

	protected static void connectDB() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/customers";
		String uName = "root";
		String passWord = "root";
		con = DriverManager.getConnection(url, uName, passWord);

	}

	protected static void closeDB() throws SQLException {
		if (!(st==null)) {
			st.close();
		}
		if (!(preSt==null)) {
			preSt.close();
		}
		if (!(callSt==null)) {
			callSt.close();
		}
		if (!(con==null)) {
			con.close();
		}

	}

	protected String getName(int custID) throws ClassNotFoundException, SQLException {
		connectDB();
		String name = "";
		query="SELECT AccName  FROM customers.customerstable where custId = " + custID+ ";";
		rs = st.executeQuery(query);
		if (rs.next()) {
			name = rs.getString("Accname");
		}
		closeDB();
		return name;
	}

	protected int getBalance(int custID) throws ClassNotFoundException, SQLException {
		connectDB();
		int balance = 0;
		query="SELECT *  FROM customers.customerstable where custId = " + custID+ ";";
		st = con.createStatement();
		rs = st.executeQuery(query);
		if (rs.next()) {
			balance = rs.getInt("AccBal");
		}
		closeDB();
		return balance;
	}

	protected void setBalance(int custID, int newAmt) throws ClassNotFoundException, SQLException {
		connectDB();
		query = "update customers.customerstable set AccBal = "+ (getBalance(custID) + newAmt) +" where custId ="+  custID + ";";
		st.execute(query);
		closeDB();
	}

	protected static String getPassword(int custID) throws ClassNotFoundException, SQLException {
		connectDB();
		String passWord = "";
		query = "SELECT AccPwd1, AccPwd2, AccPwd3  FROM customers.customerstable where custId = " + custID+ ";";
		st = con.createStatement();
		rs = st.executeQuery(query);
		while (rs.next()) {
			passWord = rs.getString("AccPwd3");
			if (passWord.equals("-")) {
				passWord = rs.getString("AccPwd2");
				if (passWord.equals("-")) {
					passWord = rs.getString("AccPwd1");

				}
			}
		}
		closeDB();
		return passWord;
	}

	protected void setNewPassword(int custID, String newPassword) throws ClassNotFoundException, SQLException {
		connectDB();
		query = "SELECT AccPwd1, AccPwd2, AccPwd3  FROM customers.customerstable where custId = " + custID+ ";";
		st = con.createStatement();
		rs = st.executeQuery(query);
		if(rs.next()) {	

			if (rs.getString("AccPwd2").equals("-") && rs.getString("AccPwd3").equals("-")) {
				if (!rs.getString("AccPwd1").equals(newPassword)) {
					query = "update customers.customerstable set AccPwd2 = '"+ newPassword +"' where custId ="+  custID + ";";
					st.execute(query);
				}
				else {
					System.out.println("Same Password already exist, please try New Password");
					changePassword(custID);
				}

			}else if (rs.getString("AccPwd3").equals("-")) {

				if (!rs.getString("AccPwd1").equals(newPassword) && !rs.getString("AccPwd2").equals(newPassword) ) {

					query = "update customers.customerstable set AccPwd3 = '"+ newPassword +"' where custId ="+  custID + ";";
					st.execute(query);
				}
				else {
					System.out.println("Same Password already exist, please try New Password");
					changePassword(custID);
				}

			}else if (!rs.getString("AccPwd1").equals(newPassword) && !rs.getString("AccPwd2").equals(newPassword) &&
					!rs.getString("AccPwd3").equals(newPassword)) {

				String p2 = rs.getString("AccPwd2");
				String p3 = rs.getString("AccPwd3");

				query = "update customers.customerstable set AccPwd1 = '"+ p2 +"' where custId ="+  custID + ";";
				st.execute(query);

				query = "update customers.customerstable set AccPwd2 = '"+ p3 +"' where custId ="+  custID + ";";
				st.execute(query);

				query = "update customers.customerstable set AccPwd3 = '"+ newPassword +"' where custId ="+  custID + ";";
				st.execute(query);

			}

		}
		closeDB();
	}

	protected void getTransactionsHistory(int custID) throws ClassNotFoundException, SQLException {
		connectDB();
		query="SELECT * FROM customers.transactions_customer where custID ="+ custID +" order by TransactionID Desc Limit 5;";
		rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println();
		}
		closeDB();
	}

	protected void addTransaction(int custID, String transaction) throws ClassNotFoundException, SQLException {
		connectDB();
		query="";
		rs = st.executeQuery(query);
		rs.next();
		closeDB();
	}

	protected int getLastID() throws ClassNotFoundException, SQLException {
		connectDB();
		int custID = 0;
		query="{call getLastCustID(?)}";
		callSt = con.prepareCall(query);
		callSt.registerOutParameter(1, Types.INTEGER);
		callSt.executeUpdate();
		custID =callSt.getInt(1);
		closeDB();
		return custID;		

	}

	protected int getLastAccNum() throws ClassNotFoundException, SQLException {
		connectDB();
		int AccNum = 0;
		query = "select AccNum from customers.customerstable  ORDER BY CUSTID DESC LIMIT 1;";
		st = con.createStatement();
		rs = st.executeQuery(query);
		while (rs.next()) {
			AccNum = rs.getInt("AccNum");
		}
		closeDB();
		return AccNum;		

	}

	protected int customerLogin(int custID, String passWord, Scanner sc) throws ClassNotFoundException, SQLException {

		while (!DBuser.validatePassword(passWord)) {
			System.out.println("Enter the Right Password");
			passWord = sc.next();
		}
		passWord = DBuser.passwordEncrypt(passWord);

		connectDB();

		query = "SELECT * FROM customers.customerstable where custID = "+custID+";";
		st = con.createStatement();
		rs = st.executeQuery(query);
		String userPass ="";
		if (rs.next()) {
			userPass = DatabaseConnection.getPassword(custID);
		}
		if (passWord.equals(userPass)) {
			return custID;
		}

		closeDB();


		return 0;
	}

	protected void depositBank(int cusID) throws ClassNotFoundException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Amount to be deposited");
		double depositAmt = sc.nextDouble();
		double oldBalance = getBalance(cusID);
		double newBalance = depositAmt+oldBalance;
		int transactionIDself = getLastTransactionID(cusID) + 1;

		connectDB();
		query = "update customers.customerstable set AccBal = "+ newBalance +" where custId ="+  cusID + ";";
		st = con.createStatement();
		st.executeUpdate(query);

		query = "INSERT INTO customers.transactions VALUES"
				+ " ("+cusID +", "+ transactionIDself +", now(),'Deposit ',"+ depositAmt +", "+ newBalance +" );";
		st = con.createStatement();
		st.executeUpdate(query);

		closeDB();
	}

	public void changePassword(int cusID) throws ClassNotFoundException, SQLException {

		System.out.println("Enter the new Password");
		Scanner sc = new Scanner(System.in);
		String newPassword = sc.next();		
		int transactionIDself = getLastTransactionID(cusID) + 1;

		while (!DBuser.validatePassword(newPassword)) {
			System.out.println("Enter the Password in RIGHT Format");
			newPassword = sc.next();
		}
		newPassword = DBuser.passwordEncrypt(newPassword);


		setNewPassword(cusID, newPassword);


		query = "INSERT INTO customers.transactions VALUES"
				+ " ("+cusID +", "+ transactionIDself +", now(),'Password Changed ', 0 , "+ getBalance(cusID) +" );";
		connectDB();
		st = con.createStatement();
		st.executeUpdate(query);
		closeDB();

	}

	protected void withDrawBank(int cusID) throws ClassNotFoundException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Amount to be Withdrawn");
		double withDrawAmt = sc.nextDouble();
		double oldBalance = getBalance(cusID);
		
		while (withDrawAmt>(oldBalance-1000)) {
			System.out.println("Insufficient Balance, PLease enter Lesser amount.");
			withDrawAmt = sc.nextDouble();
		}

		double newBalance = oldBalance-withDrawAmt;
		int transactionIDself = getLastTransactionID(cusID) + 1;

		connectDB();
		query = "update customers.customerstable set AccBal = "+ newBalance +" where custId ="+  cusID + ";";
		st = con.createStatement();
		st.executeUpdate(query);

		query = "INSERT INTO customers.transactions VALUES"
				+ " ("+cusID +", "+ transactionIDself +", now(),'Withdrawl ',"+ withDrawAmt +", "+ newBalance +" );";
		st = con.createStatement();
		st.executeUpdate(query);

		closeDB();


	}

	protected void transfer(int cusID) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Beneficiery Cust");
		int beneficiaryID = sc.nextInt();

		connectDB();

		query = "SELECT * FROM customers.customerstable where custID = "+beneficiaryID+";";
		st = con.createStatement();
		rs = st.executeQuery(query);

		while (!rs.next()) {
			System.out.println("Enter the RIGHT Beneficiery CustID");
			beneficiaryID = sc.nextInt();

			query = "SELECT * FROM customers.customerstable where custID = "+beneficiaryID+";";
			st = con.createStatement();
			rs = st.executeQuery(query);
		}

		closeDB();		

		System.out.println("Enter the Amount to be transfered");
		double transferAmt = sc.nextDouble();
		double oldSelfBalance = getBalance(cusID);
		double oldBeneficiaryBalance = getBalance(beneficiaryID);

		while (transferAmt>(oldSelfBalance-1000)) {
			System.out.println("Insufficient Balance, PLease enter Lesser amount.");
			transferAmt = sc.nextDouble();
		}

		double newBeneficaryBal = oldBeneficiaryBalance + transferAmt;
		double newSelfBalance = oldSelfBalance - transferAmt;
		int transactionIDbeneficiary = getLastTransactionID(beneficiaryID) + 1;
		int transactionIDself = getLastTransactionID(cusID) + 1;

		connectDB();
		query = "update customers.customerstable set AccBal = "+ newSelfBalance +" where custId ="+  cusID + ";";
		st = con.createStatement();
		st.executeUpdate(query);

		query = "INSERT INTO customers.transactions VALUES"
				+ " ("+cusID +", "+ transactionIDself +", now(),'Transfered to " + beneficiaryID +"',"+ transferAmt +", "+ newSelfBalance +" );";
		st = con.createStatement();
		st.executeUpdate(query);

		query = "update customers.customerstable set AccBal = "+ newBeneficaryBal +" where custId ="+  beneficiaryID + ";";
		st = con.createStatement();
		st.executeUpdate(query);

		query = "INSERT INTO customers.transactions VALUES"
				+ " ("+beneficiaryID +", "+ transactionIDbeneficiary +", now(),'Transfered from " + cusID +"',"+ transferAmt +", "+ newBeneficaryBal +" );";
		st = con.createStatement();
		st.executeUpdate(query);

		closeDB();
	}

	private int getLastTransactionID(int beneficiaryID) throws ClassNotFoundException, SQLException {
		connectDB();
		query="SELECT * FROM customers.transactions where custID = "+ beneficiaryID +" order by TransactionID  desc ;"; // Setup Bank Accounts Using Call able Statements
		st = con.createStatement();
		rs = st.executeQuery(query);
		if (rs.next()) {
			return rs.getInt("TransactionID");
		}
		closeDB();
		return 0;
	}

	protected void viewTransaction(int cusID) throws ClassNotFoundException, SQLException {
		connectDB();
		query="SELECT * FROM customers.transactions where custID = "+ cusID +" order by TransactionID  desc ;"; // Setup Bank Accounts Using Call able Statements
		st = con.createStatement();
		rs = st.executeQuery(query);
		if (rs.next()) {
			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("			Transaction List");
			System.out.println("---------------------------------------------------------------------");
			System.out.print("CustID" + "\t\t");
			System.out.print("Date & Time" + "\t");
			System.out.print("Transaction" + "\t");
			System.out.print("   Amount");
			System.out.println("   Balance");
			System.out.println("---------------------------------------------------------------------");
		}
		do {
			System.out.print(" "+rs.getInt("custID") + "\t");
			java.sql.Timestamp timestampObj = rs.getTimestamp("DateAndTime");
			System.out.print(timestampObj.toString() + "\t");
			System.out.print(rs.getString("transactionHistory") + "\t");
			System.out.print("    "+rs.getInt("Amount"));
			System.out.println("    "+rs.getInt("Balance"));

		} while (rs.next()); 
		System.out.println("---------------------------------------------------------------------");
		closeDB();	

	}

}

