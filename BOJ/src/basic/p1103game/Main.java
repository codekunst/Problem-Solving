package basic.p1103game;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, result;
	private static boolean inf;
	private static int[][] map, dp;
	private static boolean[][] visited;
	private static int[] dY = {-1, 1, 0, 0}, dX = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p1103game/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) == 'H' ? -1 : line.charAt(j) - '0';
			}
		}
		
		visited[0][0] = true;
		dfs(0, 0);
		
		bw.write(inf ? "-1" : String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int row, int column) {
		if (inf) {
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nextRow = row + map[row][column] * dY[i];
			int nextColumn = column + map[row][column] * dX[i];
			
			if (0 <= nextRow && nextRow <= N - 1
					&& 0 <= nextColumn && nextColumn <= M - 1
					&& !visited[nextRow][nextColumn]
					&& map[nextRow][nextColumn] != -1
					&& dp[nextRow][nextColumn] < dp[row][column] + 1) {
				visited[nextRow][nextColumn] = true;
				dp[nextRow][nextColumn] = dp[row][column] + 1;
				dfs(nextRow, nextColumn);
				visited[nextRow][nextColumn] = false;
				continue;
			}
			
			if (0 > nextRow || N - 1 < nextRow
					|| 0 > nextColumn || M - 1 < nextColumn
					|| map[nextRow][nextColumn] == -1) {
				result = dp[row][column] + 1 > result ? dp[row][column] + 1 : result;
				continue;
			}
			
			if (visited[nextRow][nextColumn]) {
				inf = true;
				return;
			}
		}
	}
}
