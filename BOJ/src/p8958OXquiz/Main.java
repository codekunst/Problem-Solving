package p8958OXquiz;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p8958OXquiz/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			int point = 0;
			int count = 0;
			for(char result : line.toCharArray()) {
				if (result == 'O') {
					point += ++count;
					continue;
				}
				count = 0;
			}
			builder.append(point).append("\n");
		}
		bw.write(builder.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
