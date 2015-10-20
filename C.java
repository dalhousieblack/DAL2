import java.util.*;

public class C {

	public static void  main (String[] args) {
		
		//Board
	
		//Input
		Scanner input = new Scanner(System.in);

		//Get number of test cases
		int n = input.nextInt();
		
		input.nextLine();

		//Loop
		for (int i = 0; i < n; i++) {

			String dim = input.nextLine();
			int height = Integer.parseInt(dim.split("x")[0]);
			int width = Integer.parseInt(dim.split("x")[1]);

			Character[][] board = new Character[height][width];
			
			for (int h = 0; h < height; h++) {
				String line = input.nextLine();
				for (int w = 0; w < width; w++) {
				    board[h][w] = line.charAt(w);
				}
			}

			for (int h = 0; h < height; h++) {
                                for (int w = 0; w < width; w++) {
					System.out.print(board[h][w]);
                                }
				System.out.println();
                        }

		}

	}

	private static int move(Character[][] board, int sx, int sy, int ex, int ey) {
		
		int distanceN = Integer.MAX_INT;
		int distanceS = Integer.MAX_INT;
		int distanceE = Integer.MAX_INT;
		int distanceW = Integer.MAX_INT;

		if (sx == ex && sy == ey)
			return 0;

		if (safe(board, sx, sy-1)) {
			distanceN = distance(sx, ex, sy-1, ey);
		}

		if (safe(board, sx, sy+1)) {
                        distanceS = distance(sx, ex, sy+1, ey);
                }

		if (safe(board, sx+1, sy)) {
                        distanceE = distance(sx+1, ex, sy, ey);
                }

                if (safe(board, sx-1, sy)) {
                        distanceW = distance(sx-1, ex, sy, ey);
                }

		int min = Math.min(Math.min(Math.min(distanceN, distanceS),distanceE),distanceW);

	}

	int distance(int sx, int sy, int ex, int ey) {
		return (sx-ex)*(sx-ex) + (sy-ey)*(sy-ey);
	}

	boolean safe(Character[][] board, int x, int y) {
		return (x >= 0) && (x < board[0].length) && (y >= 0) && (y < board.length) && (board[x][y].equals('E'));
	}

}

