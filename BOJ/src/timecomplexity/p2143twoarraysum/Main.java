package timecomplexity.p2143twoarraysum;

import java.io.*;
import java.util.*;

public class Main {
	private static int T, N, M;
	private static long result;
	private static int[] prefixA, prefixB, sumA, sumB;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2143twoarraysum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		prefixA = new int[N + 1];
		sumA = new int[N * (N + 1) / 2];
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			prefixA[i + 1] = prefixA[i] + Integer.parseInt(tokenizer.nextToken());
		}
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				sumA[index++] = prefixA[j] - prefixA[i];
			}
		}
		Arrays.sort(sumA);
		
		M = Integer.parseInt(br.readLine());
		prefixB = new int[M + 1];
		sumB = new int[M * (M + 1) / 2];
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			prefixB[i + 1] = prefixB[i] + Integer.parseInt(tokenizer.nextToken());
		}
		index = 0;
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M + 1; j++) {
				sumB[index++] = prefixB[j] - prefixB[i];
			}
		}
		Arrays.sort(sumB);
		
		int left = 0, right = sumB.length - 1;
		while (left < sumA.length && right > -1) {
			int sum = sumA[left] + sumB[right];
			
			if (sum < T) {
				int valueA = sumA[left];
				
				while(left < sumA.length && valueA == sumA[left]) {
					left++;					
				}
			} else if (sum > T) {
				int valueB = sumB[right];
				
				while (right > -1 && valueB == sumB[right]) {
					right--;					
				}
			} else {
				int valueA = sumA[left], valueB = sumB[right];
				long countA = 0L, countB = 0L;
				
				while (left < sumA.length && valueA == sumA[left]) {
					left++;
					countA++;
				}
				
				while (right > -1 && valueB == sumB[right]) {
					right--;
					countB++;
				}
				
				result += countA * countB;
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
