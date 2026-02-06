package timecomplexity.p1806prefix;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 100_001;
	private static int N, S, result = INF;
	private static int[] prefix;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p1806prefix/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		S = Integer.parseInt(tokenizer.nextToken());
		prefix = new int[N + 1];
		
		tokenizer = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			prefix[i + 1] = prefix[i] + Integer.parseInt(tokenizer.nextToken());
		}
		
		int left = 0, right = 0;
		while (right < N + 1) {
			int sum = prefix[right] - prefix[left];
			
			if (sum < S) {
				right++;
			} else {
				result = result > right - left ? right - left : result;
				left++;
			}
		}
		
		bw.write(result == INF ? "0" : String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
