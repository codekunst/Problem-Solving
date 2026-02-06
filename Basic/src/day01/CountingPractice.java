package day01;

import java.io.*;
import java.util.*;

/* 집계 알고리즘 - 구조화
 * 등급별로 광물이 각각 몇 개씩 있는지 어떻게 기록할 수 있을까?
 * 
 * =======================================================================
 * 데이터를 등장 빈도를 기준으로 재구조화한다.
 * 주어진 데이터의 형태, 문제의 조건을 고려하여 알맞은 자료구조를 선택한다.
 * =======================================================================
 */
public class CountingPractice {

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		/* 방법1: 리스트를 통한 구조화 */
		int[] gems = { 3, 3, 1, 2, 3, 2, 2, 3, 3, 1 };
		int[] counts = new int[3];

		Arrays.stream(gems).forEach(gem -> counts[gem - 1]++);

		for (int i = 0; i < counts.length; i++) {
			bw.write(i + 1 + "등급: " + counts[i] + "개 / ");
		}
		bw.newLine();

		/* 방법2: Map을 통한 구조화 */
		List<Integer> gemList = Arrays.asList(3, 3, 1, 2, 3, 2, 2, 3, 3, 1);
		Map<Integer, Integer> gradeToCount = new HashMap<>();
		gemList.stream().forEach(gem -> gradeToCount.put(gem, gradeToCount.getOrDefault(gem, 0) + 1));
		bw.write(String.valueOf(gradeToCount));
		bw.newLine();

		bw.flush();
		bw.close();
	}
}
