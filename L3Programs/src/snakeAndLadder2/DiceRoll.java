package snakeAndLadder2;

public class DiceRoll {

	public static void main(String[] args) {
//				Random roll = new Random();
//				int position = 0;
//				int i = 0;
//				while (position <=94) {
//					int value = roll.nextInt(6);
//					value++;
//					position = position+value;
//					//			System.out.println("Roll " +i + "= " +value);
//					printDice(value);
//					System.out.println("current position " + "= " + position);
//					i++;
//				}
//		
//		
//				System.out.println("no of die roles = " + --i);
	

	}
	
	
	public static  void printBoard(int position) {
		String[][] board = new String[12][12];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (i == 0 || i == 11) {
					board[i][j] =" --- ";
				}
				else if (j == 0 || j == 11) {
					
					board[i][j] ="  |  ";
				}
				else if (j == 1 && i == 1) {
					board[i][j] =" Win ";
				}
				else {
					board[i][j] ="  .  ";
				}
			}
		}
		position--;
		int row = 0;
		int column = 0;
		if((position/10)%2==0)
			
		{
			row = 10-(position/10);
			column = position%10+1;
		}
		else {
			row = 10-(position/10);
			column = 10-position%10;
		}
		
		
		board[row][column] =" \u2764  ";


		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {

				System.out.print(board[i][j]);
			}
			System.out.println("\n");
		}

	}



	public static  void printDice(int value) {
		System.out.println();
		if (value == 1) {
			System.out.println("* * * * *");
			System.out.println("*       *");
			System.out.println("*   #   *");
			System.out.println("*       *");
			System.out.println("* * * * *");
		}

		if (value == 2) {
			System.out.println("* * * * *");
			System.out.println("*    #  *");
			System.out.println("*       *");
			System.out.println("*  #    *");
			System.out.println("* * * * *");
		}
		if (value == 3) {

			System.out.println("* * * * *");
			System.out.println("*    #  *");
			System.out.println("*   #   *");
			System.out.println("*  #    *");
			System.out.println("* * * * *");
		}

		if (value == 4) {
			System.out.println("* * * * *");
			System.out.println("*  # #  *");
			System.out.println("*       *");
			System.out.println("*  # #  *");
			System.out.println("* * * * *");
		}

		if (value == 5) {

			System.out.println("* * * * *");
			System.out.println("*  # #  *");
			System.out.println("*   #   *");
			System.out.println("*  # #  *");
			System.out.println("* * * * *");
		}
		if (value == 6) {

			System.out.println("* * * * *");
			System.out.println("*  # #  *");
			System.out.println("*  # #  *");
			System.out.println("*  # #  *");
			System.out.println("* * * * *");
		}
		System.out.println();


	}


	

	
	

}



