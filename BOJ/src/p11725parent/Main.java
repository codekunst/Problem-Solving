package p11725parent;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] parents;
	private static List<Integer>[] adList;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11725parent/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		adList = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adList[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			adList[from].add(to);
			adList[to].add(from);
		}
		
		dfs(1);
		
		for (int i = 2; i < N + 1; i++) {
			bw.write(String.valueOf(parents[i]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int nowNode) {
		visited[nowNode] = true;
		
		for (Integer nextNode : adList[nowNode]) {
			if (!visited[nextNode]) {
				parents[nextNode] = nowNode;
				dfs(nextNode);
			}
		}
		
		visited[nowNode] = false;
	}
}
