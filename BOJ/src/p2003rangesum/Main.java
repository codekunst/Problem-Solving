package p2003rangesum;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2003rangesum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		
		int[] numbers = new int[N];
		int[] prefix = new int[N + 1];
		
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(tokenizer.nextToken());
			prefix[i + 1] = prefix[i] + numbers[i];
		}
		
		int left = 0;
		int right = 1;
		int count = 0;
		
		while(right < N + 1) {
			int sum = prefix[right] - prefix[left];
			
			if(M < sum) {
				left++;
			} else if (M > sum) {
				right++;
			} else {
				right++;
				count++;
			}
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}
