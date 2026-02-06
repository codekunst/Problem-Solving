package p10773zero;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p10773zero/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int K = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < K; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (value == 0) {
				stack.pop();
				continue;
			}
			
			stack.push(value);
		}
		
		int answer = 0;
		
		while (!stack.isEmpty()) {
			answer += stack.pop();
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}
