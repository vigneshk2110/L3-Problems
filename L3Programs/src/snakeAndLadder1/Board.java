package snakeAndLadder1;

import java.util.HashMap;
import java.util.Random;

public class Board {

	public static void main(String[] args) throws InterruptedException {

		HashMap<Integer, SnakeLadder> snakeLadderMap = new HashMap<>();

		SnakeLadder s1 = new SnakeLadder(12, 24);
		snakeLadderMap.put(12, s1);

		SnakeLadder s2 = new SnakeLadder(15, 9);
		snakeLadderMap.put(15, s2);

		SnakeLadder s3 = new SnakeLadder(32, 58);
		snakeLadderMap.put(32, s3);

		SnakeLadder s4 = new SnakeLadder(25, 94);
		snakeLadderMap.put(25, s4);

		SnakeLadder s5 = new SnakeLadder(45, 89);
		snakeLadderMap.put(45, s5);

		SnakeLadder s6 = new SnakeLadder(54, 19);
		snakeLadderMap.put(54, s6);

		SnakeLadder s7 = new SnakeLadder(65, 74);
		snakeLadderMap.put(65, s7);

		SnakeLadder s8 = new SnakeLadder(85, 29);
		snakeLadderMap.put(85, s8);
		
		SnakeLadder s9 = new SnakeLadder(99, 2);
		snakeLadderMap.put(99, s9);

		int tokenPosition = 0;
		int turns = 0;
		Random roll = new Random();
		while (tokenPosition<100) {	
			
			Thread.sleep(500);
			
			int value = roll.nextInt(6) + 1;

			if (tokenPosition<95) {
				if (value != 6 ) {
					turns++;
				}
				tokenPosition = tokenPosition + value;
				System.out.println("Dice - " + value + "  current position - " + tokenPosition);
			}
			else if (tokenPosition>=95) {
				turns++;
				if (value+ tokenPosition<=100) {
					tokenPosition =value+ tokenPosition;
					System.out.println("Dice - " + value + "  current position - " + tokenPosition);
				}
				else {
					System.out.println("Dice - " + value + "  current position - " + tokenPosition);
				}
			}
			if (snakeLadderMap.containsKey(tokenPosition)) {
				System.out.println("Snake Bite / Ladder climb - has happened");
				tokenPosition = snakeLadderMap.get(tokenPosition).change(tokenPosition);
				System.out.println("current position - " + tokenPosition);
			}
			
			if (tokenPosition == 100) {
				break;
			}
		}
		System.out.println("Player Won in " + turns + " turns");
	}

}
