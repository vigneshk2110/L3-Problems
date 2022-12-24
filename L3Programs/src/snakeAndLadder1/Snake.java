package snakeAndLadder1;

public class Snake {
	
	private int head;
	private int tail;
	
	Snake(int from, int to) {
		this.head = from;
		this.tail = to;
	}
	
	public int snakeBite(int from) {
		return this.tail;
	}
	
}
