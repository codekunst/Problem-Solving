package timecomplexity.binarysearch;

import java.io.*;
import java.util.*;

public class BinarySearch {
	private static int N, target, lowerBound, upperBound;
	private static boolean isExist;
	private static int[] array;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/binarysearch/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(array);
		N = array.length;
		target = Integer.parseInt(br.readLine());
		
		isExist = binarySearch(target);
		lowerBound = lowerBound(target);
		upperBound = upperBound(target);
		
		bw.write("Target: " + target);
		bw.newLine();
		bw.write(isExist ? "EXIST" : "Not EXIST");
		bw.newLine();
		bw.write("LowerBound: " + lowerBound);
		bw.newLine();
		bw.write("UpperBound: " + upperBound);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static boolean binarySearch(int target) {
		int left = 0, right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (array[mid] < target) {
				left = mid + 1;
			} else if (array[mid] > target) {
				right = mid;
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	private static int lowerBound(int target) {
		int left = 0, right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return left;
	}
	
	private static int upperBound(int target) {
		int left = 0, right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (array[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return left;
	}
}
