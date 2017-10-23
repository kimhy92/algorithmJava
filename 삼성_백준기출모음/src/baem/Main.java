/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 21.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package baem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Loc {
	int row,col;

	public Loc(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}

class Move {	// 몇 초에 어느 방향으로 방향을 바꾸는지 저장할 클래스
	int time;		// 시간 (몇 초인지)
	char direc;		// 회전방향. L : 왼쪽, D : 오른쪽
	public Move(int time, char direc) {
		super();
		this.time = time;
		this.direc = direc;
	}
}

public class Main {
	static int N,K,L;
	static int[][] map;		// 0:길, 1:사과, 2:뱀
	// 방향 : 상하좌우
	static Loc[] dir={new Loc(-1,0),new Loc(1,0),new Loc(0,-1),new Loc(0,1)};
	static Queue<Move> q=new LinkedList<Move>();	// 방향 전환 정보들
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		map[1][1]=2;		// 뱀의 최초 위치는 1행 1열
		K=Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			String s[]=br.readLine().split(" ");
			map[Integer.parseInt(s[0])][Integer.parseInt(s[1])]=1;
		}
		L=Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {		// 방향 전환 정보
			String s[]=br.readLine().split(" ");
			q.add(new Move(Integer.parseInt(s[0]),s[1].charAt(0)));
		}
		
		System.out.println(solve());
	}

	// 몇 초후에 게임이 끝나는지 리턴
	public static int solve() {
		int res=0;
		int time=2;
		int compareTime=0;
		Move pop=null;		// 방향전환 정보
		Loc curDir=dir[3];	// 최초의 진행 방향은 오른쪽
		Loc curP=new Loc(1,1);	// 최초 뱀의 위치는 1행 1열
		Loc nextP=new Loc(2,1);	// 다음 뱀의 위치
		Loc tailP=null;			// 뱀의 꼬리 위치
		Queue<Loc> baem=new LinkedList<Loc>();	// 뱀의 위치들을 나타내기 위한 큐
		baem.add(new Loc(1,1));		// 최초 뱀의 위치
		
		while(true) {
			pop=q.peek();	// q의 head 검사(not remove)
			
			if(pop!=null && compareTime==pop.time) {		// 방향을 전환해야 하는 경우. 단, 방향 전환을 더이상 하지 않아도 진행해야 한다.
				pop=q.poll();		// 방향 전환시 큐에서 제거
				
				switch(pop.direc) {
				case 'L':
					if(curDir==dir[0]) {	// 상->좌
						curDir=dir[2];
					} else if(curDir==dir[1]) {	// 하->우
						curDir=dir[3];
					} else if(curDir==dir[2]) {	// 좌->하
						curDir=dir[1];
					} else if(curDir==dir[3]) {	// 우->상
						curDir=dir[0];
					}
					break;
				case 'D':
					if(curDir==dir[0]) {	// 상->우
						curDir=dir[3];
					} else if(curDir==dir[1]) {	// 하->좌	
						curDir=dir[2];
					} else if(curDir==dir[2]) {	// 좌->상
						curDir=dir[0];
					} else if(curDir==dir[3]) {	// 우->하
						curDir=dir[1];
					}
					break;
				}
			}
			
			// 현재의 방향대로 진행한다.
			nextP.row=curP.row+curDir.row;
			nextP.col=curP.col+curDir.col;
			
			// 벽이거나 뱀을 만나면 res=time 후 break
			if(isWall(nextP.row,nextP.col) || isBaem(nextP.row,nextP.col)) {
				res=compareTime+2;
				break;
			}
			
			baem.add(new Loc(nextP.row,nextP.col));	// 뱀을 추가
			
			// 사과를 만난 경우에는 꼬리를 없애지 않아도 되므로 아무것도 하지 않고, 사과가 아닌 경우만 처리해 준다.
			if(map[nextP.row][nextP.col]==0) {	// 사과가 아닌 경우(0)
				// 꼬리가 위치한 칸을 큐에서 빼낸다.
				tailP=baem.poll();
				// 꼬리가 위치했던 칸을 빈 칸으로 만든다.
				map[tailP.row][tailP.col]=0; 
			}
			
			map[nextP.row][nextP.col]=2;	// 뱀이 이동한 칸을 2로 만든다.
			curP=nextP;			// 다음 칸을 가리키도록 바꾼다.
			
			time++;
			compareTime++;
		}
		
		return res;
	}
	
	// 벽이면 return true
	public static boolean isWall(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
	
	// 뱀이면 return true
	public static boolean isBaem(int row, int col) {
		if(map[row][col]==2)
			return true;
		return false;
	}
}





