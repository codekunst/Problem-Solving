package timecomplexity.p2143twoarraysum;

import java.io.*;
import java.util.*;

public class Main2 {
	private static long count;
	private static int T, N, M;
	private static int[] prefixA, prefixB, sumA, sumB;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2143twoarraysum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		prefixA = new int[N + 1];
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			prefixA[i + 1] = prefixA[i] + Integer.parseInt(tokenizer.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		prefixB = new int[M + 1];
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			prefixB[i + 1] = prefixB[i] + Integer.parseInt(tokenizer.nextToken());
		}
		
		sumA = new int[N * (N + 1) / 2];
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				sumA[index++] = prefixA[j] - prefixA[i];
			}
		}
		
		sumB = new int[M * (M + 1) / 2];
		index = 0;
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M + 1; j++) {
				sumB[index++] = prefixB[j] - prefixB[i];
			}
		}
		Arrays.sort(sumB);
		
		for (int sum : sumA) {
			count += upperBound(T - sum) - lowerBound(T - sum);
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long lowerBound(int target) {
		int left = 0, right = sumB.length;
		
		while (left < right) {
			int mid = (left + right) >> 1;
		
			if (sumB[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return (long) left;
	}
	
	private static long upperBound(int target) {
		int left = 0, right = sumB.length;
		
		while (left < right) {
			int mid = (left + right) >> 1;
		
			if (sumB[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return (long) left;
	}
}
