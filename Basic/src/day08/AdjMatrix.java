package day08;

import java.io.*;
import java.util.*;

public class AdjMatrix {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/day08/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[V][V];
		
		for (int i = 0; i < E; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			
			adjMatrix[from][to] = 1;
		}
		
		for (int[] adj : adjMatrix) {
			bw.write(Arrays.toString(adj));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
