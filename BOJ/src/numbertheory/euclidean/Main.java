package numbertheory.euclidean;

import java.io.*;

public class Main {
	private static int N = 24, M = 36, gcd, lcm;
	
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
//		gcdRecursive(N, M);
		gcdLoop(N, M);
		
		bw.write("gcd(" + N + ", " + M + ") = " + gcd);
		bw.newLine();
		bw.write("lcm(" + N + ", " + M + ") = " + lcm);
		bw.flush();
		bw.close();
	}
	
	private static void gcdRecursive(int a, int b) {
		if (b == 0) {
			gcd = a;
			lcm = N * M / gcd;
			return;
		}
		
		gcdRecursive(b, a % b);
	}
	
	private static void gcdLoop(int a, int b) {
		while (b != 0) {
			int nowA = a;
			a = b;
			b = nowA % b;
		}
		
		gcd = a;
		lcm = N * M / gcd;
	}
}
