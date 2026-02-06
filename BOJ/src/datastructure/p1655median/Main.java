package datastructure.p1655median;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static PriorityQueue<Integer> naturalQueue = new PriorityQueue<Integer>(),
			reversedQueue = new PriorityQueue<>(Comparator.reverseOrder());
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/datastructure/p1655median/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		reversedQueue.offer(Integer.parseInt(br.readLine()));
		result.append(reversedQueue.peek()).append("\n");
		
		for (int i = 0; i < N - 1; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (reversedQueue.peek() < value) {
				naturalQueue.offer(value);
			} else {
				reversedQueue.offer(value);
			}
			
			while (naturalQueue.size() > reversedQueue.size()) {
				reversedQueue.offer(naturalQueue.poll());
			}
			
			while (naturalQueue.size() + 1 < reversedQueue.size()) {
				naturalQueue.offer(reversedQueue.poll());
			}
			
			result.append(reversedQueue.peek()).append("\n");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
