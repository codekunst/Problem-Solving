package p11286absoluteheap;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int N;
	private static PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> abs(o1) == abs(o2) ? Integer.compare(o1, o2) : Integer.compare(abs(o1), abs(o2)));
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11286absoluteheap/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int query = Integer.parseInt(br.readLine());
			
			if (query == 0 && queue.isEmpty()) {
				bw.write("0\n");
			} else if (query == 0 && !queue.isEmpty()) {
				bw.write(String.valueOf(queue.poll()));
				bw.newLine();
			} else {
				queue.add(query);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int abs(int value) {
		return value > 0 ? value : -value;
	}
}
