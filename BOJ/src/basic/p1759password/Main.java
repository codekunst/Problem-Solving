package basic.p1759password;

import java.io.*;
import java.util.*;

public class Main {
	private static int L, C;
	private static char[] alphabets;
	private static boolean[] visited, isVowel;
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p1759password/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		L = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		
		alphabets = new char[C];
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabets[i] = tokenizer.nextToken().charAt(0);
		}
		Arrays.sort(alphabets);
		
		visited = new boolean[C];
		
		isVowel = new boolean[26];
		isVowel['a' - 'a']
				= isVowel['e' - 'a']
				= isVowel['i' - 'a']
				= isVowel['o' - 'a']
				= isVowel['u' - 'a']
				= true;
		
		dfs(0, 0);
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int depth, int index) {
		if (depth == L) {
			StringBuilder nowResult = new StringBuilder();
			boolean vowel = false;
			int consonant = 0;
			
			for (int i = 0; i < C; i++) {
				if (visited[i]) {
					nowResult.append(alphabets[i]);
					
					if (isVowel[alphabets[i] - 'a']) {
						vowel = true;
					} else {
						consonant++;
					}
				}
			}
			nowResult.append("\n");
			
			
			if (vowel && consonant > 1) {
				result.append(nowResult);
			}
			
			return;
		}
		
		for (int i = index; i < C; i++) {
			visited[i] = true;
			dfs(depth + 1, i + 1);
			visited[i] = false;
		}
	}
}
