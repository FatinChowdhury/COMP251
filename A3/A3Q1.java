import java.util.*;

public class A3Q1 {
	
	public static int min_moves(int[][] board) {
		// if not possible to go from corner to corner:
		// return -1
		// else return the minimum number of moves
		// required to go from the top left corner to the bottom right corner
		// of the board
		int num_rows = board.length;					// number of rows in the board
		int num_columns = board[0].length;				// columns
		int[][] dp = new int[num_rows][num_columns];	// store min_moves to reach cell in board (dp array)
		
		
		// Looping through the dp array and initializing all values to infinity
		for (int i=0; i<num_rows; i++) {
			for (int j=0; j<num_columns; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}

		dp[0][0] = 0;								// starting point is the top left corner, so it takes zero
		Queue<int[]> q = new LinkedList<>();		// queue to store the cells to be visited
		q.add(new int[]{0, 0});						// add the top left corner to the queue
		int[] dx = {0, 0, 1, -1};					// directions to move in the x-axis
		int[] dy = {1, -1, 0, 0};					// directions to move in the y-axis
		while (!q.isEmpty()) {
			int[] curr = q.poll();					// get the current cell
			int x = curr[0];
			int y = curr[1];
			for (int i=0; i<4; i++) {
				int new_x = x + dx[i];
				int new_y = y + dy[i];
				if (new_x >= 0 && new_x < num_rows && new_y >= 0 && new_y < num_columns) {
					if (board[new_x][new_y] == 0 && dp[new_x][new_y] > dp[x][y] + 1) {
						dp[new_x][new_y] = dp[x][y] + 1;
						q.add(new int[]{new_x, new_y});
					}
				}
			}
		}
		return dp[num_rows-1][num_columns-1] == Integer.MAX_VALUE ? -1 : dp[num_rows-1][num_columns-1];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num_rows = sc.nextInt();
		int num_columns = sc.nextInt();
		int [][]board = new int[num_rows][num_columns];
		for (int i=0; i<num_rows; i++) {
			char[] line = sc.next().toCharArray();
			for (int j=0; j<num_columns; j++) {
				board[i][j] = (int) (line[j] - '0');
			}
		}
		int answer = min_moves(board);
		System.out.println(answer);
	}
}

