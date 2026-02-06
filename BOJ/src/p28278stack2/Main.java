package p28278stack2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static Stack stack = new Stack();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p28278stack2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			
			int query = Integer.parseInt(tokenizer.nextToken());
			
			switch(query) {
			case 1:
				int value = Integer.parseInt(tokenizer.nextToken());
				stack.push(value);
				break;
			case 2:
				bw.write(String.valueOf(stack.pop()));
				bw.newLine();
				break;
			case 3:
				bw.write(String.valueOf(stack.size()));
				bw.newLine();
				break;
			case 4:
				bw.write(stack.isEmpty() ? "1\n" : "0\n");
				break;
			case 5:
				bw.write(String.valueOf(stack.peek()));
				bw.newLine();
				break;
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class Stack {
	private Node lastNode;
	private int size;
	
	public void push(int value) {
		lastNode = new Node(value, lastNode);
		size++;
	}
	
	public int pop() {
		if (isEmpty()) {
			return -1;
		}
		
		int ret = lastNode.getValue();
		lastNode = lastNode.getPreNode();
		size--;
		return ret;
	}
	
	public int peek() {
		if (isEmpty()) {
			return -1;
		}
		
		return lastNode.getValue();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}

class Node {
	private int value;
	private Node preNode;
	
	public Node(int value, Node preNode) {
		this.value = value;
		this.preNode = preNode;
	}
	
	public int getValue() {
		return value;
	}
	
	public Node getPreNode() {
		return preNode;
	}
}
