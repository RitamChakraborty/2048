/**
 * Provide movements
 */

public class Movement {
	private int grid;
	private int[][] arr;
	
	public Movement(int grid, int[][] arr) {
		this.grid = grid;
		this.arr = arr;
	}
	
	/**
	 * Move left is performed in the board
	 *
	 * @return true if some move is done, false otherwise
	 */
	public boolean moveLeft() {
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
						
						// Move the numbers to the very left
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
	
	/**
	 * Move right is performed in the board
	 *
	 * @return true is some move is performed, false otherwise
	 */
	public boolean moveRight() {
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
	
	/**
	 * Move up is performed in the board
	 *
	 * @return true if some move is performed, false otherwise
	 */
	public boolean moveUp() {
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
	
	/**
	 * Move down is performed in the board
	 *
	 * @return true if some move is performed, false otherwise
	 */
	public boolean moveDown() {
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
	
	/**
	 * Checks if there is any move left to be performed
	 *
	 * @return true if there is some move possible, false otherwise
	 */
	public boolean anyMoveLeft() {
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
}
