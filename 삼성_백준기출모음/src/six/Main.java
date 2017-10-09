/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 9.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
결과 : 통과
내가 푼 풀이 :
1. 우선 기준점을 정의 : 각 도형의 가장 왼쪽 열 중 가장 상단의 사각형
2. 가능한 모든 도형에 대해서 기준점으로부터 다른 사각형들이 몇행몇열 떨어져있는지 figures 배열에 선언
	ex) figures[0][0]의 값은 (0,1), (0,2), (0,3)이다. 이는  ㅁㅁㅁㅁ  이렇게 4개의 사각형을 붙인 테트로미노(도형)이다.
		이 때, 가장 왼쪽의 사각형이 기준점이 되며 나머지 사각형들은 기준점으로 각각 0행1열, 0행2열, 0행3열만큼 떨어져있는 것을 의미한다.
3. main에서는 우선 input을 받는다.
4. solve() : 우선 paper의 모든 좌표는 기준점이 된다. 그리고 그 기준점으로부터 가능한 모든 도형에 대해서 paper[][]에 적힌 값들의 합을 구한다.
			단, 도형이 paper[][] 밖으로 나가는 경우는 체크해준다. 만역 도형의 사각형이 모두 paper 안에 있다면 합을 구하고,
			기존의 최대값과 비교한다.
5. paper의 모든 점을 기준점으로 삼아서 비교하기 때문에, O(n)=n*m*19=n^2*19가 된다. (가능한 도형의 개수가 19개이고, 모든좌표는n제곱)
=============== 해설 찾아본 결과,
paper의 모든 사각형을 기준점으로 한 것은 같으나, 나는 가능한 모든 도형을 직접 체크한거고
이걸 직접하지않고 dfs로 함
그리고 ㅗ 도형은 dfs로 안되서 따로 체크하였다고 함

 */
public class Main {
	static int n,m;
	static int[][] paper;
	static int max=-1;		// paper의 모든 값은 1이상 1000이하이므로 max의 초기값은 -1
	// 가능한 모든 도형의 좌표. 기준점은 제외. 기준점은 가장 왼쪽 열 중 가장 상단의 정사각형
	static Figure[][] figures=new Figure[][]{
		{new Figure(0,1),new Figure(0,2),new Figure(0,3)},
		{new Figure(1,0),new Figure(2,0),new Figure(3,0)},
		{new Figure(1,0),new Figure(0,1),new Figure(1,1)},
		{new Figure(1,0),new Figure(2,0),new Figure(2,1)},
		{new Figure(1,0),new Figure(0,1),new Figure(0,2)},
		{new Figure(0,1),new Figure(1,1),new Figure(2,1)},
		{new Figure(0,1),new Figure(0,2),new Figure(-1,2)},
		{new Figure(0,1),new Figure(-1,1),new Figure(-2,1)},
		{new Figure(0,1),new Figure(0,2),new Figure(1,2)},
		{new Figure(0,1),new Figure(1,0),new Figure(2,0)},
		{new Figure(1,0),new Figure(1,1),new Figure(1,2)},
		{new Figure(0,1),new Figure(-1,1),new Figure(-1,2)},
		{new Figure(1,0),new Figure(1,1),new Figure(2,1)},
		{new Figure(0,1),new Figure(1,1),new Figure(1,2)},
		{new Figure(1,0),new Figure(0,1),new Figure(-1,1)},
		{new Figure(0,1),new Figure(0,2),new Figure(-1,1)},
		{new Figure(1,0),new Figure(2,0),new Figure(1,1)},
		{new Figure(0,1),new Figure(0,2),new Figure(1,1)},
		{new Figure(-1,1),new Figure(0,1),new Figure(1,1)}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		paper=new int[n][m];
		for(int i=0; i<n; i++) {
			StringTokenizer st2=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++)
				paper[i][j]=Integer.parseInt(st2.nextToken());
		}
		
		solve();
	}
	
	public static void solve() {
		// O(n) = n^2*19 ? (가능한 도형개수 19개)
		// paper의 각 좌표를 기준으로 가능한 모든 도형의 sum을 구한다.
		// 그 중 max값을 구한다.
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				for(int k=0; k<figures.length; k++) {
					int figureSum=sumFigureValue(new Figure(i,j),figures[k]);
					if(figureSum==-1)
						continue;
					else
						max=Math.max(max, figureSum);
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static int sumFigureValue(Figure refPoint, Figure[] figure) {
		int sum=paper[refPoint.row][refPoint.col];
		for(int i=0; i<figure.length; i++) {
			// 도형의 모든 사각형이 paper 안에 들어있는지 체크하고, 아닌 경우 return -1
			// 0행 <= row <= n-1행, 0열 <= col <= m-1열
			if(refPoint.row+figure[i].row<0 || refPoint.row+figure[i].row>=n || refPoint.col+figure[i].col<0 || refPoint.col+figure[i].col>=m)
				return -1;
			else
				sum+=paper[refPoint.row+figure[i].row][refPoint.col+figure[i].col];
		}
		
		return sum;
	}
	
	private static class Figure {
		int row, col;

		public Figure(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}












































