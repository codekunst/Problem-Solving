package p1954snailnumber;

import java.io.*;

public class Solution {
	private static int[] dR = { 0, 1, 0, -1 }, dC = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1954snailnumber/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringBuilder builder = new StringBuilder();

		for (int tc = 1; tc < T + 1; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] result = new int[N][N];

			search(0, 0, 1, N, result);

			builder.append("#").append(tc).append("\n");

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					builder.append(result[i][j]).append(" ");
				}
				builder.append("\n");
			}
		}

		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void search(int row, int column, int count, int N, int[][] result) {
		if (N < 0) {
			return;
		}

		if (N == 1) {
			result[row][column] = count;
			return;
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < N - 1; j++) {
				result[row][column] = count++;
				row += dR[i];
				column += dC[i];
			}
		}

		search(row + 1, column + 1, count, N - 2, result);
	}
}
