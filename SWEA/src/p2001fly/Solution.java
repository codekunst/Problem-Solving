package p2001fly;

import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2001fly/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		
		for (int tc = 1; tc < T + 1; tc++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(tokenizer.nextToken());
			int M = Integer.parseInt(tokenizer.nextToken());
			
			int[][] map = new int[N][N];
			int[][] prefix = new int[N + 1][N + 1];
			
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				
				int sum = 0;
				
				for (int j = 0; j < N; j++) {
					sum += map[i][j];
					prefix[i + 1][j + 1] = prefix[i][j + 1] + sum;
				}
			}
			
			int max = -1;
			
			for (int i = 0; i < N - M + 1; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					int value = prefix[i + M][j + M] - prefix[i + M][j] - prefix[i][j + M] + prefix[i][j];
					max = Math.max(max, value);
				}
			}
			
			answer.append("#").append(tc).append(" ").append(max).append("\n");
		}
		
		bw.write(answer.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
