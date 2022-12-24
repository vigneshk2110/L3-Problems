package snakeAndLadder;

public class Snakes {
	private int head;
	private int tail;
	
	Snakes(int head, int tail){
		if (tail>head) {
			int temp = head;
			head = tail;
			tail = temp;
		}
		this.head = head;
		this.tail = tail;
	}

	public int bite(int tokenPlayer) {
		return tail;
	}
}
