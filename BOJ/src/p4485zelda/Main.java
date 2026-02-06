package p4485zelda;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 125 * 125 * 10 + 1;
	private static int N;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
	private static int[][] map, distance;
	private static boolean[][] visited;
	private static int[] dY = {1, -1, 0, 0}, dX = {0, 0, -1, 1};
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p4485zelda/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int index = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			map = new int[N][];
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(distance[i], INF);
			}
			distance[0][0] = map[0][0];
			
			visited = new boolean[N][N];
			
			queue.clear();
			queue.add(new int[] {0, 0, map[0][0]});
			
			dijkstra();
			
			result.append("Problem ").append(index++).append(": ").append(distance[N - 1][N - 1]).append("\n");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dijkstra() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (visited[nowNode[0]][nowNode[1]]) {
				continue;
			}
			
			visited[nowNode[0]][nowNode[1]] = true;
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode[0] + dY[i];
				int nextColumn = nowNode[1] + dX[i];
				
				if (0 <= nextRow && nextRow < N
						&& 0 <= nextColumn && nextColumn < N
						&& !visited[nextRow][nextColumn]
						&& distance[nextRow][nextColumn] > nowNode[2] + map[nextRow][nextColumn]) {
					distance[nextRow][nextColumn] = nowNode[2] + map[nextRow][nextColumn];
					queue.add(new int[] {nextRow, nextColumn, distance[nextRow][nextColumn]});
				}
			}
		}
	}
}
