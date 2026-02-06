package timecomplexity.p11003minimum;

import java.io.*;
import java.util.*;

public class Main2 {
	private static int N, L;
	private static MinHeap minHeap;
	private static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p11003minimum/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		L = Integer.parseInt(tokenizer.nextToken());
		
		minHeap = new MinHeap(N);
		
		tokenizer = new StringTokenizer(br.readLine());
		
		minHeap.add(Integer.parseInt(tokenizer.nextToken()), 0);
		result.append(minHeap.peek()[0]).append(" ");
		
		for (int i = 1; i < N; i++) {
			int value = Integer.parseInt(tokenizer.nextToken());
			
			if (value <= minHeap.peek()[0]) {
				minHeap.clear();
			}
			
			minHeap.add(value, i);
			
			while (minHeap.peek()[1] < i - L + 1) {
				minHeap.poll();
			}
			
			result.append(minHeap.peek()[0]).append(" ");
		}
		
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}

class MinHeap {
	private int size;
	private int[][] tree;
	
	public MinHeap(int capacity) {
		tree = new int[capacity + 1][2];
	}
	
	public void add(int value, int time) {
		int index = ++size, parent = 0;
		tree[index][0] = value;
		tree[index][1] = time;
		
		while ((parent = index >> 1) > 0) {
			if (tree[parent][0] > tree[index][0]) {
				swap(parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}
	
	public int[] poll() {
		int index = 1;
		int[] rootValue = tree[index];
		tree[index][0] = tree[size][0];
		tree[index][1] = tree[size--][1];
		
		while (index << 1 <= size) {
			int left = index << 1, right = left + 1, smaller = left;
			
			if (right <= size && tree[right][0] < tree[left][0]) {
				smaller = right;
			}
			
			if (tree[index][0] <= tree[smaller][0]) {
				break;
			}
			
			swap(index, smaller);
			index = smaller;
		}
		
		return rootValue;
	}
	
	public int[] peek() {
		return tree[1];
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		this.size = 0;
	}
	
	private void swap(int indexA, int indexB) {
		int tempValue = tree[indexA][0], tempTime = tree[indexA][1];
		tree[indexA][0] = tree[indexB][0];
		tree[indexA][1] = tree[indexB][1];
		tree[indexB][0] = tempValue;
		tree[indexB][1] = tempTime;
	}
}
