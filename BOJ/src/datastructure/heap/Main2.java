package datastructure.heap;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int[] array;
	private static MaxHeapTree maxHeapTree;
	private static MinHeapTree minHeapTree;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/datastructure/heap/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		maxHeapTree = new MaxHeapTree(2 * array.length);
		minHeapTree = new MinHeapTree(2 * array.length);
		
		for (int element : array) {
			maxHeapTree.offer(element);
			minHeapTree.offer(element);
		}
		
		bw.write("Original: ");
		for (int element : array) {
			bw.write(String.valueOf(element));
			bw.write(" ");
		}
		bw.newLine();
		
		bw.write("MaxHeap: ");
		while (!maxHeapTree.isEmpty()) {
			bw.write(String.valueOf(maxHeapTree.poll()));
			bw.write(" ");
		}
		bw.newLine();
		
		bw.write("MinHeap: ");
		while (!minHeapTree.isEmpty()) {
			bw.write(String.valueOf(minHeapTree.poll()));
			bw.write(" ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class MaxHeapTree {
	private int size;
	private int[] tree;
	
	public MaxHeapTree(int capacity) {
		tree = new int[capacity + 1];
	}
	
	public void offer(int value) {
		int index = ++size;
		tree[index] = value;
		
		heapifyBottomUp(index);
	}
	
	public int poll() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		
		int index = 1;
		int ret = tree[index];
		tree[index] = tree[size--];
		
		heapifyTopDown(index);
		
		return ret;
	}
	
	public int peek()throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		
		return tree[1];
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void heapifyBottomUp(int index) {
		int parent = index >> 1;
		
		if (parent > 0 && tree[parent] < tree[index]) {
			swap(parent, index);
			heapifyBottomUp(parent);
		}
	}
	
	private void heapifyTopDown(int index) {
		int largest = index, left = index << 1, right = left + 1;
		
		if (left <= size && tree[left] > tree[largest]) {
			largest = left;
		}
		
		if (right <= size && tree[right] > tree[largest]) {
			largest = right;
		}
		
		if (tree[largest] > tree[index]) {
			swap(index, largest);
			heapifyTopDown(largest);
		}
	}
	
	private void swap(int indexA, int indexB) {
		int temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}

class MinHeapTree {
	private int size;
	private int[] tree;
	
	public MinHeapTree(int capacity) {
		tree = new int[capacity];
	}
	
	public void offer(int value) {
		int index = size++;
		tree[index] = value;
		
		heapifyBottomUp(index);
	}
	
	public int poll() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		
		int index = 0;
		int ret = tree[index];
		tree[index] = tree[--size];
		
		heapifyTopDown(index);
		
		return ret;
	}
	
	public int peek() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		
		return tree[0];
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private void heapifyBottomUp(int index) {
		int parent = (index - 1) >> 1;
		
		if (index > 0 && tree[parent] > tree[index]) {
			swap(parent, index);
			heapifyBottomUp(parent);
		}
	}
	
	private void heapifyTopDown(int index) {
		int smallest = index, left = (index << 1) + 1, right = (index << 1) + 2;
		
		if (left < size && tree[left] < tree[smallest]) {
			smallest = left;
		}
		
		if (right < size && tree[right] < tree[smallest]) {
			smallest = right;
		}
		
		if (tree[index] > tree[smallest]) {
			swap(index, smallest);
			heapifyTopDown(smallest);
		}
	}
	
	private void swap(int indexA, int indexB) {
		int temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}
