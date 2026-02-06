package p1012cabbage;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] cabbages;
	private static Queue<int[]> queue = new LinkedList<>();
	private static int[] dX = {-1, 1, 0, 0}, dY = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1012cabbage/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int count = 0;
			
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			M = Integer.parseInt(tokenizer.nextToken());
			N = Integer.parseInt(tokenizer.nextToken());
			K = Integer.parseInt(tokenizer.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			cabbages = new int[K][];
			
			for (int i = 0; i < K; i++) {
				tokenizer = new StringTokenizer(br.readLine());
				int column = Integer.parseInt(tokenizer.nextToken());
				int row = Integer.parseInt(tokenizer.nextToken());
				
				map[row][column] = 1;
				cabbages[i] = new int[] {row, column};
			}
			
			for(int[] cabbage : cabbages) {
				if (!visited[cabbage[0]][cabbage[1]]) {
					queue.add(new int [] {cabbage[0], cabbage[1]});
					visited[cabbage[0]][cabbage[1]] = true;
					count++;
					bfs();
				}
			}
			
			bw.write(String.valueOf(count));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode[0] + dY[i];
				int nextColumn = nowNode[1] + dX[i];
				
				if (0 <= nextRow && nextRow <= N - 1
						&& 0 <= nextColumn && nextColumn <= M - 1
						&& map[nextRow][nextColumn] == 1
						&& !visited[nextRow][nextColumn]) {
					visited[nextRow][nextColumn] = true;
					queue.add(new int [] {nextRow, nextColumn});
				}
			}
		}
	}
}
