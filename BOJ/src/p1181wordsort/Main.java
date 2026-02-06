package p1181wordsort;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1181wordsort/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Set<String> wordSet = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			wordSet.add(br.readLine());
		}
		
		List<String> words = new ArrayList<>(wordSet);
		
		Collections.sort(words, (o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());
		
		StringBuilder builder = new StringBuilder();
		words.stream().forEach(word -> builder.append(word).append("\n"));
		
		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
