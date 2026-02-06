package timecomplexity.p2096down;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] minDp, maxDp;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2096down/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		minDp = new int[3 * N];
		maxDp = new int[3 * N];
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		minDp[0] = maxDp[0] = Integer.parseInt(tokenizer.nextToken());
		minDp[1] = maxDp[1] = Integer.parseInt(tokenizer.nextToken());
		minDp[2] = maxDp[2] = Integer.parseInt(tokenizer.nextToken());
		
		for (int i = 1; i < N; i++) {
			tokenizer =  new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				int value = Integer.parseInt(tokenizer.nextToken()), index = 3 * i + j;
				
				switch (j) {
					case 0:
						minDp[index] = Math.min(minDp[index - 3], minDp[index - 2]) + value;
						maxDp[index] = Math.max(maxDp[index - 3], maxDp[index - 2]) + value;
						break;
					case 1:
						minDp[index] = Math.min(minDp[index - 2], Math.min(minDp[index - 3], minDp[index - 4])) + value;
						maxDp[index] = Math.max(maxDp[index - 2], Math.max(maxDp[index - 3], maxDp[index - 4])) + value;
						break;
					case 2:
						minDp[index] = Math.min(minDp[index - 3], minDp[index - 4]) + value;
						maxDp[index] = Math.max(maxDp[index - 3], maxDp[index - 4]) + value;
						break;
				}
			}
		}
		
		bw.write(String.valueOf(Math.max(maxDp[3 * N - 1], Math.max(maxDp[3 * N - 2], maxDp[3 * N - 3]))));
		bw.write(" ");
		bw.write(String.valueOf(Math.min(minDp[3 * N - 1], Math.min(minDp[3 * N - 2], minDp[3 * N - 3]))));
		bw.flush();
		bw.close();
		br.close();
	}
}
