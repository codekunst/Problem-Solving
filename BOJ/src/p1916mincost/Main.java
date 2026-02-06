package p1916mincost;

import java.io.*;
import java.util.*;

public class Main {
	private static final long INF = 100_000L * 100_000L + 1L;
	private static int N, M, start, destination;
	private static List<List<Node>> adList = new ArrayList<>();
	private static PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1.getValue(), o2.getValue()));
	private static long[] distance;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1916mincost/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N + 1; i++) {
			adList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			long value = Long.parseLong(tokenizer.nextToken());
			adList.get(from).add(new Node(to, value));
		}
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		start = Integer.parseInt(tokenizer.nextToken());
		destination = Integer.parseInt(tokenizer.nextToken());
		
		distance = new long[N + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0L;
		visited = new boolean[N + 1];
		
		queue.add(new Node(start, 0L));
		
		dijkstra();
		
		bw.write(String.valueOf(distance[destination]));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dijkstra() {
		while (!queue.isEmpty()) {
			Node nowNode = queue.poll();
			
			if (visited[nowNode.getIndex()]) {
				continue;
			}
			
			visited[nowNode.getIndex()] = true;
			
			for (Node nextNode : adList.get(nowNode.getIndex())) {
				if (!visited[nextNode.getIndex()]
						&& distance[nextNode.getIndex()] > nowNode.getValue() + nextNode.getValue()) {
					distance[nextNode.getIndex()] = nowNode.getValue() + nextNode.getValue();
					queue.add(new Node(nextNode.getIndex(), distance[nextNode.getIndex()]));
				}
			}
		}
	}
}

class Node {
	private int index;
	private long value;
	
	public Node(int index, long value) {
		this.index = index;
		this.value = value;
	}
	
	public int getIndex() {
		return index;
	}
	
	public long getValue() {
		return value;
	}
}
