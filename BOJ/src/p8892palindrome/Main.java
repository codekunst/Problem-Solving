package p8892palindrome;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/p8892palindrome/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int k = Integer.parseInt(br.readLine());
			String[] words = new String[k];
			for (int j = 0; j < k; j++) {
				words[j] = br.readLine();
			}
			
			String palindrome = null;
			boolean flag = false;
			
			Outer: for(int m = 0; m < k; m++) {
				for (int n = m + 1; n < k; n++) {
					String wordA = words[m].concat(words[n]);
					String wordB = words[n].concat(words[m]);
					
					if (isPalindrome(wordA)) {
						palindrome = wordA;
						flag = true;
						break Outer;
					}
					
					if (isPalindrome(wordB)) {
						palindrome = wordB;
						flag = true;
						break Outer;
					}
				}
			}
			
			if (!flag) {
				bw.write("0\n");
				continue;
			}
			bw.write(palindrome);
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static boolean isPalindrome(String string) {
		boolean flag = true;
		
		int left = 0;
		int right = string.length() - 1;
		
		while(left < right) {
			if (string.charAt(left) != string.charAt(right)) {
				flag = false;
				break;
			}
			left++;
			right--;
		}
		
		return flag;
	}
}
