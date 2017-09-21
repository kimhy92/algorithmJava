package baekjoon.algo;

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

			String nums = null;
			int a=0, b=0;
			StringTokenizer st = null;
			List<Integer> res = new ArrayList<Integer>();
			while((nums=br.readLine())!=null) {
				st = new StringTokenizer(nums," ");
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(0>a)
					return;
				if(b>10)
					return;
				
				res.add(a+b);
			}
			for(int tmp : res) {
				System.out.println(tmp);
			}
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}
