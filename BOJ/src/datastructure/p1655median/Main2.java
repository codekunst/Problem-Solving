package datastructure.p1655median;

import java.io.*;

public class Main2 {
	private static int N;
	private static MaxHeap maxHeap;
	private static MinHeap minHeap;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/datastructure/p1655median/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		maxHeap = new MaxHeap(N);
		minHeap = new MinHeap(N);
		
		maxHeap.add(Integer.parseInt(br.readLine()));
		bw.write(String.valueOf(maxHeap.peek()));
		bw.newLine();
		
		for (int i = 0; i < N - 1; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (maxHeap.peek() <= value) {
				minHeap.add(value);
			} else {
				maxHeap.add(value);
			}
			
			while (maxHeap.size() < minHeap.size()) {
				maxHeap.add(minHeap.poll());
			}
			
			while (maxHeap.size() > minHeap.size() + 1) {
				minHeap.add(maxHeap.poll());
			}
			
			bw.write(String.valueOf(maxHeap.peek()));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class MaxHeap {
	private int size;
	private int[] tree;
	
	public MaxHeap(int capacity) {
		this.tree = new int[capacity + 1];
	}
	
	public void add(int value) {
		int index = ++size, parent = 0;
		tree[index] = value;
		
		while ((parent = index >> 1) > 0) {
			if (tree[parent] >= tree[index]) {
				break;
			}
			
			swap(parent, index);
			index = parent;
		}
	}
	
	public int poll() {
		int index = 1;
		int ret = tree[index];
		tree[index] = tree[size--];
		
		while (index << 1 <= size) {
			int left = index << 1, right = left + 1, bigger = left;
			
			if (right <= size && tree[right] > tree[left]) {
				bigger = right;
			}
			
			if (tree[index] >= tree[bigger]) {
				break;
			}
			
			swap(index, bigger);
			index = bigger;
		}
		
		return ret;
	}
	
	public int peek() {
		return tree[1];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void swap(int indexA, int indexB) {
		int temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}

class MinHeap {
	private int size;
	private int[] tree;
	
	public MinHeap(int capacity) {
		tree = new int[capacity + 1];
	}
	
	public void add(int value) {
		int index = ++size, parent = 0;
		tree[index] = value;
		
		while ((parent = index >> 1) > 0) {
			if (tree[parent] <= tree[index]) {
				break;
			}
			
			swap(parent, index);
			index = parent;
		}
	}
	
	public int poll() {
		int index = 1;
		int ret = tree[index];
		tree[index] = tree[size--];
		
		while (index << 1 <= size) {
			int left = index << 1, right = left + 1, smaller = left;
			
			if (right <= size && tree[right] < tree[left]) {
				smaller = right;
			}
			
			if (tree[index] <= tree[smaller]) {
				break;
			}
			
			swap(index, smaller);
			index = smaller;
		}
		
		return ret;
	}
	
	public int peek() {
		return tree[1];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void swap(int indexA, int indexB) {
		int temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}