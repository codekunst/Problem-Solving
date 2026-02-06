package basic.p1339wordmath;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, result;
	private static Integer[] value = new Integer[26];
	private static int[] digits = { 1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000 };
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p1339wordmath/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Arrays.fill(value, 0);
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			
			for (int j = 0; j < word.length(); j++) {
				value[word.charAt(word.length() - j - 1) - 'A'] += digits[j];
			}
		}
		
		Arrays.sort(value, Comparator.reverseOrder());
		
		for (int i = 0; i < 10; i++) {
			result += (9 - i) * value[i];
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
