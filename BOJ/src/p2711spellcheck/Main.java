package p2711spellcheck;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2711spellcheck/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder builder = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(tokenizer.nextToken()) - 1;
			String miss = tokenizer.nextToken();
			builder.append(miss.substring(0, index)).append(miss.substring(index + 1, miss.length())).append("\n");
		}
		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
