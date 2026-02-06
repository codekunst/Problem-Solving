package p2309combination;

import java.io.*;
import java.util.*;

public class Main {
	private static int total;
	private static int[] heights = new int[9];
	private static List<Integer> result = new ArrayList<>();
	private static boolean[] visited = new boolean[9];
	private static boolean flag;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2309combination/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 0; i < 9; i++) {
			int height = Integer.parseInt(br.readLine());
			heights[i] = height;
			total += height;
		}
		
		dfs(0, 0, 0);
		
		Collections.sort(result);
		
		for (int i = 0; i < 7; i++) {
			bw.write(String.valueOf(result.get(i)));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index, int sum) {
		if (flag) {
			return;
		}
		
		if(depth == 2) {
			if (total - sum == 100) {
				flag = true;
				for (int i = 0; i < 9; i++) {
					if (visited[i]) {
						continue;
					}
					result.add(heights[i]);
				}
			}
			return;
		}
		
		for (int i = index; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(depth + 1, i + 1, sum + heights[i]);
				visited[i] = false;
			}
		}
	}
}
