package day04;

import java.util.*;
import java.util.stream.*;

public class PrefixSum {

	public static void main(String[] args) {
		int[] nums = { 3, 5, 1, 4, 2 };
		
		int[] prefix = new int[nums.length + 1];
		
		IntStream.range(0, nums.length).forEach(i -> prefix[i + 1] = prefix[i] + nums[i]);
		
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(prefix));
	}
}
