package p2309combination;

import java.io.*;
import java.util.*;

public class Main2 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2309combination/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] members = new int[9];
		int total = 0;
		for (int i = 0; i < 9; i++) {
			members[i] = Integer.parseInt(br.readLine());
			total += members[i];
		}
		
		List<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[9];
		
		Outer: for(int i = 0; i < 9; i++) {
			for(int j = i + 1; j < 9; j++) {
				int sum = members[i] + members[j];
				if(total - sum == 100) {
					visited[i] = true;
					visited[j] = true;
					break Outer;
				}
			}
		}
		
		for(int i = 0; i < 9; i++) {
			if(!visited[i]) {
				result.add(members[i]);
			}
		}
		
		Collections.sort(result);
		
		StringBuilder builder = new StringBuilder();
		for(Integer res : result) {
			builder.append(res).append("\n");
		}
		
		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
