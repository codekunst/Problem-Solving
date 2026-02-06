package p15903card;

import java.io.*;
import java.util.*;

public class Main {
	private static int n, m;
	private static MinHeap heap;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p15903card/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		m = Integer.parseInt(tokenizer.nextToken());
		heap = new MinHeap(2 * n);
		
		tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			heap.add(Long.parseLong(tokenizer.nextToken()));
		}
		
		for (int i = 0; i < m; i++) {
			long value = heap.poll() + heap.poll();
			heap.add(value);
			heap.add(value);
		}
		
		bw.write(String.valueOf(heap.sum()));
		bw.flush();
		bw.close();
		br.close();
	}
}

class MinHeap {
	private int size;
	private long[] tree;
	
	public MinHeap(int capacity) {
		this.tree = new long[capacity];
	}
	
	public void add(long value) {
		int index = ++size;
		tree[index] = value;
		
		while (index / 2 > 0
				&& compare(tree[index / 2], tree[index]) == 1) {
			swap(index / 2, index);
			index /= 2;
		}
	}
	
	public long poll() {
		int index = 1;
		long ret = tree[index];
		tree[index] = tree[size--];
		
		while (true) {
			if (2 * index < size) {
				int compared = compare(tree[index], tree[2 * index], tree[2 * index + 1]);
				
				if (compared == 1) {
					swap(index, 2 * index);
					index *= 2;
				} else if (compared == 2) {
					swap(index, 2 * index + 1);
					index = 2 * index + 1;
				} else {
					break;
				}
			} else if (2 * index == size) {
				int compared = compare(tree[index], tree[2 * index]);
				
				if (compared == 1) {
					swap(index, 2 * index);
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
	
	public long sum() {
		long total = 0L;
		
		for (int i = 1; i <= size; i++) {
			total += tree[i];
		}
		
		return total;
	}
	
	private int compare(long parent, long child) {
		return parent > child ? 1 : -1;
	}
	
	private int compare(long parent, long left, long right) {
		if (parent > Math.min(left, right)
				&& left >= right) {
			return 2;
		}
		
		if (parent > Math.min(left, right)
				&& right > left) {
			return 1;
		}
		
		return -1;
	}
	
	private void swap(int indexA, int indexB) {
		long temp = tree[indexA];
		tree[indexA] = tree[indexB];
		tree[indexB] = temp;
	}
}
