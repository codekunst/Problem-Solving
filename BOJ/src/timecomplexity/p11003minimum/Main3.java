package timecomplexity.p11003minimum;

import java.io.*;
import java.util.*;

public class Main3 {
	private static int N, L;
	private static Deque deque = new Deque();
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p11003minimum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		L = Integer.parseInt(tokenizer.nextToken());
		
		tokenizer = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(tokenizer.nextToken());
			
			while (!deque.isEmpty() && deque.peekLast()[0] >= value) {
				deque.pollLast();
			}
			
			deque.addLast(new int[] {value, i});
			
			while (deque.peekFirst()[1] < i - L + 1) {
				deque.pollFirst();
			}
			
			result.append(deque.peekFirst()[0]).append(" ");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}

class Deque {
	private int size;
	private Node firstNode, lastNode;
	
	public void addFirst(int[] value) {
		if (size == 0) {
			Node node = new Node(value, null, null);
			firstNode = lastNode = node;
			size++;
			
			return;
		}
		
		Node node = new Node(value, null, firstNode);
		firstNode.setPreNode(node);
		firstNode = node;
		size++;
	}
	
	public void addLast(int[] value) {
		if (size == 0) {
			Node node = new Node(value, null, null);
			firstNode = lastNode = node;
			size++;
			
			return;
		}
		
		Node node = new Node(value, lastNode, null);
		lastNode.setNextNode(node);
		lastNode = node;
		size++;
	}
	
	public int[] pollFirst() {
		int[] firstValue = firstNode.getValue();
		firstNode = firstNode.getNextNode();
		size--;
		
		return firstValue;
	}
	
	public int[] pollLast() {
		int[] lastValue = lastNode.getValue();
		lastNode = lastNode.getPreNode();
		size--;
		
		return lastValue;
	}
	
	public int[] peekFirst() {
		return firstNode.getValue();
	}
	
	public int[] peekLast() {
		return lastNode.getValue();
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}

class Node {
	private int[] value;
	private Node preNode, nextNode;
	
	public Node(int[] value, Node preNode, Node nextNode) {
		this.value = value;
		this.preNode = preNode;
		this.nextNode = nextNode;
	}
	
	public int[] getValue() {
		return this.value;
	}
	
	public Node getPreNode() {
		return this.preNode;
	}
	
	public Node getNextNode() {
		return this.nextNode;
	}
	
	public void setPreNode(Node preNode) {
		this.preNode = preNode;
	}
	
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}
