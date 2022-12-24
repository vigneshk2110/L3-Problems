package snakeAndLadder2;

public class Ladder {
	private int bottom;
	private int top;
	
	Ladder(int bottom, int top) {
		if (bottom>top) {
			bottom = bottom^top;
			top = bottom^top;
			bottom = bottom^top;
		}
		this.bottom = bottom;
		this.top = top;
	}

	public int climb(int x) {
		return this.top;
	}
}
