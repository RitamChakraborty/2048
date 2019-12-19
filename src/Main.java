import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int grid;
		
		System.out.println("Enter grid number: ");
		grid = scanner.nextInt();
		
		Game game = new Game(grid);
		game.play();
	}
}
