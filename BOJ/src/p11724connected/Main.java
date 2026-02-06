package p11724connected;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	private static int[] parents;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11724connected/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		
		parents = new int[N + 1];
		
		IntStream.range(1, N + 1).forEach(i -> parents[i] = i);
		
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(tokenizer.nextToken());
			int nodeB = Integer.parseInt(tokenizer.nextToken());
			
			union(nodeA, nodeB);
		}
		
		Set<Integer> set = new HashSet<>();
		
		for (int i = 1; i < N + 1; i++) {
			set.add(find(i));
		}
		
		bw.write(String.valueOf(set.size()));
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
