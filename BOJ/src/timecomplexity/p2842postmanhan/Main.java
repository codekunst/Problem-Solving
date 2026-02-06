package timecomplexity.p2842postmanhan;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, min, max;
	private static int[] postOffice;
	private static List<int[]> homes = new ArrayList<>();
	private static int[][] map, heights, maxHeights, minHeights;
	private static boolean flag;
	private static int[] dY = {0, 1, 1, 1, 0, -1, -1, -1}, dX = {1, 1, 0, -1, -1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2842postmanhan/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		heights = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			
			for (int j = 0; j < N; j++) {
				switch (line[j]) {
					case 'P':
						map[i][j] = 1;
						postOffice = new int[] {i, j};
						break;
					case 'K':
						map[i][j] = -1;
						homes.add(new int[] {i, j});
						break;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				heights[i][j] = Integer.parseInt(tokenizer.nextToken());
				max = heights[i][j] > max ? heights[i][j] : max;
			}
		}
		
		bw.write(String.valueOf(lowerBound()));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int lowerBound() {
		int left = min, right = max;
		
		while (left < right) {
			int mid = (left + right) >> 1;
		
			if(flag) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return left;
	}
}
