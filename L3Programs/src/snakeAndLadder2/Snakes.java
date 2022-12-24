package snakeAndLadder2;

public class Snakes {
	private int head;
	private int tail;
	
	Snakes(int head, int tail){
		if (tail>head) {
			head = head^tail;
			tail = head^tail;
			head = head^tail;
		}
		this.head = head;
		this.tail = tail;
	}

	public int bite(int tokenPlayer) {
		return tail;
	}
}
