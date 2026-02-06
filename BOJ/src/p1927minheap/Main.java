package p1927minheap;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static PriorityQueue<Integer> queue = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1927minheap/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int query = Integer.parseInt(br.readLine());
			
			if (query == 0 && queue.isEmpty()) {
				bw.write("0\n");
				continue;
			}
			
			if (query == 0) {
				bw.write(String.valueOf(queue.poll()));
				bw.newLine();
				continue;
			}
			
			queue.add(query);
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
