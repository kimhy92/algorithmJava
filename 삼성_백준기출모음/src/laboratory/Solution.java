/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 21.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package laboratory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Dir {
	int row,col;

	public Dir(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}

public class Solution {
	static int[][] map;
	static int N,M;
	// ��, ��, ��, ��
	static Dir[] dir={new Dir(-1,0),new Dir(1,0), new Dir(0,-1),new Dir(0,1)};
	static int[] selectedWall=new int[3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s[]=br.readLine().split(" ");
		N=Integer.parseInt(s[0]);
		M=Integer.parseInt(s[1]);
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			s=br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(s[j]);
			}
		}
		
		System.out.println(solve(N*M,3));
	}

	// n : ��ü ������ ����
	// r : ���� ���õ� ���� ����
	// 1~64������ ���� �� 3���� ����.
	// return�� : ���� map ���¿��� ���������� ����
	public static int solve(int n, int r) {
		int res=0;
		int cnt=0;
		int[][] tmpMap=new int[N][M];	// ������ map ���� ����
		
		if(r==0) {	// �ϳ��� ������ �Ǿ����� map�� �ٲٰ� ����
			// map�� �ӽ������Ѵ�. (������ map ����)
			cloneAry(tmpMap,map);
			
			// ���õ� 3���� ������ �ٲ۴�.
			for(int i=0; i<3; i++)
				map[selectedWall[i]/M][selectedWall[i]%M]=1;
//				System.out.print(selectedWall[i]+" ");
//			System.out.println();
			
			// moveVirus�� ȣ���Ѵ�. �������� ���̷����� �ִ� ��ġ�̴�. (���̷����� ������ �Ѵ�)
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(map[i][j]==2)
						moveVirus(new Dir(i,j));
			
			// ���������� ������ ����.
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(map[i][j]==0)
						cnt++;
			
			// ���������� ������ �ִ밪�� ���Ͽ� �� ū�� Ȯ���ϰ� �ٲ��ش�.
			res=Math.max(res, cnt);
			
			// map�� ������Ų��.
			cloneAry(map,tmpMap);
		}
		else if(n<r) {	// nCr�ε� n�� r���� ���� �� ���� �ȵ�.
			return 0;
		}
		else {
			selectedWall[r-1]=n-1;
			if(map[(n-1)/M][(n-1)%M]==0) {	// �� ĭ�� ��쿡�� ������ �ٲ� �� �ִ�.
				res=Math.max(res, solve(n-1,r-1));	// �����ϴ� ���
			}
			res=Math.max(res, solve(n-1,r));	// �������� ���� ���
		}
		
		return res;
	}
	
	// ���� �� ���¿��� dfs�� ���� ���̷����� ������ �Ѵ�.
	public static void moveVirus(Dir cur) {
		// ��,��,��,�� �� ���⿡ ���ؼ� map[][]�� ���� 0(��ĭ)�̶��, 2(���̷���)�� �ٲٰ� ���Ž�� ����
		for(int i=0; i<dir.length; i++) {
			int nextRow=cur.row+dir[i].row;
			int nextCol=cur.col+dir[i].col;
			if(checkRange(nextRow, nextCol) && map[nextRow][nextCol]==0) {
				map[nextRow][nextCol]=2;
				moveVirus(new Dir(nextRow,nextCol));
			}
		}
	}
	
	// row,col�� �迭 �ε��� ���� ���� ������ Ȯ��. �迭 ���� ���̸� return true
	public static boolean checkRange(int row, int col) {
		if(row<0||row>=N||col<0||col>=M)
			return false;
		return true;
	}
	// arr2�� arr1�� �����Ų��.
	public static void cloneAry(int[][] arr1, int[][] arr2) {
		for(int i=0; i<arr1.length; i++)
			for(int j=0; j<arr1[i].length; j++)
				arr1[i][j]=arr2[i][j];
	}
}


