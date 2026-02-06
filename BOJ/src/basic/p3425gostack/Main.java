package basic.p3425gostack;

import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static GoStack goStack = new GoStack();
	private static List<Integer> queries = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/basic/p3425gostack/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Outer: while (true) {
			queries.clear();
			
			Inner: while (true) {
				StringTokenizer tokenizer = new StringTokenizer(br.readLine());
				String query = tokenizer.nextToken();
				
				switch(query) {
				case "NUM":
					queries.add(10 + Integer.parseInt(tokenizer.nextToken()));
					break;
				case "POP":
					queries.add(1);
					break;
				case "INV":
					queries.add(2);
					break;
				case "DUP":
					queries.add(3);
					break;
				case "SWP":
					queries.add(4);
					break;
				case "ADD":
					queries.add(5);
					break;
				case "SUB":
					queries.add(6);
					break;
				case "MUL":
					queries.add(7);
					break;
				case "DIV":
					queries.add(8);
					break;
				case "MOD":
					queries.add(9);
					break;
				case "END":
					break Inner;
				case "QUIT":
					break Outer;
				}
			}
			
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				goStack.clear();
				long value = Long.parseLong(br.readLine());
				goStack.num(value);
				
				boolean flag = false;
				
				Query: for (int query : queries) {
					switch(query) {
					case 1:
						if (!goStack.pop()) {
							flag = true;
							break Query;
						}
						break;
					case 2:
						if (!goStack.inv()) {
							flag = true;
							break Query;
						}
						break;
					case 3:
						if (!goStack.dup()) {
							flag = true;
							break Query;
						}
						break;
					case 4:
						if (!goStack.swp()) {
							flag = true;
							break Query;
						}
						break;
					case 5:
						if (!goStack.add()) {
							flag = true;
							break Query;
						}
						break;
					case 6:
						if (!goStack.sub()) {
							flag = true;
							break Query;
						}
						break;
					case 7:
						if (!goStack.mul()) {
							flag = true;
							break Query;
						}
						break;
					case 8:
						if (!goStack.div()) {
							flag = true;
							break Query;
						}
						break;
					case 9:
						if (!goStack.mod()) {
							flag = true;
							break Query;
						}
						break;
					default:
						goStack.num(query - 10);
					}
				}
				
				if (flag || goStack.size != 1) {
					bw.write("ERROR\n");
					continue;
				}
				
				bw.write(String.valueOf(goStack.lastNode.value));
				bw.newLine();
			}
			
			bw.newLine();
			br.readLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class GoStack {
	public final long INF = 1_000_000_000L;
	public Node lastNode;
	public int size;
	
	public void num(long value) {
		lastNode = new Node(value, lastNode);
		size++;
	}
	
	public boolean pop() {
		if (size == 0) {
			return false;
		}
		
		lastNode = lastNode.preNode;
		size--;
		return true;
	}
	
	public boolean inv() {
		if (size == 0) {
			return false;
		}
		
		lastNode.value = -lastNode.value;
		return true;
	}
	
	public boolean dup() {
		if (size == 0) {
			return false;
		}
		
		lastNode = new Node(lastNode.value, lastNode);
		size++;
		return true;
	}
	
	public boolean swp() {
		if (size < 2) {
			return false;
		}
		
		Node preNode = lastNode.preNode;
		lastNode.preNode = preNode.preNode;
		preNode.preNode = lastNode;
		lastNode = preNode;
		return true;
	}
	
	public boolean add() {
		if (size < 2) {
			return false;
		}
		
		long right = lastNode.value;
		lastNode = lastNode.preNode;
		size--;
		long left = lastNode.value;
		long value = left + right;
		
		if (value > INF || value < -INF) {
			return false;
		}
		
		lastNode.value = value;
		return true;
	}
	
	public boolean sub() {
		if (size < 2) {
			return false;
		}
		
		long right = lastNode.value;
		lastNode = lastNode.preNode;
		size--;
		long left = lastNode.value;
		long value = left - right;
		
		if (value > INF || value < -INF) {
			return false;
		}
		
		lastNode.value = value;
		return true;
	}
	
	public boolean mul() {
		if (size < 2) {
			return false;
		}
		
		long right = lastNode.value;
		lastNode = lastNode.preNode;
		size--;
		long left = lastNode.value;
		long value = left * right;
		
		if (value > INF || value < -INF) {
			return false;
		}
		
		lastNode.value = value;
		return true;
	}
	
	public boolean div() {
		if (size < 2) {
			return false;
		}
		
		long right = lastNode.value;
		lastNode = lastNode.preNode;
		size--;
		long left = lastNode.value;
		
		if (right == 0L) {
			return false;
		}
		
		long value = left / right;
		lastNode.value = value;
		return true;
	}
	
	public boolean mod() {
		if (size < 2) {
			return false;
		}
		
		long right = lastNode.value;
		lastNode = lastNode.preNode;
		size--;
		long left = lastNode.value;
		
		if (right == 0L) {
			return false;
		}
		
		long value = left % right;
		lastNode.value = value;
		return true;
	}
	
	public void clear() {
		lastNode = null;
		size = 0;
	}
}

class Node {
	public long value;
	public Node preNode;
	
	public Node(long value, Node preNode) {
		this.value = value;
		this.preNode = preNode;
	}
}