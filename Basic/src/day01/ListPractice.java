package day01;

import java.io.*;
import java.util.*;

/* 1차원 리스트와 선형탐색
 * 어떤 광산에서는 채굴한 광물들의 등급을 상품인 1등급부터 하품인 3등급까지 나누어 등급을 매긴 후, 해당 정보를 저장해서 관리한다.
 * A 광산은 10개의 광물을 채굴하였고 각각 등급은 다음과 같다.
 * 
 * gems = [3, 3, 1, 2, 3, 2, 2, 3, 3, 1]
 * 
 * 채굴한 광물들 가운데 1등급 광물이 존재하는지 여부를 어떻게 알 수 있을까?
 * 
 * ==========================================================
 * 반복문을 활용하여 1차원 리스트를 탐색할 수 있다.
 * 조건문을 조합하여 필요한 데이터를 선별하고 처리할 수 있다.
 * ==========================================================
 */
public class ListPractice {

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		/* 방법1: Arrays.stream() */
		int[] gems = { 3, 3, 1, 2, 3, 2, 2, 3, 3, 1 };
		bw.write(String.valueOf(Arrays.stream(gems).anyMatch(gem -> gem == 1)));
		bw.newLine();
		
		/* 방법2: list.contains() */
		List<Integer> gemList = Arrays.asList(3, 3, 1, 2, 3, 2, 2, 3, 3, 1);
		bw.write(String.valueOf(gemList.contains(1)));
		bw.newLine();
		
		/* 방법3: 완전탐색 */
		int[] gemsArray = { 3, 3, 1, 2, 3, 2, 2, 3, 3, 1 };
		boolean result = false;
		for(Integer gem : gemsArray) {
			if (gem == 1) {
				result = true;
				break;
			}
		}
		bw.write(String.valueOf(result));
		bw.newLine();
		
		bw.flush();
		bw.close();
	}
}
