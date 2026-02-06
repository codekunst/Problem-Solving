package p14503robot;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, result;
	private static int[] robot;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dY = {-1, 0, 1, 0}, dX = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p14503robot/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		robot = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		map = new int[N][];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		visited = new boolean[N][M];
		
		dfs();
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs() {
		if (!visited[robot[0]][robot[1]]) {
			visited[robot[0]][robot[1]] = true;
			result++;
		}
		
		boolean flag = false;
		
		for (int i = 0; i < 4; i++) {
			int row = robot[0] + dY[i];
			int column = robot[1] + dX[i];
			
			if (map[row][column] == 0 && !visited[row][column]) {
				flag = true;
				break;
			}
		}
		
		if (flag) {
			robot[2] = (robot[2] + 3) % 4;
			
			int row = robot[0] + dY[robot[2]];
			int column = robot[1] + dX[robot[2]];
			
			if (map[row][column] == 0 && !visited[row][column]) {
				robot[0] = row;
				robot[1] = column;
			}
			
			dfs();
		} else {
			int row = robot[0] - dY[robot[2]];
			int column = robot[1] - dX[robot[2]];
			
			if (map[row][column] == 0) {
				robot[0] = row;
				robot[1] = column;
				dfs();
			} else {
				return;
			}
		}
	}
}
