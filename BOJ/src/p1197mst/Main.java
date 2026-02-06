package p1197mst;

import java.io.*;
import java.util.*;

// Kruskal's
public class Main {
	private static int V, E, edgeCount, result;
	private static PriorityQueue<int[]> edgeList = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
	private static int[] parents;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1197mst/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokenizer.nextToken());
		E = Integer.parseInt(tokenizer.nextToken());
		
		parents = new int[V + 1];
		for (int i = 0; i < V + 1; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			edgeList.offer(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
		}
		
		while (edgeCount < V - 1) {
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
