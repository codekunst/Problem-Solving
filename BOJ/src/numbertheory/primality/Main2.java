package numbertheory.primality;

import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Main2 {
	private static BigInteger N = new BigInteger("43489723408745904117423947298365732849720347074348972340874590411742394729836573284972035319");
	private static BigInteger[] bases = new BigInteger[10];
	
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
	
	private static boolean isPrime(BigInteger number) {
		if (number.equals(BigInteger.valueOf(2L)) || number.equals(BigInteger.valueOf(3L))) {
			return true;
		}
		
		if (number.equals(BigInteger.ONE) || number.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
			return false;
		}
		
		BigInteger d = number.subtract(BigInteger.ONE);
		int s = 0;
		
		while (d.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
			d = d.shiftRight(1);
			s++;
		}
		
		makeBaseNumbers();
		for (BigInteger a : bases) {
			BigInteger value = moduloExponential(a, d, number);
			
			if (value.equals(BigInteger.ONE) || value.equals(number.subtract(BigInteger.ONE))) {
				continue;
			}
			
			boolean flag = false;
			
			for (int i = 0; i < s - 1; i++) {
				value = moduloMultiply(value, value, number);
				
				if (value.equals(number.subtract(BigInteger.ONE))) {
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
	
	private static void makeBaseNumbers() {
		Random random = new Random();
		
		for (int i = 0; i < bases.length; i++) {
			bases[i] = new BigInteger(64, random).mod(N.subtract(BigInteger.valueOf(3L))).add(BigInteger.valueOf(2L));
		}
	}
	
	private static BigInteger moduloExponential(BigInteger a, BigInteger b, BigInteger mod) {
		BigInteger result = BigInteger.ONE;
		
		while (b.compareTo(BigInteger.ZERO) == 1) {
			if (b.and(BigInteger.ONE).equals(BigInteger.ONE)) {
				result = moduloMultiply(a, result, mod);
			}
			
			a = moduloMultiply(a, a, mod);
			b = b.shiftRight(1);
		}
		
		return result;
	}
	
	private static BigInteger moduloMultiply(BigInteger a, BigInteger b, BigInteger mod) {
		BigInteger result = BigInteger.ZERO;
		
		while (b.compareTo(BigInteger.ZERO) == 1) {
			if (b.and(BigInteger.ONE).equals(BigInteger.ONE)) {
				result = a.add(result).mod(mod);
			}
			
			a = a.shiftLeft(1).mod(mod);
			b = b.shiftRight(1);
		}
		
		return result;
	}
}
