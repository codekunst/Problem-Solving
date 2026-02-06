package p1247optimized;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static int[] company, home;
	private static int[][] delivery;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1247optimized/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder result = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc < T + 1; tc++) {
			answer = 100 * 100 + 1;
			
			N = Integer.parseInt(br.readLine());
			delivery = new int[N][];
			visited = new boolean[N];
			
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			
			company = new int[] {
					Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())
			};
			home = new int[] {
					Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())
			};
			
			for (int i = 0; i < N; i++) {
				delivery[i] = new int[] {
						Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())
				};
			}
			
			dfs(0, 0, company);
			
			result.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int distance, int[] nowNode) {
		if (depth == N) {
			answer = Math.min(answer, distance + (int) Math.abs(nowNode[0] - home[0]) + (int) Math.abs(nowNode[1] - home[1]));
			return;
		}
		
		for (int i = 0; i < N ; i++) {
			if (!visited[i]) {
				int[] nextNode = delivery[i];
				
				visited[i] = true;
				dfs(depth + 1, distance + (int) Math.abs(nowNode[0] - nextNode[0]) + (int) Math.abs(nowNode[1] - nextNode[1]), nextNode);
				visited[i] = false;
			}
		}
	}
}
