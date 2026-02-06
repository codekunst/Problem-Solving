package day08;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class AdjList {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/day08/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		List<Integer>[] adjList = new List[V];
		
		IntStream.range(0, V).forEach(i -> adjList[i] = new ArrayList<>());
		
		for (int j = 0; j < E; j++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			adjList[from].add(to);
		}
		
		for (List<Integer> adj : adjList) {
			bw.write(adj.toString());
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
