package p10988palindrome;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p10988palindrome/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] input = br.readLine().toCharArray();
		
		int left = 0;
		int right = input.length - 1;
		boolean result = true;
		
		while (left < right) {
			if (input[left] != input[right]) {
				result = false;
				break;
			}
			left++;
			right--;
		}
		
		bw.write(result ? "1" : "0");
		bw.flush();
		bw.close();
		br.close();
	}
}
