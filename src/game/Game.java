package game;

import canvas.Canvas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Contains the logic for the game
 */
public class Game {
	private int grid;
	private int[][] arr;
	private canvas.Canvas canvas;
	private Movement movement;
	private Deque<int[][]> snapshots;
	private int iteration = 0;
	
	
	/**
	 * @param grid size of the board
	 */
	public Game(int grid) {
		this.grid = grid;
		arr = new int[grid][grid];
		canvas = new Canvas(grid, arr);
		movement = new Movement(grid, arr);
		snapshots = new ArrayDeque<>();
	}
	
	/**
	 * Get all the position where there is no element except 0
	 *
	 * @return return the empty position as a list
	 */
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
	
	
	/**
	 * Place new numbers in empty positions
	 */
	private void placeNewNumbers() {
		List<Position> emptyPositions = getEmptyPositions();
		int length = emptyPositions.size();
		
		int random = (int) Math.round(Math.random() * (length - 1));        // Selects a random number in between the size of empty positions
		Position randomPosition = emptyPositions.get(random);               // Selects a random position where to put the next element
		
		int num = (int) Math.round(Math.random() * 1) == 0                  // Selects either 2 or 4 to put in the selected position
				          ? 2
				          : 4;
		arr[randomPosition.i][randomPosition.j] = num;
	}
	
	/**
	 * game.Game begins here
	 */
	public void play() {
		int max = 0;
		
		while (max != 2048) {                                                   // Play the game until the max sum reaches 2048
			placeNewNumbers();
			
			if (iteration == 0) {
				placeNewNumbers();
			}
			
			takeSnapshot();
			canvas.draw();                                                      // Draw the board after every iteration
			
			if (!movement.anyMoveLeft()) {                                               // Print game over if there is no move left
				System.out.println("GAME OVER!");
				break;
			}
			
			takeUserAction();                                             // Takes user move
			max = getMaxValue();
			++iteration;
			
			if (max == 2048) {                                                  // If max sum reaches 2048, print win
				System.out.println("You've won!");
				System.out.println("Number of moves: " + iteration);
			}
		}
	}
	
	/**
	 * Save the previous board positions in the stack
	 */
	private void takeSnapshot() {
		int[][] snapshot = new int[grid][grid];
		
		for (int i = 0; i < grid; ++i) {
			System.arraycopy(arr[i], 0, snapshot[i], 0, grid);
		}
		
		snapshots.add(snapshot);
	}
	
	/**
	 * Board positions are rollback to previous iteration
	 *
	 * @return true if undo can be performed, false otherwise
	 */
	private boolean undo() {
		if (iteration == 0) {
			return false;
		}
		
		if (snapshots.size() < 2) {
			return false;
		}
		
		int[][] temp = snapshots.peek();
		
		for (int i = 0; i < grid; ++i) {
			System.arraycopy(temp[i], 0, arr[i], 0, grid);
		}
		
		return true;
	}
	
	/**
	 * Get the maximum value of the sums
	 *
	 * @return Maximum sum
	 */
	private int getMaxValue() {
		int max = 0;
		for (int[] ints : arr) {
			for (int anInt : ints) {
				max = Integer.max(max, anInt);
			}
		}
		
		return max;
	}
	
	/**
	 * Takes user input
	 */
	private void takeUserAction() {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		boolean moved = false;
		boolean undone = false;
		
		while (!moved) {
			boolean undoIsPerformed = false;
			System.out.println("(h: left, l: right, j: up, k: down, u: undo)");
			System.out.println("Enter move: ");
			char move = scanner.next().toLowerCase().charAt(0);
			
			switch (move) {
				case 'h':
					moved = movement.moveLeft();
					break;
				case 'j':
					moved = movement.moveUp();
					break;
				case 'k':
					moved = movement.moveDown();
					break;
				case 'l':
					moved = movement.moveRight();
					break;
				case 'u':
					undone = undo();
					undoIsPerformed = true;
					break;
				default:
					System.out.println("WRONG INPUT");
			}
			
			if (undoIsPerformed) {
				if (!undone) {
					System.out.println("(UNDO CAN NOT BE PERFORMED!)");
				} else {
					if (snapshots.size() > 1) {
						snapshots.removeLast();
					}
					canvas.draw();
				}
			}
			if (!undoIsPerformed && !moved) {
				System.out.println("(NO MOVE TAKEN!)");
			} else {
				if (snapshots.size() >= 2) {
					snapshots.removeFirst();
				}
			}
		}
	}
	
	/**
	 * Holds the position of an element in the board
	 */
	private static class Position {
		int i;
		int j;
		
		/**
		 * @param i position in the x axis
		 * @param j position in the y axis
		 */
		public Position(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
