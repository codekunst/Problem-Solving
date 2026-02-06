package numbertheory.primality;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
	private static long N = 4_348_972_340_874_590_411L;
	private static long[] bases = new long[10]; // 확률적 방법
//	private static long[] bases = {2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L}; // 결정론
	
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		
		if (isPrime(N)) {
			bw.write(formatter.format(N) + " is a prime number.");
		} else {
			bw.write(formatter.format(N) + " is not a prime number.");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void makeBaseNumbers() {
		Random random = new Random();
		
		for (int i = 0; i < bases.length; i++) {
			bases[i] = random.nextLong(2L, N - 1L);
		}
	}
	
	private static boolean isPrime(long number) {
		if (number == 2L || number == 3L) {
			return true;
		}
		
		if (number == 1L || (number & 1L) == 0L) {
			return false;
		}
		
		long d = number - 1L;
		int s = 0;
		
		while ((d & 1L) == 0L) {
			d >>= 1L;
			s++;
		}
		
		makeBaseNumbers();
		for (long a : bases) {
			long value = moduloExponential(a, d, number);
			
			if (value == 1L || value == number - 1L) {
				continue;
			}
			
			boolean flag = false;
			
			for (int i = 0; i < s - 1; i++) {
				value = moduloMultiply(value, value, number);
				
				if (value == number - 1L) {
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				return false;
			}
		}
		
		return true;
	}
	
	private static long moduloExponential(long a, long b, long mod) {
		long result = 1L;
		
		while (b > 0L) {
			if ((b & 1L) == 1L) {
				result = moduloMultiply(a, result, mod);
			}
			
			a = moduloMultiply(a, a, mod);
			b >>= 1L;
		}
		
		return result;
	}
	
	private static long moduloMultiply(long a, long b, long mod) {
		long result = 0L;
		
		while (b > 0L) {
			if ((b & 1L) == 1L) {
				result = (a + result) % mod;
			}
			
			a = (a << 1L) % mod;
			b >>= 1L;
		}
		
		return result;
	}
}
