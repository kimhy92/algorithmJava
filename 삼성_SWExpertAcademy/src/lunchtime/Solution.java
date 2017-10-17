/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package lunchtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tCnt=Integer.parseInt(br.readLine());
		int res=0;
		int n=0;
		int[][] map;
		for(int x=1; x<=tCnt; x++) {
			res=0;
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			for(int i=0;i<n;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++)
					map[i][j]=Integer.parseInt(st.nextToken());
			}
			
			
			
			System.out.println("#"+x+" "+res);
		}
	}
}









