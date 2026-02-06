package p15654NM;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] array;
	private static boolean[] visited;
	private static StringBuilder builder = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p15654NM/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		visited = new boolean[N];
		
		Arrays.sort(array);
		
		dfs(0, new int[M]);
		
		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int[] index) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				builder.append(array[index[i]]).append(" ");
			}
			builder.append("\n");
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				index[depth] = i;
				visited[i] = true;
				dfs(depth + 1, index);
				visited[i] = false;
			}
		}
	}
}
