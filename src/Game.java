import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Game {
	private int grid;
	private int[][] arr;
	private Canvas canvas;
	private Stack<int[][]> snapshots;
	
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
		snapshots = new Stack<>();
	}
	
	public void play() {
		int max = 0;
		
		while (max != 2048) {
			List<Position> emptyPositions = getEmptyPositions();
			int length = emptyPositions.size();
			
			// Get a random position where you can put the new number
			int random = (int) Math.round(Math.random() * (length - 1));
			Position randomPosition = emptyPositions.get(random);
			// New Number should be 2 or 4
			int num = (int) Math.round(Math.random() * 1) == 0
					          ? 2
					          : 4;
			arr[randomPosition.i][randomPosition.j] = num;
			canvas.draw();
			
			if (!anyMoveLeft()) {
				System.out.println("GAME OVER!");
				break;
			}
			
			max = takeUserAction();
			
			if (max == 2048) {
				System.out.println("You've won!");
			}
		}
	}
	
	private boolean anyMoveLeft() {
		for (int i = 0; i < grid; ++i) {
			for (int j = 0; j < grid; ++j) {
				if (arr[i][j] == 0) {
					return true;
				}
				if (j < grid - 1) {
					if (arr[i][j] == arr[i][j + 1]) {
						return true;
					}
				}
				if (i < grid - 1) {
					if (arr[i][j] == arr[i + 1][j]) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private boolean moveLeft() {
		boolean moved = false;
		
		for (int i = 0; i < grid; ++i) {
			for (int j = 0; j < grid; ++j) {
				boolean found = false;
				
				// Check if there are same numbers in the same row
				// If so, then add them and move the very left
				for (int k = j + 1; k < grid; ++k) {
					if (arr[i][j] != 0 && arr[i][j] == arr[i][k]) {
						found = true;
						moved = true;
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
					
					while (tempJ > 0 && arr[i][tempJ] != 0 && arr[i][tempJ - 1] == 0) {
						moved = true;
						arr[i][tempJ - 1] = arr[i][tempJ];
						arr[i][tempJ] = 0;
						--tempJ;
					}
				}
			}
		}
		
		return moved;
	}
	
	private boolean moveRight() {
		boolean moved = false;
		
		for (int i = 0; i < grid; ++i) {
			for (int j = grid - 1; j >= 0; --j) {
				boolean found = false;
				
				for (int k = j - 1; k >= 0; --k) {
					if (arr[i][j] != 0 && arr[i][j] != 0 && arr[i][j] == arr[i][k]) {
						found = true;
						moved = true;
						arr[i][j] += arr[i][k];
						arr[i][k] = 0;
						
						int tempJ = j;
						
						while (tempJ < grid - 1 && arr[i][tempJ + 1] == 0) {
							arr[i][tempJ + 1] = arr[i][tempJ];
							arr[i][tempJ] = 0;
							++tempJ;
						}
						
						break;
					} else if (arr[i][k] != 0) {
						break;
					}
				}
				
				if (!found) {
					int tempJ = j;
					
					while (tempJ < grid - 1 && arr[i][tempJ] != 0 && arr[i][tempJ + 1] == 0) {
						moved = true;
						arr[i][tempJ + 1] = arr[i][tempJ];
						arr[i][tempJ] = 0;
						++tempJ;
					}
				}
			}
		}
		
		return moved;
	}
	
	private boolean moveUp() {
		boolean moved = false;
		
		for (int j = 0; j < grid; ++j) {
			for (int i = 0; i < grid; ++i) {
				boolean found = false;
				
				for (int k = i + 1; k < grid; ++k) {
					if (arr[i][j] != 0 && arr[i][j] == arr[k][j]) {
						found = true;
						moved = true;
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
					
					while (tempI > 0 && arr[tempI][j] != 0 && arr[tempI - 1][j] == 0) {
						moved = true;
						arr[tempI - 1][j] = arr[tempI][j];
						arr[tempI][j] = 0;
						--tempI;
					}
				}
			}
		}
		
		return moved;
	}
	
	private boolean moveDown() {
		boolean moved = false;
		
		for (int j = 0; j < grid; ++j) {
			for (int i = grid - 1; i >= 0; --i) {
				boolean found = false;
				
				for (int k = i - 1; k >= 0; --k) {
					if (arr[i][j] != 0 && arr[i][j] == arr[k][j]) {
						found = true;
						moved = true;
						arr[i][j] += arr[k][j];
						arr[k][j] = 0;
						
						int tempI = i;
						
						while (tempI < grid - 1 && arr[tempI + 1][j] == 0) {
							arr[tempI + 1][j] = arr[tempI][j];
							arr[tempI][j] = 0;
							++tempI;
						}
						
						break;
					} else if (arr[k][j] != 0) {
						break;
					}
				}
				
				if (!found) {
					int tempI = i;
					
					while (tempI < grid - 1 && arr[tempI][j] != 0 && arr[tempI + 1][j] == 0) {
						moved = true;
						arr[tempI + 1][j] = arr[tempI][j];
						arr[tempI][j] = 0;
						++tempI;
					}
				}
			}
		}
		
		return moved;
	}
	
	private void takeSnapshot() {
		int[][] snapshot = new int[grid][grid];
		
		if (!snapshots.isEmpty()) {
			snapshots.pop();
		}
		
		for (int i = 0; i < grid; ++i) {
			System.arraycopy(arr[i], 0, snapshot[i], 0, grid);
		}
		
		snapshots.push(snapshot);
	}
	
	private boolean undo() {
		if (snapshots.isEmpty()) {
			return false;
		}
		
		int[][] temp = snapshots.pop();
		
		for (int i = 0; i < grid; ++i) {
			System.arraycopy(temp[i], 0, arr[i], 0, grid);
		}
		
		return true;
	}
	
	private int takeUserAction() {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		boolean moved = false;
		boolean undone = false;
		
		while (!moved) {
			System.out.println("Enter move: ");
			char move = scanner.next().charAt(0);
			
			switch (move) {
				case 'h':
					takeSnapshot();
					moved = moveLeft();
					System.out.println();
					break;
				case 'j':
					takeSnapshot();
					moved = moveUp();
					break;
				case 'k':
					takeSnapshot();
					moved = moveDown();
					break;
				case 'l':
					takeSnapshot();
					moved = moveRight();
					break;
				case 'u':
					undone = undo();
					break;
				default:
					System.out.println("WRONG INPUT");
			}
			
			if (moved) {
				undone = true;
			} else if (undone) {
				moved = true;
			}
			
			if (!moved) {
				System.out.println("(NO MOVE TAKEN)");
			}
			if (!undone) {
				System.out.println("(UNDO CAN NOT PERFORMED)");
			}
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
