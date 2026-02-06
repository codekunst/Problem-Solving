package numbertheory.chineseremaindertheorem;

import java.io.*;

public class Main {
	private static long solution;
	private static long[] remainders = {4L, 2L, 3L, 8L, 7L}, modulos = {11L, 13L, 17L, 19L, 23L};
	
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		solveCRT(remainders, modulos);
		
		for (int i = 0; i < remainders.length; i++) {
			bw.write(solution + " = " + solution % modulos[i] + " (mod " + modulos[i] + ")\n");
		}
		bw.write("Solution: " + solution);
		bw.flush();
		bw.close();
	}
	
	private static void solveCRT(long[] remainders, long[] modulos) {
		long M = 1L;
		
		for (long mod : modulos) {
			M *= mod;
		}
		
		for (int i = 0; i < remainders.length; i++) {
			long a = remainders[i];
			long m = M / modulos[i];
			long y = moduloInverse(m, modulos[i]);
			
			solution += a * m * y;
		}
		
		solution %= M;
	}
	
	private static long moduloInverse(long a, long m) {
		if (m == 0L) {
			return 0L;
		}
		
		long mod = m;
		long x0 = 1L, x1 = 0L;
		
		while (m != 0L) {
			long q = a / m;
			long r = a % m;
			
			long x2 = x0 - q * x1;
			
			a = m;
			m = r;
			x0 = x1;
			x1 = x2;
		}
		
		return (x0 + mod) % mod;
	}
}
