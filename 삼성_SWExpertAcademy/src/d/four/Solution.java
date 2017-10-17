/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package d.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 미로1(d4)
// 시작점에서 출발하여 도착점에 도달할 수 있는지 체크하는 문제
public class Solution {
	static int N=16;
	static int[][] map=new int[N][N];
	static int[][] visited=new int[N][N];
	static int sr, sc;
	static int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};	//상,하,좌,우
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String tmp=null;
		// (0,0)~(15,15) 까지 가능
		for(int tc=1; tc<=10; tc++) {
			int tcNo=Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++) {
				tmp=br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j]=tmp.charAt(j)-48;
					visited[i][j]=0;
					if(map[i][j]==2) {	// 시작점인 경우,
						sr=i;
						sc=j;
					}
				}
			}
			res=0;
			solve(sr,sc);
			System.out.println("#"+tcNo+" "+res);
		}
	}
	
	static void solve(int row, int col) {
		if(map[row][col]==3) {	// 도착점이면 res=1
			res=1;
			return;
		}

		visited[row][col]=1;
		
		for(int i=0; i<dir.length; i++) {
			if(visited[row+dir[i][0]][col+dir[i][1]]==0 && map[row+dir[i][0]][col+dir[i][1]]!=1) {	// 상,하,좌,우 중 하나로 갈 때 길인 경우에만 갈 수 있도록 체크
				solve(row+dir[i][0],col+dir[i][1]);
			}
		}
	}
}
