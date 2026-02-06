package p15657NM;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] array, selected;
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p15657NM/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		selected = new int[M];
		
		dfs(0, 0);
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index) {
		if (depth == M) {
			for (int element : selected) {
				result.append(element).append(" ");
			}
			result.append("\n");
			return;
		}
		
		for (int i = index; i < N; i++) {
			selected[depth] = array[i];
			dfs(depth + 1, i);
		}
	}
}
