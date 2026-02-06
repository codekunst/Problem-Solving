package p2206breakwall;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] map;
	private static Queue<int[]> queue = new LinkedList<>();
	private static boolean[][][] visited;
	private static int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2206breakwall/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		queue.add(new int[] {0, 0, 1, 0});
		
		visited = new boolean[N][M][2];
		visited[0][0][0] = true;
		
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int answer = bfs();
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int bfs() {
		
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (nowNode[0] == N - 1 && nowNode[1] == M - 1) {
				return nowNode[2];
			}
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode[0] + dR[i];
				int nextColumn = nowNode[1] + dC[i];
				
				if (0 <= nextRow && nextRow <= N - 1
						&& 0 <= nextColumn && nextColumn <= M - 1) {
					if (nowNode[3] == 0 && map[nextRow][nextColumn] == '1'
							&& !visited[nextRow][nextColumn][1]) {
						queue.add(new int[] {nextRow, nextColumn, nowNode[2] + 1, 1});
						visited[nextRow][nextColumn][1] = true;
					} else if (map[nextRow][nextColumn] == '0'
							&& !visited[nextRow][nextColumn][nowNode[3]]) {
						queue.add(new int[] {nextRow, nextColumn, nowNode[2] + 1, nowNode[3]});
						visited[nextRow][nextColumn][nowNode[3]] = true;
					}
				}
			}
		}
		
		return -1;
	}
}
