package p1302bestseller;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p1302bestseller/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Map<String, Integer> booksToCount = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String book = br.readLine();
			booksToCount.put(book, booksToCount.getOrDefault(book, 0) + 1);
		}
		
		int max = -1;
		
		List<String> books = new ArrayList<>(booksToCount.keySet());
		
		Collections.sort(books, (o1, o2) -> booksToCount.get(o1) != booksToCount.get(o2) ? booksToCount.get(o2) - booksToCount.get(o1) : o1.compareTo(o2));
		
		bw.write(books.get(0));
		bw.flush();
		bw.close();
		br.close();
	}
}
