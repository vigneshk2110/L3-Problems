package bankWIthDB;

public interface CustomerInterface {
	
	void checkBalance(int custID);
	void deposit(int custID);
	void withDraw(int custID);
	void transfer(int custID);
	void viewTransactionHistory(int custID);
	void changeNewPassword(int custID);
	
	static void thisBankName() {
		System.out.println("CRM Bank");
	}

}
