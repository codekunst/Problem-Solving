package timecomplexity.p2805treecut;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long M;
	private static long[] tree;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2805treecut/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Long.parseLong(tokenizer.nextToken());
		
		tree = new long[N];
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Long.parseLong(tokenizer.nextToken());
		}
		
		bw.write(String.valueOf(binarySearch()));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long binarySearch() {
		long left = 0L, right = 1_000_000_000L;
		
		while (left < right) {
			long mid = (left + right) / 2L;
			long treeLength = 0L;
			
			for (int i = 0; i < N; i++) {
				treeLength += tree[i] - mid >= 0 ? tree[i] - mid : 0L;
			}
			
			if (treeLength >= M) {
				left = mid + 1L;
			} else {
				right = mid;
			}
		}
		
		return left - 1L;
	}
}
