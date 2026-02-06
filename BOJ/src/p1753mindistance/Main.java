package p1753mindistance;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 10 * 300_000 + 1;
	private static int V, E, K;
	private static List<List<int[]>> adList = new ArrayList<>();
	private static int[] distance;
	private static boolean[] visited;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1753mindistance/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokenizer.nextToken());
		E = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < V + 1; i++) {
			adList.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adList.get(edge[0]).add(new int[] {edge[1], edge[2]});
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
			
			for (int[] nextNode : adList.get(nowNode[0])) {
				if (!visited[nextNode[0]]
						&& distance[nextNode[0]] > nowNode[1] + nextNode[1]) {
					distance[nextNode[0]] = nowNode[1] + nextNode[1];
					queue.add(new int[] {nextNode[0], distance[nextNode[0]]});
				}
			}
		}
	}
}
