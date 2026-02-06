package p15903card;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int n, m;
	private static PriorityQueue<Long> queue = new PriorityQueue<>();
	private static long result;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p15903card/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		m = Integer.parseInt(tokenizer.nextToken());
		
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			queue.add(Long.parseLong(tokenizer.nextToken()));
		}
		
		for (int i = 0; i < m; i++) {
			long value = queue.poll() + queue.poll();
			queue.offer(value);
			queue.offer(value);
		}
		
		queue.forEach(element -> result += element);
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
