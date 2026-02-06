package p23260gcd;

import java.io.*;
import java.util.*;

public class Main {
	private static final int MOD = 1_000_000_007;
	private static int N, K, result;
	private static int[] array;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/p23260gcd/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		
		array = new int[N];
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(tokenizer.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			dfs(1, i + 1, array[i]);
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index, int gcd) {
		if (depth == K) {
			if (gcd == 1) {
				result = (result + 1) % MOD;
			}
			return;
		}
		
		if (gcd == 1) {
			result = (result + combination(N - index, K - depth)) % MOD;
			return;
		}
		
		for (int i = index; i < N; i++) {
			dfs(depth + 1, i + 1, gcd(gcd, array[i]));
		}
	}
	
	private static int gcd(int a, int b) {
		while (b != 0) {
			int nowA = a;
			a = b;
			b = nowA % b;
		}
		
		return a;
	}
	
	private static int combination(int n, int r) {
		int result = 1;
		
		for (int i = 0; i < r; i++) {
			result = (result) * (n - i) % MOD;
		}
		
		for (int i = 0; i < n - r; i++) {
			result /= r - i;
		}
		
		return result;
	}
}
