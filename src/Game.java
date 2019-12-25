import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private int grid;
	private int[][] arr;
	private Canvas canvas;
	
	// Position class keeps the position of row and column
	private static class Position {
		int i;
		int j;
		
		public Position(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public Game(int grid) {
		this.grid = grid;
		arr = new int[grid][grid];
		canvas = new Canvas(grid, arr);
	}
	
	public void play() {
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
				max = takeUserAction();
				
				if (max == 2048) {
					System.out.println("You've won!");
				}
			} else {
				System.out.println("Game Over!");
				break;
			}
		}
	}
	
	private void moveLeft() {
		for (int i = 0; i < grid; ++i) {
			for (int j = 0; j < grid; ++j) {
				boolean found = false;
				
				// Check if there are same numbers in the same row
				// If so, then add them and move the very left
				for (int k = j + 1; k < grid; ++k) {
					if (arr[i][j] == arr[i][k]) {
						found = true;
						arr[i][j] += arr[i][k];
						arr[i][k] = 0;
						
						int tempJ = j;
						
						// Logic of moving the number to the very left
						while (tempJ > 0 && arr[i][tempJ - 1] == 0) {
							arr[i][tempJ - 1] = arr[i][tempJ];
							arr[i][tempJ] = 0;
							--tempJ;
						}
						
						break;
					} else if (arr[i][k] != 0) {
						break;
					}
				}
				
				// If no consecutive same number found,
				// then just move the number to the left
				if (!found) {
					int tempJ = j;
					
					while (tempJ > 0 && arr[i][tempJ - 1] == 0) {
						arr[i][tempJ - 1] = arr[i][tempJ];
						arr[i][tempJ] = 0;
						--tempJ;
					}
				}
			}
		}
	}
	
	private void moveRight() {
	
	}
	
	private void moveUp() {
		for (int j = 0; j < grid; ++j) {
			for (int i = 0; i < grid; ++i) {
				boolean found = false;
				
				for (int k = i + 1; k < grid; ++k) {
					if (arr[i][j] == arr[k][j]) {
						found = true;
						arr[i][j] += arr[k][j];
						arr[k][j] = 0;
						
						int tempI = i;
						
						while (tempI > 0 && arr[tempI - 1][j] == 0) {
							arr[tempI - 1][j] = arr[tempI][j];
							arr[tempI][j] = 0;
							--tempI;
						}
						
						break;
					} else if (arr[k][j] != 0) {
						break;
					}
				}
				
				if (!found) {
					int tempI = i;
					
					while (tempI > 0 && arr[tempI - 1][j] == 0) {
						arr[tempI - 1][j] = arr[tempI][j];
						arr[tempI][j] = 0;
						--tempI;
					}
				}
			}
		}
	}
	
	private void moveDown() {
	
	}
	
	private int takeUserAction() {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		System.out.println("Enter move: ");
		char move = scanner.next().charAt(0);
		
		switch (move) {
			case 'h':
				moveLeft();
				System.out.println();
				break;
			case 'j':
				moveUp();
				break;
			case 'k':
				moveDown();
				break;
			case 'l':
				moveRight();
				break;
			default:
				System.out.println("Wrong input");
		}
		
		int max = 0;
		for (int[] ints : arr) {
			for (int anInt : ints) {
				max = Integer.max(max, anInt);
			}
		}
		
		return max;
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
