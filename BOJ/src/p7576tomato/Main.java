package p7576tomato;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, unripe;
	private static int[][] map;
	private static Queue<int[]> queue = new LinkedList<>();
	private static int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p7576tomato/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		M = Integer.parseInt(tokenizer.nextToken());
		N = Integer.parseInt(tokenizer.nextToken());
		
		map = new int[N][];
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					unripe++;
				} else if (map[i][j] == 1) {
					queue.add(new int[] {i, j, 0});
				}
			}
		}
		
		int answer = bfs();
		
		bw.write(String.valueOf(answer));
		bw.close();
		br.close();
	}
	
	private static int bfs() {
		int tomatoes = 0;
		int day = 0;
		
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			day = nowNode[2];
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode[0] + dR[i];
				int nextColumn = nowNode[1] + dC[i];
				
				if (0 <= nextRow && nextRow <= N - 1
						&& 0 <= nextColumn && nextColumn <= M - 1
						&& map[nextRow][nextColumn] == 0) {
					map[nextRow][nextColumn] = 1;
					queue.add(new int [] {nextRow, nextColumn, nowNode[2] + 1});
					tomatoes++;
				}
			}
		}
		
		return tomatoes < unripe ? -1 : day;
	}
}
