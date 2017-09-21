/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 8.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 
// 문자열이 주어졌을 때 10개씩 끊어서 출력하는 것
package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = null;
		String str = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
			
			for(int i=0; i<str.length(); i++) {
				if(i!=0 && (i%10)==0)
					System.out.println();
				System.out.print(str.charAt(i));
			}
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}
