package p10971tsp;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer = 1_000_000 * 10 + 1;
	private static int[][] adMatrix;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p10971tsp/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		
		adMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			adMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		for (int i = 0; i < N; i++) {
			dfs(0, i, i, 0);
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int startNode, int nowNode, int dist) {
		visited[nowNode] = true;
		
		if (depth == N - 1) {
			if (adMatrix[nowNode][startNode] != 0) {
				answer = Math.min(answer, dist + adMatrix[nowNode][startNode]);				
			}
			visited[nowNode] = false;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adMatrix[nowNode][i] != 0) {
				dfs(depth + 1, startNode, i, dist + adMatrix[nowNode][i]);
			}
		}
		
		visited[nowNode] = false;
	}
}
