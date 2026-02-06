package basic.p9663nqueen;

import java.io.*;

public class Main {
	private static int N, result;
	private static boolean[] column, positive, negative;
	
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p9663nqueen/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		column = new boolean[N];
		positive = new boolean[2 * N];
		negative = new boolean[2 * N];
		
		dfs(0);
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth) {
		if (depth == N) {
			result++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!column[i] && !positive[depth + i] && !negative[depth - i + N]) {
				column[i] = true;
				positive[depth + i] = true;
				negative[depth - i + N] = true;
				dfs(depth + 1);
				column[i] = false;
				positive[depth + i] = false;
				negative[depth - i + N] = false;
			}
		}
	}
}
