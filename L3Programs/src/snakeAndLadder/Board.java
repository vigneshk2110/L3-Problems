package snakeAndLadder;

import java.util.HashMap;
import java.util.Random;

public class Board {

	public static void main(String[] args) {

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
		int tokenPosition = 0;
		int turnCount = 0;
		while (tokenPosition <100) {
			int value = roll.nextInt(6);
			value++;
			if (tokenPosition<95) {
				tokenPosition = tokenPosition+value;
				if (value != 6) {
					turnCount++;
				}
				System.out.print("Dice -->" + value +", ");
				System.out.print("current position - player 1 " + "= " + tokenPosition);
			}
			else if (tokenPosition>=95) {
				turnCount++;
				if(tokenPosition+value <= 100) {
					tokenPosition = tokenPosition + value;
					System.out.print("Dice -->" +value +", ");
					System.out.print("current position - player 1 " + "= " + tokenPosition);
				}
				else {
					System.out.print("Dice -->" +value +", ");
				}
			}
			if (ladderMap.get(tokenPosition) != null) {
				tokenPosition = ladderMap.get(tokenPosition).climb(tokenPosition);
				System.out.print(" ladder climb -->"  + tokenPosition);
			}
			else if (snakeMap.get(tokenPosition) != null) {
				tokenPosition = snakeMap.get(tokenPosition).bite(tokenPosition);
				System.out.print(" Snake Bite --> " + tokenPosition);
			}
			System.out.println();
			if (tokenPosition == 100) {
				break;
			}
		}
		
		System.out.println("Player1 Won---------------------------");
		System.out.println("no of turns = " + turnCount);
		System.out.println("current position - player 1" + "= " + tokenPosition);
	}
}
