package baekjoon.algo;
// Case #1: 1 + 1 = 2


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			int lineCnt = Integer.parseInt(br.readLine());
			String nums = null;
			int a=0, b=0;
			StringTokenizer st = null;
			List<String> res = new ArrayList<String>();
			
			for(int i=0; i<lineCnt; i++) {
				nums = br.readLine();
				st = new StringTokenizer(nums);
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(0>a)
					return;
				if(b>10)
					return;
				res.add("Case #"+(i+1)+": "+a+" + "+b+" = "+(a+b));
			}
			for(String tmp : res) {
				System.out.println(tmp);
			}
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}
