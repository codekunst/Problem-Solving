package basic.sorting;

import java.io.*;
import java.util.*;

public class Main {
	private static int[] array, sortedArray;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/sorting/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		sortedArray = new int[array.length];
		
		System.arraycopy(array, 0, sortedArray, 0, array.length);
		
//		bubbleSort(sortedArray);
//		selectionSort(sortedArray);
//		insertionSort(sortedArray);
//		mergeSort(sortedArray);
//		quickSort(sortedArray, 0, sortedArray.length - 1);
		heapSort(sortedArray);
		
		bw.write("Original: ");
		bw.write(Arrays.toString(array));
		bw.newLine();
		bw.write("Sorted: ");
		bw.write(Arrays.toString(sortedArray));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			boolean flag = false;
			
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
					flag = true;
				}
			}
			
			if (!flag) {
				break;
			}
		}
	}
	
	private static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			
			if (minIndex != i) {
				swap(array, minIndex, i);
			}
		}
	}
	
	private static void insertionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int key = array[i + 1];
			int j = i;
			
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j--];
			}
			
			array[j + 1] = key;
		}
	}
	
	private static void mergeSort(int[] array) {
		if (array.length <= 1) {
			return;
		}
		
		int mid = array.length / 2;
		int[] left = new int[mid];
		int[] right = new int[array.length - mid];
		
		System.arraycopy(array, 0, left, 0, mid);
		System.arraycopy(array, mid, right, 0, array.length - mid);
		
		mergeSort(left);
		mergeSort(right);
		
		merge(array, left, right);
	}
	
	private static void merge(int[] array, int[] left, int[] right) {
		int i = 0, j = 0, k = 0;
		
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				array[k++] = left[i++];
			} else {
				array[k++] = right[j++];
			}
		}
		
		while (i < left.length) {
			array[k++] = left[i++];
		}
		
		while (j < right.length) {
			array[k++] = right[j++];
		}
	}
	
	private static void quickSort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		
		int pivotIndex = partition(array, left, right);
		quickSort(array, left, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, right);
	}
	
	private static int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int i = left - 1;
		
		for (int j = left; j < right; j++) {
			if (array[j] <= pivot) {
				swap(array, ++i, j);
			}
		}
		
		swap(array, i + 1, right);
		
		return i + 1;
	}
	
	private static void heapSort(int[] array) {
		int n = array.length;
		
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(array, n, i);
		}
		
		for (int i = n - 1; i > 0; i--) {
			swap(array, i, 0);
			heapify(array, i, 0);
		}
	}
	
	private static void heapify(int[] array, int heapSize, int index) {
		int largest = index, left = 2 * index + 1, right = 2 * index + 2;
		
		if (left < heapSize && array[largest] < array[left]) {
			largest = left;
		}
		
		if (right < heapSize && array[largest] < array[right]) {
			largest = right;
		}
		
		if (largest != index) {
			swap(array, index, largest);
			heapify(array, heapSize, largest);
		}
	}
	
	private static void swap(int[] array, int indexA, int indexB) {
		int temp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = temp;
	}
}
