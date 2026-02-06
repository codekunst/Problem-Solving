package p6603lotto;

import java.io.*;
import java.util.*;

public class Main {
	private static int k = -1;
	private static int[] array, selected = new int[6];
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p6603lotto/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		while (k != 0) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			k = Integer.parseInt(tokenizer.nextToken());
			array = new int[k];
			
			for (int i = 0; i < k; i++) {
				array[i] = Integer.parseInt(tokenizer.nextToken());
			}
			
			dfs(0, 0);
			
			result.append("\n");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index) {
		if (depth == 6) {
			for (int element : selected) {
				result.append(element).append(" ");
			}
			result.append("\n");
			return;
		}
		
		for (int i = index; i < k; i++) {
			selected[depth] = array[i];
			dfs(depth + 1, i + 1);
		}
	}
}
