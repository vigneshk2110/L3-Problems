package bankWIthDB;

public interface BankInterFace {
	
	void addCustomer();
	void topNcustomers();
	void viewCustomers();
	
	default void interestRate() {
		System.out.println("6.5%");
	}
	
	static void thisBankName() {
		System.out.println("CRM Bank");
	}

}
