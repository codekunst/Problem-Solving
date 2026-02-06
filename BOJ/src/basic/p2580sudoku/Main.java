package basic.p2580sudoku;

import java.io.*;
import java.util.*;

public class Main {
	private static boolean flag;
	private static int[][] map = new int[9][9];
	private static List<int[]> empties = new ArrayList<>();
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p2580sudoku/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 9; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				
				if (map[i][j] == 0) {
					empties.add(new int[] {i, j});
				}
			}
		}
		
		dfs(0);
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth) {
		if (flag) {
			return;
		}
		
		if (depth == empties.size()) {
			for (int[] line : map) {
				for (int element : line) {
					result.append(element).append(" ");
				}
				result.append("\n");
			}
			
			flag = true;
			return;
		}
		
		int[] nowNode = empties.get(depth);
		
		Loop: for (int num = 1; num < 10; num++) {
			for (int i = 0; i < 9; i++) {
				if (num == map[i][nowNode[1]]
						|| num == map[nowNode[0]][i]) {
					continue Loop;
				}
			}
			
			int row = nowNode[0] - nowNode[0] % 3;
			int column = nowNode[1] - nowNode[1] % 3;

			for (int i = row; i < row + 3; i++) {
				for (int j = column; j < column + 3; j++) {
					if (num == map[i][j]) {
						continue Loop;
					}
				}
			}
			
			map[nowNode[0]][nowNode[1]] = num;
			dfs(depth + 1);
			map[nowNode[0]][nowNode[1]] = 0;
		}
	}
}
