package canvas;

import java.util.StringJoiner;

/**
 * Use to draw the board
 */
public class Canvas {
	private int grid;
	private int[][] arr;
	
	/**
	 * @param grid size of the board
	 * @param arr  position of all the numbers in the board
	 */
	public Canvas(int grid, int[][] arr) {
		this.grid = grid;
		this.arr = arr;
	}
	
	/**
	 * Draw the board
	 */
	public void draw() {
		CharSequence separator = "\n";
		StringJoiner stringJoiner = new StringJoiner(separator);
		
		for (int i = 0; i < grid; i++) {
			StringJoiner stringJoiner1 = new StringJoiner(" ", " ", " ");
			
			for (int j = 0; j < grid; ++j) {
				CharSequence charSequence = "------";
				stringJoiner1.add(charSequence);
			}
			
			String cover = stringJoiner1.toString();
			stringJoiner.add(cover);
			stringJoiner1 = new StringJoiner("|", "|", "|");
			
			for (int j = 0; j < grid; ++j) {
				String num = arr[i][j] == 0
						             ? "      "
						             : " " + String.format("%4d", arr[i][j]) + " ";
				stringJoiner1.add(num);
			}
			
			stringJoiner.add(stringJoiner1.toString());
			
			if (i == grid - 1) {
				stringJoiner.add(cover);
			}
		}
		
		System.out.println("\n" + stringJoiner.toString() + "\n");
	}
}
