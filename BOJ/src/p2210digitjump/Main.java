package p2210digitjump;

import java.io.*;
import java.util.*;

public class Main {
	private static final int N = 5, SIZE = 6;
	private static int[] numbers = new int[SIZE];
	private static int[][] map = new int[N][];
	private static Set<String> result = new HashSet<>();
	private static int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2210digitjump/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(0, i, j);
			}
		}
		
		bw.write(String.valueOf(result.size()));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int row, int column) {
		numbers[depth] = map[row][column];
		
		if (depth == SIZE - 1) {
			StringBuilder builder = new StringBuilder();
			Arrays.stream(numbers).forEach(number -> builder.append(number));
			result.add(builder.toString());
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nextRow = row + dR[i];
			int nextColumn = column + dC[i];
			
			if (0 <= nextRow && nextRow <= N - 1 
					&& 0 <= nextColumn && nextColumn <= N - 1) {
				dfs(depth + 1, nextRow, nextColumn);
			}
		}
	}
}
