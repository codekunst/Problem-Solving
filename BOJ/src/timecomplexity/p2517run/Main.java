package timecomplexity.p2517run;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, leaf;
	private static int[] tree, players, sortedPlayers;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/timecomplexity/p2517run/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		while (N > 1 << leaf) {
			leaf++;
		}
		leaf = 1 << leaf;
		
		tree = new int[2 * leaf];
		
		players = new int[N];
		
		for (int i = 0; i < N; i++) {
			players[i] = Integer.parseInt(br.readLine());
		}
		
		sortedPlayers = Arrays.copyOf(players, N);
		Arrays.sort(sortedPlayers);
		
		for(Integer player : players) {
			int rank = binarySearch(player);
			update(rank);
			bw.write(String.valueOf(query(0, rank)));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int binarySearch(int target) {
		int start = 0, end = N;
		
		while (start < end) {
			int mid = (start + end) >> 1;
			
			if (sortedPlayers[mid] < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return N - start - 1;
	}
	
	private static void update(int index) {
		index += leaf;
		
		while (index > 0) {
			tree[index]++;
			index >>= 1;
		}
	}
	
	private static int query(int startIndex, int endIndex) {
		startIndex += leaf;
		endIndex += leaf;
		int rank = 0;
		
		while (startIndex <= endIndex) {
			if (startIndex % 2 == 1) {
				rank += tree[startIndex++];
			}
			
			if (endIndex % 2 == 0) {
				rank += tree[endIndex--];
			}
			
			startIndex >>= 1;
			endIndex >>= 1;
		}
		
		return rank;
	}
}
