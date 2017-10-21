package fail.mesaengmool;
///**
// * @ClassName : Solution
// * @Author : Kim HyunYong
// * @Date : 2017. 10. 20.
// * @Description : -
// * @Version : 1.0
// * @History : 수정일, 수정자, 수정내용
// */ 
//
//package fail.mesaengmool;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//class Dir {	// 방향에 따라 움직여야 할 행,열의 값을 저장하기 위한 클래스
//	int row,col;
//
//	public Dir(int row, int col) {
//		super();
//		this.row = row;
//		this.col = col;
//	}
//}
//
//class Map {		// 미생물 군집합을 의미하는 클래스
//	int cnt, nextDir, maxCnt;
//	/*
//	cnt : 현재 미생물 군집합에 속해있는 미생물의 수
//	nextDir : 다음에 움직일 방향. 1:상,2:하,3:좌,4:우
//	maxCnt : 여러 군집합이 겹쳤을 때, 가장 미생물의 수가 많은 군집합의 미생물 수
//	 */
//	public Map(int cnt, int nextDir, int maxCnt) {
//		super();
//		this.cnt = cnt;
//		this.nextDir = nextDir;
//		this.maxCnt = maxCnt;
//	}
//}
//
//
///*
//	-미생물을 리스트로 만들어 걔네만 관리하지 말고, map[][]을 만들되 int형 2차원 배열이 아니라 내가 만든 클래스 타입으로 정한다.
//	그리고 map[row][col]별로 sum, nextDir, maxCnt를 저장할 수 있도록 한다.
//	1시간 단위로 for문을 반복시킨다.
//	
//	-미생물 군집이 이미 이동한건지 아닌지 구분할 수가 없기 때문에, map을 2개 만들어 사용한다. (curMap, nextMap)
//	
//	미생물 군집합이 겹치는지를 판별할 때에는, 모든 이동이 끝난 후에 해야 한다. (이동 후 겹치는지 판별)
//	약품처리된 셀에서는 합쳐질 경우가 없기 때문에 고려하지 않아도 된다.
//	
//	
// */
//public class Solution {
//	static int N,M,K;	// 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K
//	static Map[][] map;
//	static Map[][] nextMap;
//	// 미생물이 움직일 수 있는 방향. dir[0]은 안 쓰이며, dir[1]=상, dir[2]=하, dir[3]=좌, dir[4]=우
//	static Dir[] dir={new Dir(0,0),new Dir(-1,0),new Dir(1,0),new Dir(0,-1),new Dir(0,1)};
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		int tc=Integer.parseInt(br.readLine());
//		
//		for(int i=1; i<=tc; i++) {
//			String[] s=br.readLine().split(" ");
//			N=Integer.parseInt(s[0]);
//			M=Integer.parseInt(s[1]);
//			K=Integer.parseInt(s[2]);
//			map=new Map[N][N];
//			nextMap=new Map[N][N];
//			// map 초기화
//			for(int j=0; j<N; j++)
//				for(int k=0; k<N; k++)
//					map[j][k]=new Map(0,0,0);
//			
//			for(int j=0; j<K; j++) {
//				s=br.readLine().split(" ");
//				map[Integer.parseInt(s[0])][Integer.parseInt(s[1])]=new Map(Integer.parseInt(s[2]),Integer.parseInt(s[3]),Integer.parseInt(s[2]));
//			}
//			
//			System.out.println("#"+i+" "+solve());
//		}
//	}
//	
//	// 시간이 흐른 뒤 남아있는 미생물의 수를 리턴
//	public static int solve() {
//		int sum=0;
//		int nextRow=0;
//		int nextCol=0;
//		Map cur=null;
//		
//		// row, col, maxCnt, maxDir, micSum 저장
//		for(int i=1; i<=M; i++) {		// 격리 시간이 M시간이므로 M번 반복
//			// map[][]의 모든 좌표에 대해 진행한다.
//			for(int j=0; j<N; j++) {
//				for(int k=0; k<N; k++) {
//					cur=map[j][k];
//					if(cur.cnt!=0) {	// 군집합이 존재하는 경우
//						// 1. 군집합 이동
//						nextRow=j+dir[cur.nextDir].row;		// 이동 후 row
//						nextCol=k+dir[cur.nextDir].col;		// 이동 후 col
//						swap(map[j][k],map[nextRow][nextCol]);
//						
//						// 2. 약품처리된 셀인 경우
//						if(isMedicine(nextRow,nextCol)) {
//							
//						}
//						
//						// 3. 이미 이동을 했는데 군집합이 겹치는 경우 (아직 이동하지 않은 군집합은 제외. 이동 후 다른 곳으로 가기 때문)
//						
//					}
//				}
//			}	// map[][] 탐색 끝
//		}
//		
//		
//		// 미생물의 총합을 구한다.
//		for(int i=0; i<N; i++)
//			for(int j=0; j<N; j++)
//				sum+=map[i][j].cnt;
//		
//		return sum;
//	}
//	
//	// 테두리에 있는 셀이면 return true
//	public static boolean isMedicine(int row, int col) { 
//		if(row==0 || row==N-1 || col==0 || col==N-1)
//			return true;
//		return false;
//	}
//	
//	// Map타입의 a,b의 정보를 서로 교환
//	public static void swap(Map a, Map b) {
//		Map tmp=a;
//		a=b;
//		b=tmp;
//	}
//}
//
//
//
////for(int j=mic.size()-1; j>=0; j--) {	// 모든 군집합에 대해 진행. 미생물 개수가 0개가 되면 제거 후 index 때문에 j를 큰 값에서부터 진행
////	// 군집합들 이동
////	// 1. 약품처리된 셀일 경우 미생물 수를 2로 나누어 주고, 방향을 바꾼다. 미생물 개수가 0개가 되면 해당 군집합을 리스트에서 제거
////	// 군집합 이동
////	pop=mic.get(j);
////	pop.row+=dir[pop.nextDir].row;
////	pop.col+=dir[pop.nextDir].col;
////	
////	if(isExist[pop.row][pop.col]) {	// 기존에 다른 군집합이 있는 경우
////		// 미생물 수를 합한다.
////		pop.cnt
////		
////		if( comp.get(new Dir(pop.row, pop.col)).cnt < pop.cnt) {
////			
////		}
////	} else {
////		isExist[pop.row][pop.col]=true;
////	}
////	
////	
////	// 약품처리된 셀인 경우 처리
////	if(isMedicine(pop.row,pop.col)) {
////		// 미생물 개수를 2로 나눈다.
////		pop.cnt/=2;
////		
////		// 미생물 개수가 0개가 되면 리스트에서 제거
////		if(pop.cnt==0) {
////			mic.remove(pop);
////			continue;	// 제거된 경우 방향 바꿀 필요 없으니 continue
////		}
////		
////		// 방향을 바꾼다.
////		switch(pop.nextDir) {
////			case 1:	// 상->하
////				pop.nextDir=2;
////				break;
////			case 2:	// 하->상
////				pop.nextDir=1;
////				break;
////			case 3:	// 좌->우
////				pop.nextDir=4;
////				break;
////			case 4:	// 우->좌
////				pop.nextDir=3;
////				break;
////		}
////	}
////}
////
////// 겹치는 군집합 있는지 확인하고, 겹칠 경우 미생물의 수를 합하여 하나의 군집합으로 만들고 방향 설정
////// 겹치는 군집합들을 없애고 그 수를 합하여 새로운 군집합을 만들고 방향을 정한다.
////// 약품처리된 셀에서는 겹칠 경우가 없으므로 검사하지 않아도 된다.
////for(int j=1; j<N-1; j++) {
////	for(int k=1; k<N-1; k++) {
////		
////	}
////}
////
////// 군집합의 수가 0개이면 더 이상 진행하지 않고 0을 리턴
////if(mic.isEmpty())
////	return 0;
//
//
//
//
//
//
//
