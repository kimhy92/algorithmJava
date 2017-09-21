
package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		BufferedReader br = null;
		List<String> res = new ArrayList<String>();
		String tmpLine=null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while((tmpLine=br.readLine())!=null)
				res.add(tmpLine);
			
			for(String tmp : res)
				System.out.println(tmp);
			
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}
