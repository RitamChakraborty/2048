import java.util.StringJoiner;

public class Canvas {
	private int grid;
	
	public Canvas(int grid) {
		this.grid = grid;
	}
	
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
				CharSequence charSequence = "      ";
				stringJoiner1.add(charSequence);
			}
			
			stringJoiner.add(stringJoiner1.toString());
			
			if (i == grid - 1) {
				stringJoiner.add(cover);
			}
		}
		
		System.out.println(stringJoiner.toString());
	}
}
