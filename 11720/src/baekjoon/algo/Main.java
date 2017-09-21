
package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = null;
		int sum=0;
		int numCnt=0;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			numCnt=Integer.parseInt(br.readLine());
			for(int i=0;i<numCnt; i++){ 
				sum+=(br.read()-48);
			}
			System.out.println(sum);
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}
