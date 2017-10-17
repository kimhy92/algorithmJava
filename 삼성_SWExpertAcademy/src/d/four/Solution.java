/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package d.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// �̷�1(d4)
// ���������� ����Ͽ� �������� ������ �� �ִ��� üũ�ϴ� ����
public class Solution {
	static int N=16;
	static int[][] map=new int[N][N];
	static int[][] visited=new int[N][N];
	static int sr, sc;
	static int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};	//��,��,��,��
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String tmp=null;
		// (0,0)~(15,15) ���� ����
		for(int tc=1; tc<=10; tc++) {
			int tcNo=Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++) {
				tmp=br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j]=tmp.charAt(j)-48;
					visited[i][j]=0;
					if(map[i][j]==2) {	// �������� ���,
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
		if(map[row][col]==3) {	// �������̸� res=1
			res=1;
			return;
		}

		visited[row][col]=1;
		
		for(int i=0; i<dir.length; i++) {
			if(visited[row+dir[i][0]][col+dir[i][1]]==0 && map[row+dir[i][0]][col+dir[i][1]]!=1) {	// ��,��,��,�� �� �ϳ��� �� �� ���� ��쿡�� �� �� �ֵ��� üũ
				solve(row+dir[i][0],col+dir[i][1]);
			}
		}
	}
}
