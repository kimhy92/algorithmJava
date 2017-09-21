/**
 * @ClassName : Problem6
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package problem;

public class Problem6 {

	
	// 채점해서 테스트케이스 11개 중에 3개는 실패 뜨고 나머지는 통과
	public static void main(String[] args) {
		String str1[] = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String str2[] = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		
		System.out.println(solution(4,5,str1));
		System.out.println("==========================================");
//		System.out.println(solution(6,6,str2));
	}

	// 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)
	// m : 판의 높이, n : 판의 폭, board : 판의 배치 정보
	// board는 길이 n인 문자열 m개의 배열
	// board는 대문자 A~Z가 쓰인다.
	public static int solution(int m, int n, String[] board) {
		int answer=0;
		int delCnt=0;	// 매 판마다 삭제된 블록의 개수
		boolean[][] isDel = new boolean[m][n];
		
		
		while(true) {
//		for(int x=0; x<2; x++) {
			delCnt=0;	// 매 판마다 삭제된 블록의 개수는 0으로 초기화
			// 이미 삭제되었는지 체크하는 배열의 모든 원소를 false로 만든다.
			for(int i=0; i<m; i++)
				for(int j=0; j<n; j++)
					isDel[i][j]=false;
			
			// 1. 현재 board를 기준으로, 몇 개가 지워지는지 계산
			char refPoint=0;
			for(int i=0; i<m-1; i++) {
				for(int j=0; j<n-1; j++) {
					refPoint=board[i].charAt(j);
					if(refPoint!='d' && refPoint==board[i].charAt(j+1) && refPoint==board[i+1].charAt(j) && refPoint==board[i+1].charAt(j+1)) {
						// 기존에 제거되지 않은 블록이면 개수 추가
						if(!isDel[i][j])
							delCnt++;
						if(!isDel[i][j+1])
							delCnt++;
						if(!isDel[i+1][j])
							delCnt++;
						if(!isDel[i+1][j+1])
							delCnt++;
						
						// 제거 했으므로 true로 선언
						isDel[i][j]=true;
						isDel[i][j+1]=true;
						isDel[i+1][j]=true;
						isDel[i+1][j+1]=true;
					}
							
				}
			}
			
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					System.out.print(isDel[i][j]+"\t");
				}
				System.out.println();
			}
			
			// 2. 결과값에 현재 판에서 삭제된 개수를 더한다.
			answer+=delCnt;	

			// 3. 이번 board에서 하나도 지워지지 않았다면 종료
			if(delCnt==0)
				break;
			
			System.out.println(delCnt);
			
			// 4. 다음 판을 시작하기 전에 빈칸을 채운다.
			// 비어있는 블록은 d로 표시한다.
			int falseCnt=0;
			for(int i=0; i<n-1; i++) {
				for(int j=m-1; j>=0; j--) {
					falseCnt=0;
					if(isDel[j][i]) {	// 삭제된 블록인 경우,
						falseCnt++;
						
						// true가 연속으로 나오는 개수를 계산
						while(j-falseCnt>=0) {
							if(isDel[j-falseCnt][i])
								falseCnt++;
							else
								break;
						}
						
						for(int z=j; z-falseCnt>=0; z--) {	// 밑으로 내린다.
							// 문자를 내린다.
							StringBuilder sb = new StringBuilder(board[z]);
							sb.setCharAt(i, board[z-falseCnt].charAt(i));
							board[z]=sb.toString();
							
							// true도 내린다.
							isDel[z][i]=isDel[z-falseCnt][i];
						}
						
						for(int z=0; z<falseCnt; z++) {	// 밑으로 내려와서 새로 생긴 칸들 처리
							// 내려서 생긴 빈 칸을 d로 바꾼다.
							StringBuilder sb = new StringBuilder(board[z]);
							sb.setCharAt(i, 'd');
							board[z]=sb.toString();
							
							// 내려서 생긴 빈 칸을 false로 바꾼다.
							isDel[z][i]=false;
						}
					}
				}
			}
			
			for(int i=0; i<m; i++)
				System.out.println(board[i]);
		}
		
		return answer;
	}
}
