package p1504minpath;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 200_000 * 1_000 + 1;
	private static int N, E, vertexA, vertexB, resultA, resultB, result;
	private static List<int[]>[] adList;
	private static boolean[] visited;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
	private static int[] distance;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1504minpath/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		E = Integer.parseInt(tokenizer.nextToken());
		
		adList = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(tokenizer.nextToken());
			int nodeB = Integer.parseInt(tokenizer.nextToken());
			int value = Integer.parseInt(tokenizer.nextToken());
			adList[nodeA].add(new int[] {nodeB, value});
			adList[nodeB].add(new int[] {nodeA, value});
		}
		
		tokenizer = new StringTokenizer(br.readLine());
		vertexA = Integer.parseInt(tokenizer.nextToken());
		vertexB = Integer.parseInt(tokenizer.nextToken());
		
		visited = new boolean[N + 1];
		distance = new int[N + 1];
		
		Arrays.fill(distance, INF);
		distance[1] = 0;
		queue.add(new int[] {1, 0});
		
		dijkstra();
		
		resultA += distance[vertexA];
		resultB += distance[vertexB];
		Arrays.fill(visited, false);
		Arrays.fill(distance, INF);
		distance[vertexA] = 0;
		queue.clear();
		queue.add(new int[] {vertexA, 0});
		
		dijkstra();
		
		resultA += distance[vertexB];
		Arrays.fill(visited, false);
		Arrays.fill(distance, INF);
		distance[vertexB] = 0;
		queue.clear();
		queue.add(new int[] {vertexB, 0});
		
		dijkstra();
		
		resultA += distance[N];
		Arrays.fill(visited, false);
		Arrays.fill(distance, INF);
		distance[vertexB] = 0;
		queue.clear();
		queue.add(new int[] {vertexB, 0});
		
		dijkstra();
		
		resultB += distance[vertexA];
		Arrays.fill(visited, false);
		Arrays.fill(distance, INF);
		distance[vertexA] = 0;
		queue.clear();
		queue.add(new int[] {vertexA, 0});
		
		dijkstra();
		
		resultB += distance[N];
		
		result = resultA > resultB ? resultB : resultA;
		bw.write(result >= INF ? "-1" : String.valueOf(result));
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
				if (distance[nextNode[0]] > distance[nowNode[0]] + nextNode[1]) {
					distance[nextNode[0]] = distance[nowNode[0]] + nextNode[1];
					queue.add(new int[] {nextNode[0], distance[nextNode[0]]});
				}
			}
		}
	}
}
