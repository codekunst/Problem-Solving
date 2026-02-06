package p14502research;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, total, answer = -1;
	private static int[][] map;
	private static List<int[]> virus, emptys;
	private static Queue<int[]> queue = new LinkedList<>();
	private static boolean[][] visited;
	private static int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p14502research/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		map = new int[N][];
		virus = new ArrayList<>();
		emptys = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					emptys.add(new int[] {i, j});
				} else if (map[i][j] == 2) {
					virus.add(new int[] {i, j});
				}
			}
		}
		
		total = emptys.size() - 3;
		
		dfs(0, 0);
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index) {
		if (depth == 3) {
			queue.clear();
			visited = new boolean[N][M];
			
			for(int[] vir : virus) {
				queue.add(vir);
				visited[vir[0]][vir[1]] = true;
			}
			
			bfs();
			return;
		}
		
		for (int i = index; i < emptys.size(); i++) {
			int[] empty = emptys.get(i);
			map[empty[0]][empty[1]] = 1;
			dfs(depth + 1, i + 1);
			map[empty[0]][empty[1]] = 0;
		}
	}
	
	private static void bfs() {
		int nowTotal = total;
		
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextRow = nowNode[0] + dR[i];
				int nextColumn = nowNode[1] + dC[i];
				
				if (0 <= nextRow && nextRow <= N - 1
						&& 0 <= nextColumn && nextColumn <= M - 1
						&& !visited[nextRow][nextColumn]
						&& map[nextRow][nextColumn] == 0) {
					visited[nextRow][nextColumn] = true;
					queue.add(new int[] {nextRow, nextColumn});
					nowTotal--;
				}
			}
		}
		
		answer = Math.max(answer, nowTotal);
	}
}
