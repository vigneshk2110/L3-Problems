package snakeAndLadder2;

import java.util.HashMap;
import java.util.Random;

public class Board {

	public static void main(String[] args) throws InterruptedException {

		HashMap<Integer, Snakes> snakeMap = new HashMap<>();
		HashMap<Integer, Ladder> ladderMap = new HashMap<>();

		Snakes s1 = new Snakes(99, 41);
		snakeMap.put(99, s1);
		Snakes s2 = new Snakes(89, 53);
		snakeMap.put(89, s2);
		Snakes s3 = new Snakes(76, 58);
		snakeMap.put(76, s3);
		Snakes s4 = new Snakes(66, 45);
		snakeMap.put(66, s4);
		Snakes s5 = new Snakes(54, 31);
		snakeMap.put(54, s5);
		Snakes s6 = new Snakes(42, 18);
		snakeMap.put(42, s6);
		Snakes s7 = new Snakes(40, 3);
		snakeMap.put(40, s7);
		Snakes s8 = new Snakes(27, 5);
		snakeMap.put(27, s8);

		Ladder l1 = new Ladder(4, 25);
		ladderMap.put(4, l1);
		Ladder l2 = new Ladder(13, 46);
		ladderMap.put(13, l2);
		Ladder l3 = new Ladder(33, 49);
		ladderMap.put(33, l3);
		Ladder l4 = new Ladder(42, 63);
		ladderMap.put(42, l4);
		Ladder l5 = new Ladder(50, 69);
		ladderMap.put(50, l5);
		Ladder l6 = new Ladder(62, 81);
		ladderMap.put(62, l6);
		Ladder l7 = new Ladder(74, 92);
		ladderMap.put(74, l7);

		Random roll = new Random();
		int tokenPlayer1 = 0;
		int turnCount = 0;
		while (tokenPlayer1 <100) {
			
			Thread.sleep(1250);			
			
			
			int value = roll.nextInt(6);
			value++;
			if (tokenPlayer1<95) {
				tokenPlayer1 = tokenPlayer1+value;
				if (value != 6) {
					turnCount++;
				}
//				System.out.print("Dice -->" +value +", ");
				DiceRoll.printDice(value);
				System.out.println("current position - player 1 " + "= " + tokenPlayer1);
				DiceRoll.printBoard(tokenPlayer1);
			}
			else if (tokenPlayer1>=95) {
				turnCount++;
				if(tokenPlayer1+value <= 100) {
					tokenPlayer1 = tokenPlayer1 + value;
//					System.out.print("Dice -->" +value +", ");
					DiceRoll.printDice(value);
					System.out.println("current position - player 1 " + "= " + tokenPlayer1);
					DiceRoll.printBoard(tokenPlayer1);
				}
				else {
//					System.out.print("Dice -->" +value +", ");
					DiceRoll.printDice(value);
				}
			}
			if (ladderMap.get(tokenPlayer1) != null) {
				tokenPlayer1 = ladderMap.get(tokenPlayer1).climb(tokenPlayer1);
				System.out.println(" ladder climb -->"  + tokenPlayer1);
				DiceRoll.printBoard(tokenPlayer1);
			}
			else if (snakeMap.get(tokenPlayer1) != null) {
				tokenPlayer1 = snakeMap.get(tokenPlayer1).bite(tokenPlayer1);
				System.out.println(" Snake Bite --> " + tokenPlayer1);
				DiceRoll.printBoard(tokenPlayer1);
			}
			System.out.println();
			if (tokenPlayer1 == 100) {
				break;
			}
		}
		System.out.println("Player1 Won---------------------------");
		System.out.println("no of turns = " + turnCount);
		System.out.println("current position - player 1" + "= " + tokenPlayer1);
	}
}
