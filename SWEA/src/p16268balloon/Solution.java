package p16268balloon;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
	private static int[] dR = { 1, -1, 0, 0 }, dC = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p16268balloon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		StringBuilder builder = new StringBuilder();

		for (int tc = 1; tc < T + 1; tc++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(tokenizer.nextToken());
			int M = Integer.parseInt(tokenizer.nextToken());

			int[][] map = new int[N + 2][M + 2];

			for (int i = 0; i < N; i++) {
				int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				for (int j = 0; j < M; j++) {
					map[i + 1][j + 1] = line[j];
				}
			}

			int max = -1;

			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M; j++) {
					int row = i;
					int col = j;

					int value = IntStream.range(0, 4).reduce(map[i][j],
							(acc, k) -> acc + map[row + dR[k]][col + dC[k]]);
					max = Math.max(max, value);
				}
			}

			builder.append("#").append(tc).append(" ").append(max).append("\n");
		}

		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
