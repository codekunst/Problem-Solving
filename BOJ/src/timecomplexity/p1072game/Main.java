package timecomplexity.p1072game;

import java.io.*;
import java.util.*;

public class Main {
	private static long X, Y, Z;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p1072game/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		X = Long.parseLong(tokenizer.nextToken());
		Y = Long.parseLong(tokenizer.nextToken());
		Z = 100L * Y / X;
		
		bw.write(Z >= 99L ? "-1" : String.valueOf(binarySearch()));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long binarySearch() {
		long left = 0L, right = X;
		
		while (left < right) {
			long mid = (left + right) / 2;
			long ratio = 100L * (Y + mid) / (X + mid);
			
			if (ratio <= Z) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return left;
	}
}
