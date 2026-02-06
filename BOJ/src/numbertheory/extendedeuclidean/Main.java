package numbertheory.extendedeuclidean;

import java.io.*;
import java.util.*;

// Want to: a * x + b * y = gcd
// eg. gcd(84, 32)
// gcd(84, 32) = gcd(32, 20) = gcd(20, 12) = gcd(12, 8) = gcd(8, 4) = gcd(4, 0) = 4
// 84 = 32 * 2 + 20
// 32 = 20 * 1 + 12
// 20 = 12 * 1 + 8
// 12 = 8 * 1 + 4
// 8 = 4 * 2 + 0 을 기반으로 역으로 올라가보면...
//
// 4 = 4 * 1 + 0 ==> x0 = 1, y0 = 0
// 12 = 8 * 1 + 4 ==> 4 = 12 - 8 * 1 ==> x1 = 1, y1 = -1
// 4 = 12 - (20 - 12 * 1) = 20 * (-1) + 12 * 2 ==> x2 = -1, y2 = 2
// 4 = 20 * (-1) + (32 - 20 * 1) * 2 = 32 * 2 + 20 * (-3) ==> x3 = 2, y3 = -3
// 4 = 32 * 2 + (84 - 32 * 2) * (-3) = 84 * (-3) + 32 * 8 ==> x4 = -3, y4 = 8
// 따라서, 84 * x + 32 * y = gcd의 디오판토스 해는 (-3, 8), gcd는 4
//
// (xn, yn)과 (xn-1, yn-1) 사이의 점화식
// an * xn + bn * yn = gcd
// an-1 * xn-1 + bn-1 * yn-1 = gcd
// an-1 = bn
// bn-1 = an % bn = an - an // bn * bn  이 때
// an * xn + bn * yn = bn * xn-1 + (an - an // bn * bn) * yn-1
// an * (xn - yn-1) + bn (yn - xn-1 + an // bn * yn-1) = 0
// xn = yn-1, yn = xn-1 - an // bn * yn-1 이 성립한다.
public class Main {
	private static int N = 84, M = 32, X, Y, GCD;
	
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
//		extendedEuclideanRecursive(N, M);
		extendedEuclideanLoop(N, M);
		
		bw.write(N + " * " + X + " + " + M + " * " + Y + " = " + GCD);
		bw.flush();
		bw.close();
	}
	
	private static void extendedEuclideanRecursive(int a, int b) {
		if (b == 0) {
			GCD = a;
			X = 1;
			Y = 0;
			return;
		}
		
		extendedEuclideanRecursive(b, a % b);
		
		int nowX = X;
		X = Y;
		Y = nowX - a / b * Y;
	}
	
	private static void extendedEuclideanLoop(int a, int b) {
		int x0 = 1, y0 = 0;
		int x1 = 0, y1 = 1;
		
		while (b != 0) {
			int q = a / b;
			int r = a % b;
			
			int x2 = x0 - q * x1;
			int y2 = y0 - q * y1;
			
			a = b;
			b = r;
			x0 = x1;
			y0 = y1;
			x1 = x2;
			y1 = y2;
		}
		
		GCD = a;
		X = x0;
		Y = y0;
	}
}
