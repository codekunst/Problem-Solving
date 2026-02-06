package p2805tree;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2805tree/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		long M = Long.parseLong(tokenizer.nextToken());
		
		long[] trees = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		
		long left = 0L;
		long right = 1_000_000_001L;
		
		while(left <= right) {
			long mid = (left + right) / 2L;
			
			long cut = Arrays.stream(trees)
								.reduce(0L, (acc, tree) -> acc + Math.max(0L, tree - mid));
			
			if(cut < M) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		bw.write(String.valueOf(right));
		bw.flush();
		bw.close();
		br.close();
	}
}
