package timecomplexity.p2748fibbonacci2;

import java.io.*;

public class Main {
	private static int N;
	private static long[] dp;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2748fibbonacci2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];
		dp[0] = 0L;
		dp[1] = 1L;
		
		for (int i = 2; i < N + 1; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		for (int i = 0; i < 91; i++) {
			bw.write(String.valueOf(dp[i]));
			bw.write("L, ");
		}
		
//		bw.write(String.valueOf(dp[N]));
		bw.flush();
		bw.close();
		br.close();
	}
}
