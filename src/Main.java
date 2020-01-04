import game.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int grid;
		
		boolean correctInput = false;
		
		while (!correctInput) {
			try {
				System.out.println("Enter grid number(greater than 2): ");
				grid = scanner.nextInt();
				
				if (grid <= 2) {
					throw new Exception("(GRID NUMBER HAS TO BE GREATER THAN 2)");
				} else {
					correctInput = true;
					Game game = new Game(grid);
					game.play();
				}
			} catch (Exception e) {
				System.out.println("(PLEASE ENTER A VALID INPUT!");
			}
		}
	}
}
