package graph2.p1753mindistance;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 10 * 300_000 + 1;
	private static int V, E, K;
	private static List<int[]>[] adList;
	private static int[] distance;
	private static boolean[] visited;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/graph2/p1753mindistance/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokenizer.nextToken());
		E = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(br.readLine());
		
		adList = new List[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			int value = Integer.parseInt(tokenizer.nextToken());
			
			adList[from].add(new int[] {to, value});
		}
		
		distance = new int[V + 1];
		Arrays.fill(distance, INF);
		distance[K] = 0;
		
		visited = new boolean[V + 1];
		
		queue.add(new int[] {K, 0});
		
		dijkstra();
		
		for (int i = 1; i < V + 1; i++) {
			bw.write(distance[i] == INF ? "INF" : String.valueOf(distance[i]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dijkstra() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (visited[nowNode[0]]) {
				continue;
			}
			
			visited[nowNode[0]] = true;
			
			for (int[] nextNode : adList[nowNode[0]]) {
				if (distance[nextNode[0]] > nowNode[1] + nextNode[1]) {
					distance[nextNode[0]] = nowNode[1] + nextNode[1];
					queue.add(new int[] {nextNode[0], distance[nextNode[0]]});
				}
			}
		}
	}
}
