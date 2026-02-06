package p1197mst;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int V, E, edgeCount, result;
	private static List<int[]>[] adList;
	private static boolean[] visited;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1197mst/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokenizer.nextToken());
		E = Integer.parseInt(tokenizer.nextToken());
		adList = new List[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adList[edge[0]].add(new int[] { edge[1], edge[2] });
			adList[edge[1]].add(new int[] { edge[0], edge[2] });
		}
		
		queue.add(new int[] {1, 0});
		visited = new boolean[V + 1];
		
		prim();
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void prim() {
		while (edgeCount < V) {
			int[] nowNode = queue.poll();
			
			if (visited[nowNode[0]]) {
				continue;
			}
			
			visited[nowNode[0]] = true;
			edgeCount++;
			result += nowNode[1];
			
			for (int[] nextNode : adList[nowNode[0]]) {
				if (!visited[nextNode[0]]) {
					queue.add(nextNode);					
				}
			}
		}
	}
}
