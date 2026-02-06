package p11723set;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11723set/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int set = 0;
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			String command = tokenizer.nextToken();
			
			switch(command) {
			case "add":
				int value = 1 << Integer.parseInt(tokenizer.nextToken());
				set |= value;
				break;
			case "remove":
				value = 1 << Integer.parseInt(tokenizer.nextToken());
				set &= ~value;
				break;
			case "check":
				value = 1 << Integer.parseInt(tokenizer.nextToken());
				bw.write(value == (set & value) ? "1" : "0");
				bw.newLine();
				break;
			case "toggle":
				value = 1 << Integer.parseInt(tokenizer.nextToken());
				set ^= value;
				break;
			case "all":
				set = ~0;
				break;
			case "empty":
				set = 0;
				break;
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
