package timecomplexity.p11003minimum;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 1_000_000_001;
	private static int N, L, leaf;
	private static int[] tree;
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p11003minimum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		L = Integer.parseInt(tokenizer.nextToken());
		
		while (N > 1 << leaf) {
			leaf++;
		}
		leaf = 1 << leaf;
		
		tokenizer = new StringTokenizer(br.readLine());
		tree = new int[2 * leaf];
		for (int i = leaf; i < leaf + N; i++) {
			tree[i] = Integer.parseInt(tokenizer.nextToken());
		}
		for (int i = leaf - 1; i > 0; i--) {
			tree[i] = tree[2 * i] > tree[2 * i + 1] ? tree[2 * i + 1] : tree[2 * i];
		}
		
		for (int i = 0; i < N; i++) {
			result.append(query(i - L + 1 < 0 ? 0 : i - L + 1, i)).append(" ");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int query(int indexA, int indexB) {
		indexA += leaf;
		indexB += leaf;
		int minimum = INF;
		
		while (indexA <= indexB) {
			if (indexA % 2 == 1) {
				minimum = minimum > tree[indexA] ? tree[indexA] : minimum;
				indexA++;
			}
			
			if (indexB % 2 == 0) {
				minimum = minimum > tree[indexB] ? tree[indexB] : minimum;
				indexB--;
			}
			
			indexA >>= 1;
			indexB >>= 1;
		}
		
		return minimum;
	}
}
