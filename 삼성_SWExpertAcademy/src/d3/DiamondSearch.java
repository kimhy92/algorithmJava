/**
 * @ClassName : DiamondSearch
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 19.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2805. 농작물 수확 문제(d3)
// 홈 방범 문제의 마름모 탐색법 참조하였음
public class DiamondSearch {
	static int N;
	static int[][] farm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc; i++) {
			N=Integer.parseInt(br.readLine());
			farm=new int[N][N];
			
			for(int j=0; j<N; j++) {
				String[] s=br.readLine().split("");
				for(int k=0; k<N; k++)
					farm[j][k]=Integer.parseInt(s[k]);
			}
			
			System.out.println("#"+i+" "+solve());
		}
	}
	
	/*
	7*7 의 배열에서 아래와 같은 방법으로 마름모를 탐색하는 경우, 탐색 순서는 이와 같다. (숫자가 탐색 순서)
					1
				3	4	5
			9	10	11	12	13
		19	20	21	22	23	24	25
			14	15	16	17	18
				6	7	8
					2
	탐색 순서 :
		마름모 위쪽 영역과 아래쪽 영역을 한 번의 for문에서 같이 검사.
		위쪽 영역과 아래쪽 영역 탐색이 끝나면 마름모의 가운데 줄 검사
	필요정보 :
	1. 마름모의 중간점에 대한 row index, col index.
	2. 마름모 한 변의 길이
	이 2개만 있으면 가능
	연산을 위해 추가로 구했던 값
	1. upCheckRow		: 마름모 위쪽 영역의 검사할 row
	2. downCheckRow		: 마름모 아래쪽 영역의 검사할 row
	3. loopCnt			: 각 행의 검사해야 할 열의 개수 (이는 위쪽 영역, 아래쪽 영역이 같다. 위의 탐색 순서 참조)
	4. startCol			: 각 행의 가장 왼쪽 col의 index (위쪽 영역, 아래쪽 영역이 같다. 위의 탐색 순서 참조)
	5. checkCol			: 현재 검사하는 col의 index. 가장왼쪽col의index+j값으로 구한다. 0<=j<loopCnt.
						  loopCnt가 검사해야 할 열의 개수를 의미하기 때문에 그 회수만큼 반복하면서 j값을 더한다.
	 */
	public static int solve() {
		int sum=0;
		int k=N/2+1;			// 마름모 한 변의 길이
		int row=N/2, col=N/2;	// 마름모의 중앙에 있는 칸의 행,열
		
		// 마름모의 위,아래 영역 검사
		for(int i=1; i<=k-1; i++) {	// 마름모 한 변의 길이-1 만큼 반복. 매번 위/아래영역 검사
			int upCheckRow=row-(k-i);
			int downCheckRow=row+(k-i);
			
			int loopCnt=i*2-1;			// 각 행의 검사해야 할 열의 개수
			int startCol=col-loopCnt/2;	// 첫 번째 행의 시작 col index는 (중간점의col-0), 2번째 행의 시작 col index는 (중간점의col-1) , ....
			
			// 각 행의 검사해야 할 열의 개수는 i*2-1 이다. (1행:1번, 2행:3번, 3행:5번, ...)
			for(int j=0; j<loopCnt; j++) {
				int checkCol=startCol+j;
				if(checkRange(upCheckRow,checkCol))
					sum+=farm[upCheckRow][checkCol];
				if(checkRange(downCheckRow,checkCol))
					sum+=farm[downCheckRow][checkCol];
			}
		}
		
		// 마름모의 가운데 줄 검사. 한 줄만 검사하니까 모든 열에 대해 검사하면 됨 (중첩for문 사용하지 않아도 됨)
		for(int i=col-(k-1); i<=col+(k-1); i++)
			sum+=farm[row][i];	// 행은 중간 행으로 고정이고 열의 값만 바뀜
		
		return sum;
	}
	
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=N)
			return false;
		return true;
	}
}













