package p15656NM;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] array, selected;
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p15656NM/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).distinct().sorted().toArray();
		selected = new int[M];
		
		dfs(0);
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth) {
		if (depth == M) {
			for (int element : selected) {
				result.append(element).append(" ");
			}
			result.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			selected[depth] = array[i];
			dfs(depth + 1);
		}
	}
}
