package p9012brackets;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p9012brackets/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		Stack stack = new Stack();
		
		for (int i = 0; i < T; i++) {
			stack.clear();
			boolean flag = false;
			
			String line = br.readLine();
			
			for (Character bracket : line.toCharArray()) {
				if (bracket == '(') {
					stack.push(0);
					continue;
				}
				
				if (stack.isEmpty()) {
					flag = true;
					break;
				}
				
				stack.pop();
			}
			
			if (!stack.isEmpty()) {
				flag = true;
			}
			
			bw.write(flag ? "NO\n" : "YES\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}


class Stack {
	private int size;
	private Node lastNode;
	
	public void push(int value) {
		lastNode = new Node(value, lastNode);
		size++;
	}
	
	public int pop() {
		int ret = -1;
		
		if (size == 0) {
			return ret;
		}
		
		ret = lastNode.getValue();
		lastNode = lastNode.getPreNode();
		size--;
		return ret;
	}
	
	public int peek() {
		return size == 0 ? -1 : lastNode.getValue();
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		lastNode = null;
		size = 0;
	}
}

class Node {
	private final int value;
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