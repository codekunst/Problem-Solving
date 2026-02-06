package basic.p15686chickendelivery;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, result;
	private static int[][] map;
	private static List<int[]> homes = new ArrayList<>(),
			stores = new ArrayList<>(), selectedStores = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p15686chickendelivery/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		result = 2 * N * 2 * N + 1;
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				
				if (map[i][j] == 1) {
					homes.add(new int[] {i, j});
					continue;
				}
				
				if (map[i][j] == 2) {
					stores.add(new int[] {i, j});
					continue;
				}
			}
		}
		
		dfs(0, 0);
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index) {
		if (depth == M) {
			int nowResult = 0;
			
			for (int[] home : homes) {
				int minDistance = 2 * N + 1;
				
				for (int[] store : selectedStores) {
					int distance = Math.abs(home[0] - store[0]) + Math.abs(home[1] - store[1]);
					
					minDistance = minDistance > distance ? distance : minDistance;
				}
				
				nowResult += minDistance;
			}
			
			result = result > nowResult ? nowResult : result;
			return;
		}
		
		for (int i = index; i < stores.size(); i++) {
			selectedStores.add(stores.get(i));
			dfs(depth + 1, i + 1);
			selectedStores.remove(depth);
		}
	}
}
