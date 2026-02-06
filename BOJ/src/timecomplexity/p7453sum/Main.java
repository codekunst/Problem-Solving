package timecomplexity.p7453sum;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long count;
	private static long[] A, B, C, D, AB, CD;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p7453sum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		A = new long[N];
		B = new long[N];
		C = new long[N];
		D = new long[N];
		AB = new long[N * N];
		CD = new long[N * N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(tokenizer.nextToken());
			B[i] = Long.parseLong(tokenizer.nextToken());
			C[i] = Long.parseLong(tokenizer.nextToken());
			D[i] = Long.parseLong(tokenizer.nextToken());
		}
		
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[index] = A[i] + B[j];
				CD[index++] = C[i] + D[j];
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		index = 0;
		while (index < AB.length) {
			long valueAB = AB[index], countA = 0L;
			
			while (index < AB.length && valueAB == AB[index]) {
				index++;
				countA++;
			}
			
			long countB = upperBound(-valueAB) - lowerBound(-valueAB);
			
			count += countA * countB;
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long lowerBound(long target) {
		int left = 0, right = CD.length;
		
		while (left < right) {
			int mid = (left + right) >> 1;
			
			if (CD[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return (long) left;
	}
	
	private static long upperBound(long target) {
		int left = 0, right = CD.length;
		
		while (left < right) {
			int mid = (left + right) >> 1;
		
			if (CD[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return (long) left;
	}
}
