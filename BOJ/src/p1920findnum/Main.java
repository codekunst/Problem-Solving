package p1920findnum;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1920findnum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Set<Integer> numbers = new HashSet<>();
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		while (tokenizer.hasMoreTokens()) {
			int number = Integer.parseInt(tokenizer.nextToken());
			numbers.add(number);
		}
		
		int M = Integer.parseInt(br.readLine());
		tokenizer = new StringTokenizer(br.readLine());
		
		while (tokenizer.hasMoreTokens()) {
			int target = Integer.parseInt(tokenizer.nextToken());
			bw.write(numbers.contains(target) ? "1\n" : "0\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
