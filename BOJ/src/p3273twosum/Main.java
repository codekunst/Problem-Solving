package p3273twosum;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p3273twosum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];

		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(tokenizer.nextToken());
		}
		int target = Integer.parseInt(br.readLine());

		int left = 0;
		int right = n - 1;
		int count = 0;

		Arrays.sort(numbers);

		while (left < right) {
			int sum = numbers[left] + numbers[right];
			if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			} else {
				count++;
				left++;
			}
		}

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}
