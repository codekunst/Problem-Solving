package p11723set;

import java.io.*;
import java.util.*;

public class Main2 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11723set/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Set<Integer> set = new HashSet<>();
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			String command = tokenizer.nextToken();
			
			switch(command) {
			case "add":
				int value = Integer.parseInt(tokenizer.nextToken());
				set.add(value);
				break;
			case "remove":
				value = Integer.parseInt(tokenizer.nextToken());
				set.remove(value);
				break;
			case "check":
				value = Integer.parseInt(tokenizer.nextToken());
				bw.write(set.contains(value) ? "1" : "0");
				bw.newLine();
				break;
			case "toggle":
				value = Integer.parseInt(tokenizer.nextToken());
				if (set.contains(value)) {
					set.remove(value);
				} else {
					set.add(value);
				}
				break;
			case "all":
				for (int j = 1; j < 21; j++) {
					set.add(j);
				}
				break;
			case "empty":
				set.clear();
				break;
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
