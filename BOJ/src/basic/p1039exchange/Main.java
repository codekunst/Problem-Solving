package basic.p1039exchange;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K, result;
	private static Queue<int[]> queue = new LinkedList<>();
	private static int[] visited = new int [1_000_001];
	private static int[] digits = {1, 10, 100, 1_000, 10_000, 100_000, 1_000_000};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p1039exchange/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		M = String.valueOf(N).length();
		
		queue.add(new int[] {N, 0});
		
		bfs();
		
		bw.write(result == 0 ? "-1" : String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (nowNode[1] == K) {
				result = nowNode[0] > result ? nowNode[0] : result;
				continue;
			}
			
			for (int i = 0; i < M - 1; i++) {
				for (int j = i + 1; j < M; j++) {
					if (j == M - 1 && nowNode[0] / digits[i] % 10 == 0) {
						continue;
					}
					
					int nextNode = nowNode[0];
					int iNumber = nextNode / digits[i] % 10;
					int jNumber = nextNode / digits[j] % 10;
					nextNode = nextNode
							- iNumber * digits[i]
							- jNumber * digits[j]
							+ iNumber * digits[j]
							+ jNumber * digits[i];
					
					if (visited[nextNode] < nowNode[1] + 1) {
						visited[nextNode] = nowNode[1] + 1;
						queue.add(new int[] {nextNode, nowNode[1] + 1});						
					}
				}
			}
		}
	}
}
