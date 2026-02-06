package p11286absoluteheap;

import java.io.*;

public class Main {
	private static int N;
	private static AbsoluteHeap heap;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p11286absoluteheap/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		heap = new AbsoluteHeap(2 * N);
		
		for (int i = 0; i < N; i++) {
			int query = Integer.parseInt(br.readLine());
			
			if (query == 0) {
				bw.write(String.valueOf(heap.poll()));
				bw.newLine();
			} else {
				heap.add(query);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class AbsoluteHeap {
	private int[] tree;
	private int size;
	
	public AbsoluteHeap(int capacity) {
		this.tree = new int[capacity];
	}
	
	public void add(int value) {
		int index = ++size;
		tree[index] = value;
		
		while (index / 2 > 0
				&& compare(tree[index / 2], tree[index]) == 1) {
			int temp = tree[index];
			tree[index] = tree[index / 2];
			tree[index / 2] = temp;
			index /= 2;
		}
	}
	
	public int poll() {
		if (isEmpty()) {
			return 0;
		}
		
		int index = 1;
		int ret = tree[index];
		tree[index] = tree[size--];
		
		while (true) {
			if (2 * index < size) {
				int compared = compare(tree[index], tree[2 * index], tree[2 * index + 1]);
				
				if (compared == 1) {
					int temp = tree[index];
					tree[index] = tree[2 * index];
					tree[2 * index] = temp;
					index *= 2;
				} else if (compared == 2) {
					int temp = tree[index];
					tree[index] = tree[2 * index + 1];
					tree[2 * index + 1] = temp;
					index = 2 * index + 1;
				} else {
					break;
				}
			} else if (2 * index == size) {
				int compared = compare(tree[index], tree[2 * index]);
				
				if (compared == 1) {
					int temp = tree[index];
					tree[index] = tree[2 * index];
					tree[2 * index] = temp;
					index *= 2;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		
		return ret;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private int absolute(int value) {
		return value > 0 ? value : -value;
	}
	
	private int compare(int parent, int child) {
		if (absolute(parent) > absolute(child)) {
			return 1;
		}
		
		if (absolute(parent) == absolute(child)
				&& parent > child) {
			return 1;
		}
		
		return -1;
	}
	
	private int compare(int parent, int left, int right) {
		if (absolute(parent) > Math.min(absolute(left), absolute(right))) {
			if (absolute(left) > absolute(right)) {
				return 2;
			} else if (absolute(right) > absolute(left)) {
				return 1;
			} else if (left > right) {
				return 2;
			} else if (right > left) {
				return 1;
			} else {
				return 1;
			}
		}
		
		if (absolute(parent) == Math.min(absolute(left), absolute(right))) {
			if (absolute(left) >= absolute(right)
					&& parent > right) {
				return 2;
			} else if (absolute(right) >= absolute(left)
					&& parent > left) {
				return 1;
			}
		}
		
		return -1;
	}
}
