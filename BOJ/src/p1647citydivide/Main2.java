package p1647citydivide;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int N, M, edgeCount, maxEdge, result;
	private static List<List<int[]>> adList;
	private static boolean[] visited;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1647citydivide/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		adList = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			adList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adList.get(edge[0]).add(new int[] { edge[1], edge[2] });
			adList.get(edge[1]).add(new int[] { edge[0], edge[2] });
		}
		
		visited = new boolean[N + 1];
		queue.add(new int[] {1, 0});
		
		prim();
		
		bw.write(String.valueOf(result - maxEdge));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void prim() {
		while (edgeCount < N) {
			int[] nowNode = queue.poll();
			
			if (visited[nowNode[0]]) {
				continue;
			}
			
			visited[nowNode[0]] = true;
			edgeCount++;
			maxEdge = nowNode[1] > maxEdge ? nowNode[1] : maxEdge;
			result += nowNode[1];
			
			for (int[] nextNode : adList.get(nowNode[0])) {
				if (!visited[nextNode[0]]) {
					queue.offer(nextNode);					
				}
			}
		}
	}
}
