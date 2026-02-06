package graph1.p1922network;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, edgeCount, result;
	private static PriorityQueue<int[]> edgeList = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
	private static int[] parent;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/graph1/p1922network/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(tokenizer.nextToken());
			int nodeB = Integer.parseInt(tokenizer.nextToken());
			int value = Integer.parseInt(tokenizer.nextToken());
			
			edgeList.add(new int[] {nodeA, nodeB, value});
		}
		
		kruskal();
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void kruskal() {
		while (edgeCount < N - 1) {
			int[] edge = edgeList.poll();
			
			if (find(edge[0]) == find(edge[1])) {
				continue;
			}
			
			union(edge[0], edge[1]);
			result += edge[2];
			edgeCount++;
		}
	}
	
	private static void union(int nodeA, int nodeB) {
		nodeA = find(nodeA);
		nodeB = find(nodeB);
		
		if (nodeA != nodeB) {
			parent[nodeB] = nodeA;
		}
	}
	
	private static int find(int node) {
		if (parent[node] == node) {
			return parent[node];
		}
		
		return parent[node] = find(parent[node]);
	}
}
