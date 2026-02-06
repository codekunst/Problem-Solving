package p1697hideandseek;

import java.io.*;
import java.util.*;

public class Main {
	private static final int SIZE = 100_001;
	private static int N, K;
	private static Queue<int[]> queue = new LinkedList<>();
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1697hideandseek/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		
		visited = new boolean[SIZE];
		
		queue.add(new int[] {N, 0});
		
		int answer = bfs();
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int bfs() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (nowNode[0] == K) {
				return nowNode[1];
			}
			
			int distance = nowNode[1] + 1;
			
			int next = nowNode[0] + 1;
			if (0 <= next && next < SIZE
					&& !visited[next]) {
				visited[next] = true;
				queue.add(new int[] {next, distance});
			}
			
			next = nowNode[0] - 1;
			if (0 <= next && next < SIZE
					&& !visited[next]) {
				visited[next] = true;
				queue.add(new int[] {next, distance});
			}
			
			next = 2 * nowNode[0];
			if (0 <= next && next < SIZE
					&& !visited[next]) {
				visited[next] = true;
				queue.add(new int[] {next, distance});
			}
		}
		
		return -1;
	}
}
