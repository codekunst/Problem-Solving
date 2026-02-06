package graph1.p2252makeline;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] inDegree;
	private static List<Integer>[] adList;
	private static Queue<Integer> queue = new LinkedList<>();
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/graph1/p2252makeline/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		inDegree = new int[N + 1];
		
		adList = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			
			adList[from].add(to);
			inDegree[to]++;
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		
		topologicalSort();
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void topologicalSort() {
		while (!queue.isEmpty()) {
			int nowNode = queue.poll();
			
			result.append(nowNode).append(" ");
			
			for (int nextNode : adList[nowNode]) {
				inDegree[nextNode]--;
				
				if (inDegree[nextNode] == 0) {
					queue.add(nextNode);
				}
			}
		}
	}
}
