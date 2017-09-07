package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String nums = br.readLine();
			StringTokenizer st = new StringTokenizer(nums," ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(0>a)
				return;
			if(b>10)
				return;
			
			System.out.print(a+b);			
			
		} catch(IOException ie) {
			System.out.println("readLine() ¿¡·¯ : " + ie.getMessage());
			ie.printStackTrace();
		}
	}
}
