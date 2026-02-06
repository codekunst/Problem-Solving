package p1991treetraversal;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static char[][] tree = new char[26][2];
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1991treetraversal/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			int parent = tokenizer.nextToken().charAt(0) - 'A';
			char left = tokenizer.nextToken().charAt(0);
			char right = tokenizer.nextToken().charAt(0);
			tree[parent][0] = left;
			tree[parent][1] = right;
		}
		
		preOrder('A');
		result.append("\n");
		inOrder('A');
		result.append("\n");
		postOrder('A');
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void preOrder(char node) {
		if (node == '.') {
			return;
		}
		
		result.append(node);
		preOrder(tree[node - 'A'][0]);
		preOrder(tree[node - 'A'][1]);
	}
	
	private static void inOrder(char node) {
		if (node == '.') {
			return;
		}
		
		inOrder(tree[node - 'A'][0]);
		result.append(node);
		inOrder(tree[node - 'A'][1]);
	}
	
	private static void postOrder(char node) {
		if (node == '.') {
			return;
		}
		
		postOrder(tree[node - 'A'][0]);
		postOrder(tree[node - 'A'][1]);
		result.append(node);
	}
}
