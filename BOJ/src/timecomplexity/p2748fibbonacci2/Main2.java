package timecomplexity.p2748fibbonacci2;

import java.io.*;

public class Main2 {
	private static int N;
	private static long[] dp;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2748fibbonacci2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];
		
		bw.write(String.valueOf(fibonacci(N)));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long fibonacci(int n) {
		if (n == 0) {
			return 0L;
		}
		
		if (n == 1) {
			return 1L;
		}
		
		return dp[n] == 0L ? dp[n] = fibonacci(n - 1) + fibonacci(n - 2) : dp[n];
	}
}
