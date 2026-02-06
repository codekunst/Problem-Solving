package basic.p1713candidates;

import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static List<Integer> candidates = new ArrayList<>();
	private static Map<Integer, int[]> candidateToInfo = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/basic/p1713candidates/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			int candidate = Integer.parseInt(tokenizer.nextToken());
			
			if (candidateToInfo.containsKey(candidate)) {
				candidateToInfo.get(candidate)[0]++;
				continue;
			}
			
			if (candidateToInfo.size() < N && !candidateToInfo.containsKey(candidate)) {
				candidates.add(candidate);
				candidateToInfo.put(candidate, new int[] {1, i});
				continue;
			}
			
			Collections.sort(candidates, (o1, o2) -> 
				candidateToInfo.get(o1)[0] == candidateToInfo.get(o2)[0] ? candidateToInfo.get(o1)[1] - candidateToInfo.get(o2)[1] : candidateToInfo.get(o1)[0] - candidateToInfo.get(o2)[0]
			);
			
			candidateToInfo.remove(candidates.get(0));
			candidates.remove(0);
			candidates.add(candidate);
			candidateToInfo.put(candidate, new int[] {1, i});
		}
		
		Collections.sort(candidates);
		
		for (Integer candidate : candidates) {
			bw.write(String.valueOf(candidate));
			bw.write(" ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
