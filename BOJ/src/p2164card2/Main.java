package p2164card2;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p2164card2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Queue queue = new Queue();
		
		for (int i = 1; i < N + 1; i++) {
			queue.add(i);
		}
		
		for (int i = 0; i < N - 1; i++) {
			queue.poll();
			queue.add(queue.poll());
		}
		bw.write(String.valueOf(queue.poll()));
		bw.flush();
		bw.close();
		br.close();
	}
}

class Queue {
	private int size;
	private Node firstNode, lastNode;

	public void add(int value) {
		if (isEmpty()) {
			firstNode = new Node(value);
			lastNode = firstNode;
			size++;
			return;
		}

		Node newNode = new Node(value);
		lastNode.setNextNode(newNode);
		lastNode = newNode;
		size++;
	}

	public int poll() {
		if (isEmpty()) {
			return -1;
		}

		int ret = peek();
		firstNode = firstNode.getNextNode();
		size--;
		return ret;
	}

	public int peek() {
		return isEmpty() ? -1 : firstNode.getValue();
	}

	public boolean isEmpty() {
		return size == 0;
	}
}

class Node {
	private int value;
	private Node nextNode;

	public Node(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}
