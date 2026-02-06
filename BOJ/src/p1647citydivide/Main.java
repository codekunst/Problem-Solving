package p1647citydivide;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, edgeCount, result;
	private static int[] parents;
	private static PriorityQueue<int[]> edgeList = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1647citydivide/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		parents = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			edgeList.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
		}
		
		while (edgeCount < N - 2) {
			int[] edge = edgeList.poll();
			
			if (find(edge[0]) == find(edge[1])) {
				continue;
			}
			
			union(edge[0], edge[1]);
			edgeCount++;
			result += edge[2];
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void union(int nodeA, int nodeB) {
		nodeA = find(nodeA);
		nodeB = find(nodeB);
		
		if (nodeA != nodeB) {
			parents[nodeB] = nodeA;
		}
	}
	
	private static int find(int node) {
		if (parents[node] == node) {
			return parents[node];
		}
		
		return parents[node] = find(parents[node]);
	}
}
