package p11444fibonacci6;

import java.io.*;
import java.util.*;

public class Main {
	private static final int MOD = 1_000_000_007;
	private static long n;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11444fibonacci6/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Long.parseLong(br.readLine());
		
		if (n == 1L) {
			bw.write("1");
			bw.flush();
			bw.close();
			br.close();
			return;
		}
		
		int twoBefore = 0;
		int oneBefore = 1;
		int now = 1;
		
		for (long i = 3L; i < n + 1; i++) {
			twoBefore = oneBefore;
			oneBefore = now;
			now = (oneBefore + twoBefore) % MOD;
		}
		
		bw.write(String.valueOf(now));
		bw.flush();
		bw.close();
		br.close();
	}
}
