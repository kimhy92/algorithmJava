/**
 * @ClassName : sc
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 29.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sc {
	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st=new StringTokenizer("");

	public static String readLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
		}
		return null;
	}

	public static String readLineReplace() {
		try {
			return br.readLine().replaceAll("\\s+", "");
		} catch (IOException e) {
		}
		return null;
	}

	public static String next() {
		while (!st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
			}
		}
		return st.nextToken();
	}

	public static long nextLong() {
		return Long.parseLong(next());
	}

	public static int nextInt() {
		return Integer.parseInt(next());
	}

	public static double nextDouble() {
		return Double.parseDouble(next());
	}

}
