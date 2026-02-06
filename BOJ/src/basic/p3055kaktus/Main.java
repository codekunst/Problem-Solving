package basic.p3055kaktus;

import java.io.*;
import java.util.*;

public class Main {
	private static int R, C;
	private static Queue<Node> queue = new LinkedList<>();
	private static Node start, dest;
	private static char[][] map;
	private static int[][] dp;
	private static boolean[][] visited;
	private static int[] dY = {-1, 1, 0, 0}, dX = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p3055kaktus/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		
		map = new char[R][];
		dp = new int[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < C; j++) {
				switch(map[i][j]) {
				case 'S':
					start = new Node(i, j, false);
					break;
				case 'D':
					dest = new Node(i, j, false);
					break;
				case '*':
					queue.add(new Node(i, j, true));
					break;
				}
			}
		}
		
		queue.add(start);
		visited[start.getRow()][start.getColumn()] = true;
		
		bfs();
		
		int result = dp[dest.getRow()][dest.getColumn()];
		bw.write(result == 0 ? "KAKTUS" : String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs() {
		while (!queue.isEmpty()) {
			Node nowNode = queue.poll();
			
			if (nowNode.getRow() == dest.getRow() && nowNode.getColumn() == dest.getColumn()) {
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode.getRow() + dY[i];
				int nextColumn = nowNode.getColumn() + dX[i];
				
				if (0 <= nextRow && nextRow < R
						&& 0 <= nextColumn && nextColumn < C
						&& nowNode.getIsWater()
						&& map[nextRow][nextColumn] != 'D'
						&& map[nextRow][nextColumn] != 'X'
						&& map[nextRow][nextColumn] != '*') {
					queue.add(new Node(nextRow, nextColumn, true));
					map[nextRow][nextColumn] = '*';
					continue;
				}
				
				if (0 <= nextRow && nextRow < R
						&& 0 <= nextColumn && nextColumn < C
						&& !nowNode.getIsWater()
						&& !visited[nextRow][nextColumn]
						&& map[nextRow][nextColumn] != 'X'
						&& map[nextRow][nextColumn] != '*') {
					queue.add(new Node(nextRow, nextColumn, false));
					dp[nextRow][nextColumn] = dp[nowNode.getRow()][nowNode.getColumn()] + 1;
					visited[nextRow][nextColumn] = true;
				}
			}
		}
	}
}

class Node {
	private int row, column;
	private boolean isWater;
	
	public Node(int row, int column, boolean isWater) {
		this.row = row;
		this.column = column;
		this.isWater = isWater;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean getIsWater() {
		return isWater;
	}
}
