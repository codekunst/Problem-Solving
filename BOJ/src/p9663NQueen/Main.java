package p9663NQueen;

import java.io.*;

public class Main {
	private static int N, answer;
	private static boolean[] col, positive, negative;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p9663NQueen/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		col = new boolean[N];
		positive = new boolean[2 * N];
		negative = new boolean[2 * N];
		
		dfs(0);
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth) {
		if (depth == N) {
			answer++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!col[i] && !positive[depth + i] && !negative[N + depth - i]) {
				col[i] = true;
				positive[depth + i] = true;
				negative[N + depth - i] = true;
				dfs(depth + 1);
				col[i] = false;
				positive[depth + i] = false;
				negative[N + depth - i] = false;
			}
		}
	}
}
