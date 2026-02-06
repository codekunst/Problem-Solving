package numbertheory.moduloinverse;

import java.io.*;

public class Main {
	private static int a = 12, m = 29, inverse;
	private static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		findModuloInverse(a, m);
		
		if (flag) {
			bw.write(a + " * " + inverse + " = " + a * inverse % m + " (mod " + m + ")");		
		} else {
			bw.write("There is not inverse of " + a + " modulo " + m);
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void findModuloInverse(int a, int m) {
		if (m == 0) {
			return;
		}
		
		int mod = m;
		int x0 = 1, x1 = 0;
		
		while (m != 0) {
			int q = a / m;
			int r = a % m;
			
			int x2 = x0 - q * x1;
			
			a = m;
			m = r;
			x0 = x1;
			x1 = x2;
		}
		
		if (a != 1) {
			return;
		}
		
		inverse = (x0 + mod) % mod;
		flag = true;
	}
}
