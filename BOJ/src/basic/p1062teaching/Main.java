package basic.p1062teaching;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, result;
	private static String[] words;
	private static boolean[] visited = new boolean[26];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/basic/p1062teaching/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken()) - 5;
		
		if (K < 0) {
			bw.write("0");
			bw.flush();
			bw.close();
			br.close();
			System.exit(0);
		}
		
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine().replaceAll("[acint]", "");
		}
		
		visited['a' - 'a']
				= visited['c' - 'a']
				= visited['i' - 'a']
				= visited['n' - 'a']
				= visited['t' - 'a']
				= true;
		
		dfs(0, 0);
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index) {
		if (depth == K) {
			int count = 0;
			
			for (String word : words) {
				boolean flag = false;
				
				for (int i = 0; i < word.length(); i++) {
					if (!visited[word.charAt(i) - 'a']) {
						flag = true;
						break;
					}
				}
				
				if (!flag) {
					count++;
				}
			}
			
			result = count > result ? count : result;
			return;
		}
		
		for (int i = index; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
}
