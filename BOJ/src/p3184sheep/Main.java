package p3184sheep;

import java.io.*;
import java.util.*;

public class Main {
	private static int R, C, sheeps, wolves;
	private static int[][] map;
	private static boolean[][] visited;
	private static List<int[]> areas = new ArrayList<>();
	private static Queue<int[]> queue = new LinkedList<>();
	private static int[] dX = {-1, 1, 0, 0}, dY = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p3184sheep/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			
			for (int j = 0; j < C; j++) {
				switch (line[j]) {
				case '#':
					map[i][j] = 1;
					break;
				case 'v':
					map[i][j] = 2;
					areas.add(new int[] {i, j});
					break;
				case 'o':
					map[i][j] = 3;
					areas.add(new int[] {i, j});
					break;
				}
			}
		}
		
		for (int[] area : areas) {
			if (!visited[area[0]][area[1]]) {
				queue.add(area);
				visited[area[0]][area[1]] = true;
				bfs();
			}
		}
		
		bw.write(String.valueOf(sheeps));
		bw.write(" ");
		bw.write(String.valueOf(wolves));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs() {
		int sheepCount = 0, wolfCount = 0;
		
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (map[nowNode[0]][nowNode[1]] == 2) {
				wolfCount++;
			}
			
			if (map[nowNode[0]][nowNode[1]] == 3) {
				sheepCount++;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode[0] + dY[i];
				int nextColumn = nowNode[1] + dX[i];
				
				if (map[nextRow][nextColumn] != 1
						&& !visited[nextRow][nextColumn]) {
					queue.add(new int[] {nextRow, nextColumn});
					visited[nextRow][nextColumn] = true;
				}
			}
		}
		
		sheeps += sheepCount > wolfCount ? sheepCount : 0;
		wolves += sheepCount <= wolfCount ? wolfCount : 0;
	}
}
