package basic.p1920find;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] numbers;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p1920find/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		M = Integer.parseInt(br.readLine());
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			bw.write(binarySearch(Integer.parseInt(tokenizer.nextToken())) ? "1\n" : "0\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static boolean binarySearch(int target) {
		int left = 0;
		int right = N - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (numbers[mid] < target) {
				left = mid + 1;
			} else if (numbers[mid] > target) {
				right = mid - 1;
			} else {
				return true;
			}
		}
		
		return false;
	}
}
