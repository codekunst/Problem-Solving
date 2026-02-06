package p16236babyshark;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, eaten, sharkSize = 2, result;
	private static int[] shark;
	private static int[][] map;
	private static Queue<int[]> queue = new LinkedList<>();
	private static boolean[][] visited;
	private static PriorityQueue<int[]> eatable = new PriorityQueue<>((o1, o2) -> o1[2] == o2[2] ? o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]) : Integer.compare(o1[2], o2[2]));
	private static int[] dY = {-1, 1, 0, 0}, dX = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p16236babyshark/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][];
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 9) {
					shark = new int[] {i, j, 0};
				}
			}
		}
		
		while (true) {
			queue.clear();
			visited = new boolean[N][N];
			eatable.clear();
			
			queue.add(shark);
			visited[shark[0]][shark[1]] = true;
			
			bfs();
			
			if (eatable.isEmpty()) {
				break;
			}

			int[] target = eatable.poll();
			map[shark[0]][shark[1]] = 0;
			map[target[0]][target[1]] = 9;
			shark = new int[] {target[0], target[1], 0};
			result += target[2];
			
			if (sharkSize == ++eaten) {
				sharkSize++;
				eaten = 0;
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int[] nextNode = new int[] {nowNode[0] + dY[i], nowNode[1] + dX[i], nowNode[2] + 1};
				
				if (0 <= nextNode[0] && nextNode[0] <= N - 1
						&& 0 <= nextNode[1] && nextNode[1] <= N - 1
						&& !visited[nextNode[0]][nextNode[1]]
						&& map[nextNode[0]][nextNode[1]] <= sharkSize) {
					if (0 < map[nextNode[0]][nextNode[1]] && map[nextNode[0]][nextNode[1]] < sharkSize) {
						eatable.add(nextNode);
					}
					
					visited[nextNode[0]][nextNode[1]] = true;
					queue.add(nextNode);
				}
			}
		}
	}
}
