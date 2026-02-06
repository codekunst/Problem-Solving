package p7785company;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p7785company/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		Set<String> workers = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			String name = tokenizer.nextToken();
			if (workers.contains(name)) {
				workers.remove(name);
			} else {
				workers.add(name);
			}
		}
		
		List<String> answer = new ArrayList<>(workers);
		Collections.sort(answer, Comparator.reverseOrder());
		
		for(String ans : answer) {
			bw.write(ans);
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
