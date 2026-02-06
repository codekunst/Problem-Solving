package p11659rangesum;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11659rangesum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		
		long[] numbers = Arrays.stream(br.readLine().split(" "))
								.mapToLong(Long::parseLong)
								.toArray();
		long[] prefix = new long[N + 1];
		
		IntStream.range(0, N).forEach(i -> {
			prefix[i + 1] = prefix[i] + numbers[i];
		});
		
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			
			long rangeSum = prefix[to] - prefix[from - 1];
			bw.write(String.valueOf(rangeSum));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
