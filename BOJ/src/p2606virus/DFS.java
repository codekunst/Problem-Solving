package p2606virus;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class DFS {
	private static int answer;
	private static List<Integer>[] adList;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2606virus/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		adList = new List[V + 1];
		IntStream.range(0, V + 1).forEach(i -> adList[i] = new ArrayList<>());
		
		visited = new boolean[V + 1];
		
		for (int i = 0; i < E; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			
			adList[from].add(to);
			adList[to].add(from);
		}
		
		dfs(1);
		
		bw.write(String.valueOf(answer - 1));
		bw.close();
		br.close();
	}
	
	private static void dfs(int nowNode) {
		visited[nowNode] = true;
		answer++;
		
		for (Integer nextNode : adList[nowNode]) {
			if (!visited[nextNode]) {
				dfs(nextNode);
			}
		}
	}
}
