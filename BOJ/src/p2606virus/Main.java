package p2606virus;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	private static int[] parents;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2606virus/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parents = new int[N + 1];
		IntStream.range(1, N + 1).forEach(i -> parents[i] = i);
		
		for (int i = 0; i < M; i++) {
			int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			union(nodes[0], nodes[1]);
		}
		
		long count = IntStream.range(2, N + 1).filter(i -> find(i) == find(1)).count();
		bw.write(String.valueOf(count));
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
