package graph1.p1922network;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int N, M, result;
	private static List<int[]>[] adList;
	private static boolean[] visited;
	private static PriorityQueue<int[]> queue;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/graph1/p1922network/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adList = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(tokenizer.nextToken());
			int nodeB = Integer.parseInt(tokenizer.nextToken());
			int value = Integer.parseInt(tokenizer.nextToken());
			
			adList[nodeA].add(new int[] {nodeB, value});
			adList[nodeB].add(new int[] {nodeA, value});
		}
		
		visited = new boolean[N + 1];
		
		queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		queue.add(new int[] {1, 0});
		
		prim();
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void prim() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (visited[nowNode[0]]) {
				continue;
			}
			
			visited[nowNode[0]] = true;
			result += nowNode[1];
			
			for (int[] nextNode : adList[nowNode[0]]) {
				queue.add(nextNode);
			}
		}
	}
}
