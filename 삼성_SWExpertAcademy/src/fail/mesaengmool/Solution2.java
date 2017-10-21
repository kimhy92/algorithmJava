package fail.mesaengmool;
///**
// * @ClassName : Solution
// * @Author : Kim HyunYong
// * @Date : 2017. 10. 20.
// * @Description : -
// * @Version : 1.0
// * @History : ������, ������, ��������
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
//class Dir {	// ���⿡ ���� �������� �� ��,���� ���� �����ϱ� ���� Ŭ����
//	int row,col;
//
//	public Dir(int row, int col) {
//		super();
//		this.row = row;
//		this.col = col;
//	}
//}
//
//class Map {		// �̻��� �������� �ǹ��ϴ� Ŭ����
//	int cnt, nextDir, maxCnt;
//	/*
//	cnt : ���� �̻��� �����տ� �����ִ� �̻����� ��
//	nextDir : ������ ������ ����. 1:��,2:��,3:��,4:��
//	maxCnt : ���� �������� ������ ��, ���� �̻����� ���� ���� �������� �̻��� ��
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
//	-�̻����� ����Ʈ�� ����� �³׸� �������� ����, map[][]�� ����� int�� 2���� �迭�� �ƴ϶� ���� ���� Ŭ���� Ÿ������ ���Ѵ�.
//	�׸��� map[row][col]���� sum, nextDir, maxCnt�� ������ �� �ֵ��� �Ѵ�.
//	1�ð� ������ for���� �ݺ���Ų��.
//	
//	-�̻��� ������ �̹� �̵��Ѱ��� �ƴ��� ������ ���� ���� ������, map�� 2�� ����� ����Ѵ�. (curMap, nextMap)
//	
//	�̻��� �������� ��ġ������ �Ǻ��� ������, ��� �̵��� ���� �Ŀ� �ؾ� �Ѵ�. (�̵� �� ��ġ���� �Ǻ�)
//	��ǰó���� �������� ������ ��찡 ���� ������ ������� �ʾƵ� �ȴ�.
//	
//	
// */
//public class Solution {
//	static int N,M,K;	// ���� ���� N, �ݸ� �ð� M, �̻��� ������ ���� K
//	static Map[][] map;
//	static Map[][] nextMap;
//	// �̻����� ������ �� �ִ� ����. dir[0]�� �� ���̸�, dir[1]=��, dir[2]=��, dir[3]=��, dir[4]=��
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
//			// map �ʱ�ȭ
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
//	// �ð��� �帥 �� �����ִ� �̻����� ���� ����
//	public static int solve() {
//		int sum=0;
//		int nextRow=0;
//		int nextCol=0;
//		Map cur=null;
//		
//		// row, col, maxCnt, maxDir, micSum ����
//		for(int i=1; i<=M; i++) {		// �ݸ� �ð��� M�ð��̹Ƿ� M�� �ݺ�
//			// map[][]�� ��� ��ǥ�� ���� �����Ѵ�.
//			for(int j=0; j<N; j++) {
//				for(int k=0; k<N; k++) {
//					cur=map[j][k];
//					if(cur.cnt!=0) {	// �������� �����ϴ� ���
//						// 1. ������ �̵�
//						nextRow=j+dir[cur.nextDir].row;		// �̵� �� row
//						nextCol=k+dir[cur.nextDir].col;		// �̵� �� col
//						swap(map[j][k],map[nextRow][nextCol]);
//						
//						// 2. ��ǰó���� ���� ���
//						if(isMedicine(nextRow,nextCol)) {
//							
//						}
//						
//						// 3. �̹� �̵��� �ߴµ� �������� ��ġ�� ��� (���� �̵����� ���� �������� ����. �̵� �� �ٸ� ������ ���� ����)
//						
//					}
//				}
//			}	// map[][] Ž�� ��
//		}
//		
//		
//		// �̻����� ������ ���Ѵ�.
//		for(int i=0; i<N; i++)
//			for(int j=0; j<N; j++)
//				sum+=map[i][j].cnt;
//		
//		return sum;
//	}
//	
//	// �׵θ��� �ִ� ���̸� return true
//	public static boolean isMedicine(int row, int col) { 
//		if(row==0 || row==N-1 || col==0 || col==N-1)
//			return true;
//		return false;
//	}
//	
//	// MapŸ���� a,b�� ������ ���� ��ȯ
//	public static void swap(Map a, Map b) {
//		Map tmp=a;
//		a=b;
//		b=tmp;
//	}
//}
//
//
//
////for(int j=mic.size()-1; j>=0; j--) {	// ��� �����տ� ���� ����. �̻��� ������ 0���� �Ǹ� ���� �� index ������ j�� ū ���������� ����
////	// �����յ� �̵�
////	// 1. ��ǰó���� ���� ��� �̻��� ���� 2�� ������ �ְ�, ������ �ٲ۴�. �̻��� ������ 0���� �Ǹ� �ش� �������� ����Ʈ���� ����
////	// ������ �̵�
////	pop=mic.get(j);
////	pop.row+=dir[pop.nextDir].row;
////	pop.col+=dir[pop.nextDir].col;
////	
////	if(isExist[pop.row][pop.col]) {	// ������ �ٸ� �������� �ִ� ���
////		// �̻��� ���� ���Ѵ�.
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
////	// ��ǰó���� ���� ��� ó��
////	if(isMedicine(pop.row,pop.col)) {
////		// �̻��� ������ 2�� ������.
////		pop.cnt/=2;
////		
////		// �̻��� ������ 0���� �Ǹ� ����Ʈ���� ����
////		if(pop.cnt==0) {
////			mic.remove(pop);
////			continue;	// ���ŵ� ��� ���� �ٲ� �ʿ� ������ continue
////		}
////		
////		// ������ �ٲ۴�.
////		switch(pop.nextDir) {
////			case 1:	// ��->��
////				pop.nextDir=2;
////				break;
////			case 2:	// ��->��
////				pop.nextDir=1;
////				break;
////			case 3:	// ��->��
////				pop.nextDir=4;
////				break;
////			case 4:	// ��->��
////				pop.nextDir=3;
////				break;
////		}
////	}
////}
////
////// ��ġ�� ������ �ִ��� Ȯ���ϰ�, ��ĥ ��� �̻����� ���� ���Ͽ� �ϳ��� ���������� ����� ���� ����
////// ��ġ�� �����յ��� ���ְ� �� ���� ���Ͽ� ���ο� �������� ����� ������ ���Ѵ�.
////// ��ǰó���� �������� ��ĥ ��찡 �����Ƿ� �˻����� �ʾƵ� �ȴ�.
////for(int j=1; j<N-1; j++) {
////	for(int k=1; k<N-1; k++) {
////		
////	}
////}
////
////// �������� ���� 0���̸� �� �̻� �������� �ʰ� 0�� ����
////if(mic.isEmpty())
////	return 0;
//
//
//
//
//
//
//
