package p10826fibonacci4;

import java.io.*;
import java.math.*;
import java.util.stream.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p10826fibonacci4/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		if(n == 0 || n == 1) {
			bw.write(n == 0 ? "0" : "1");
			bw.flush();
			bw.close();
			br.close();
			return;
		}
		
		BigInteger[] dp = new BigInteger[n + 1];
		dp[0] = BigInteger.ZERO;
		dp[1] = BigInteger.ONE;
		
		IntStream.range(2, n + 1).forEach(i -> {
			dp[i] = dp[i - 1].add(dp[i - 2]);
		});
		
		BigInteger answer = dp[n];
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}
