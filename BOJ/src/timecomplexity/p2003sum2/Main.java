package timecomplexity.p2003sum2;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, result;
	private static int[] array;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2003sum2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		array = new int[N + 1];
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(tokenizer.nextToken());
		}
		
		int left = 0, right = 0, sum = 0;
		
		while (right < N + 1) {
			if (sum > M) {
				sum -= array[left++];
			} else if (sum < M) {
				sum += array[right++];
			} else {
				sum -= array[left++];
				result++;
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
