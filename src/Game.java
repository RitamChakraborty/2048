import java.util.ArrayList;
import java.util.List;

public class Game {
	private int[][] arr;
	private Canvas canvas;
	
	private static class Position {
		int i;
		int j;
		
		public Position(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public Game(int grid) {
		arr = new int[grid][grid];
		canvas = new Canvas(grid, arr);
	}
	
	public void play() throws Exception {
		int max = 0;
		
		while (max != 2048) {
			List<Position> emptyPositions = getEmptyPositions();
			int length = emptyPositions.size();
			
			if (length != 0) {
				// Get a random position where you can put the new number
				int random = (int) Math.round(Math.random() * (length - 1));
				Position randomPosition = emptyPositions.get(random);
				// New Number should be 2 or 4
				int num = (int) Math.round(Math.random() * 1) == 0
						          ? 2
						          : 4;
				arr[randomPosition.i][randomPosition.j] = num;
				canvas.draw();
				Thread.sleep(1000);
			} else {
				break;
			}
		}
	}
	
	// Get the positions where you can place new number
	private List<Position> getEmptyPositions() {
		List<Position> positions = new ArrayList<>();
		
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				if (arr[i][j] == 0) {
					positions.add(new Position(i, j));
				}
			}
		}
		
		return positions;
	}
}
