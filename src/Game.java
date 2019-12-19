public class Game {
	private int grid;
	private int[][] arr;
	private Canvas canvas;
	
	public Game(int grid) {
		this.grid = grid;
		arr = new int[grid][grid];
		canvas = new Canvas(grid, arr);
	}
	
	public void play() {
		int max = 0;
		canvas.draw();
	}
}
