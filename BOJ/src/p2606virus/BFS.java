package p2606virus;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BFS {
	private static int answer;
	private static List<Integer>[] adList;
	private static Queue<Integer> queue;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2606virus/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		adList = new List[V + 1];
		IntStream.range(0, V + 1).forEach(i -> adList[i] = new ArrayList<>());
		
		for (int i = 0; i < E; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			
			adList[from].add(to);
			adList[to].add(from);
		}
		
		queue = new LinkedList<>();
		queue.add(1);
		visited = new boolean[V + 1];
		visited[1] = true;
		
		bfs();
		
		bw.write(String.valueOf(answer - 1));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs() {
		while (!queue.isEmpty()) {
			int nowNode = queue.poll();
			answer++;
			
			for (int nextNode : adList[nowNode]) {
				if (!visited[nextNode]) {
					visited[nextNode] = true;
					queue.add(nextNode);
				}
			}
		}
	}
}
