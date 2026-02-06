package p10870fibonacci5;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p10870fibonacci5/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		int answer = fibonacci(n);
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int fibonacci(int n) {
		return n == 0 ? 0 : n == 1 ? 1 : fibonacci(n - 1) + fibonacci(n - 2);
	}
}
