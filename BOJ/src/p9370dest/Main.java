package p9370dest;

import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 50_000 * 1_000 + 1;
	private static int T, n, m, t, s, g, h, x;
	private static List<int[]>[] adList, backList;
	private static int[] distance;
	private static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
	private static List<Integer> result = new ArrayList<>();
	private static Stack<int[]> stack = new Stack<>();
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p9370dest/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			n = Integer.parseInt(tokenizer.nextToken());
			m = Integer.parseInt(tokenizer.nextToken());
			t = Integer.parseInt(tokenizer.nextToken());
			
			tokenizer = new StringTokenizer(br.readLine());
			s = Integer.parseInt(tokenizer.nextToken());
			g = Integer.parseInt(tokenizer.nextToken());
			h = Integer.parseInt(tokenizer.nextToken());
			
			adList = new List[n + 1];
			backList = new List[n + 1];
			for (int i = 0; i < n + 1; i++) {
				adList[i] = new ArrayList<>();
				backList[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < m; i++) {
				tokenizer = new StringTokenizer(br.readLine());
				int vertexA = Integer.parseInt(tokenizer.nextToken());
				int vertexB = Integer.parseInt(tokenizer.nextToken());
				int value = Integer.parseInt(tokenizer.nextToken());
				adList[vertexA].add(new int[] {vertexB, value});
				adList[vertexB].add(new int[] {vertexA, value});
			}
			
			distance = new int[n + 1];
			Arrays.fill(distance, INF);
			distance[s] = 0;
			queue.clear();
			queue.add(new int[] {s, 0});
			visited = new boolean[n + 1];
			
			dijkstra();
			
			Arrays.fill(visited, false);
			result.clear();
			for (int i = 0; i < t; i++) {
				x = Integer.parseInt(br.readLine());
				stack.clear();
				stack.add(new int[] {x, 0, 0});
				visited[x] = true;
				dfs();
			}
			
			Collections.sort(result);
			for (Integer element : result) {
				bw.write(String.valueOf(element));
				bw.write(" ");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dijkstra() {
		while (!queue.isEmpty()) {
			int[] nowNode = queue.poll();
			
			if (visited[nowNode[0]]) {
				continue;
			}
			
			visited[nowNode[0]] = true;
			
			for (int[] nextNode : adList[nowNode[0]]) {
				if (distance[nextNode[0]] >= distance[nowNode[0]] + nextNode[1]) {
					distance[nextNode[0]] = distance[nowNode[0]] + nextNode[1];
					queue.add(new int[] {nextNode[0], distance[nextNode[0]]});
					backList[nextNode[0]].add(new int[] {nowNode[0], nextNode[1]});
				}
			}
		}
	}

	private static void dfs() {

	    while (!stack.isEmpty()) {
	        int[] now = stack.pop();  // 현재 상태를 꺼냄
	        
	        // 시작점 도달
	        if (now[0] == s) {
	            if (now[2] == 1 && now[1] == distance[x]) {
	                result.add(x);
	            }
	            visited[now[0]] = false;
	            continue;
	        }
	        
	        // 현재 노드에서 더 탐색할 간선이 있는지 체크
	        boolean hasUnvisited = false;
	        for (int[] next : backList[now[0]]) {
	            if (!visited[next[0]]) {
	                hasUnvisited = true;
	                break;
	            }
	        }
	        
	        // 더 탐색할 간선이 없으면 방문 해제
	        if (!hasUnvisited) {
	            visited[now[0]] = false;
	            continue;
	        }
	        
	        // 현재 상태를 다시 스택에 넣음 (백트래킹을 위해)
	        stack.push(now);
	        
	        // 다음 노드 탐색
	        for (int[] next : backList[now[0]]) {
	            if (!visited[next[0]]) {
	                visited[next[0]] = true;
	                
	                if ((now[0] == g && next[0] == h) || 
	                    (now[0] == h && next[0] == g)) {
	                    stack.push(new int[]{next[0], now[1] + next[1], 1});
	                } else {
	                    stack.push(new int[]{next[0], now[1] + next[1], now[2]});
	                }
	                break;  // 한 번에 하나의 경로만 탐색
	            }
	        }
	    }
	}
}
