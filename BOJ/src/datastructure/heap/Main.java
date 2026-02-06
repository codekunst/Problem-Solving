package datastructure.heap;

import java.io.*;
import java.util.*;

public class Main {
	private static int[] array;
	private static MaxHeap maxHeap;
	private static MinHeap minHeap;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/datastructure/heap/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		maxHeap = new MaxHeap(2 * array.length);
		minHeap = new MinHeap(2 * array.length);
		
		for(int element : array) {
			maxHeap.add(element);
			minHeap.add(element);
		}
		
		bw.write("[MaxHeap]: ");
		while (!maxHeap.isEmpty()) {
			bw.write(String.valueOf(maxHeap.poll()));
			bw.write(" ");
		}
		bw.newLine();
		
		bw.write("[MinHeap]: ");
		while (!minHeap.isEmpty()) {
			bw.write(String.valueOf(minHeap.poll()));
			bw.write(" ");
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
		int index = ++size;
		tree[index] = value;
		
		while(index >> 1 > 0) {
			int parent = index >> 1, child = index;
			
			if (tree[parent] < tree[child]) {
				swap(parent, child);
				index = parent;
			} else {
				break;
			}
		}
	}
	
	public int poll() {
		int index = 1;
		int ret = tree[index];
		tree[index] = tree[size--];
		
		while (index << 1 <= size) {
			int parent = index, left = index << 1, right = left + 1, bigger = left;
			
			if (right <= size && tree[right] > tree[left]) {
				bigger = right;
			}
			
			if (tree[bigger] > tree[parent]) {
				swap(parent, bigger);
				index = bigger;
			} else {
				break;
			}
		}
		
		return ret;
	}
	
	public int peek() {
		return tree[1];
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void swap(int indexA, int indexB) {
		int temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}

class MinHeap {
	private int size;
	private int[] tree;
	
	public MinHeap (int capacity) {
		this.tree = new int[capacity + 1];
	}
	
	public void add(int value) {
		int index = ++size;
		tree[index] = value;
		
		while (index >> 1 > 0) {
			int parent = index >> 1, child = index;
			
			if (tree[parent] > tree[child]) {
				swap(parent, child);
				index = parent;
			} else {
				break;
			}
		}
	}
	
	public int poll() {
		int index = 1;
		int ret = tree[index];
		tree[index] = tree[size--];
		
		while (index << 1 <= size) {
			int parent = index, left = index << 1, right = left + 1, smaller = left;
			
			if (right <= size && tree[right] < tree[left]) {
				smaller = right;
			}
			
			if (tree[smaller] < tree[parent]) {
				swap(parent, smaller);
				index = smaller;
			} else {
				break;
			}
		}
		
		return ret;
	}
	
	public int peek() {
		return tree[1];
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void swap(int indexA, int indexB) {
		int temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}
