/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 21.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
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

class Move {	// �� �ʿ� ��� �������� ������ �ٲٴ��� ������ Ŭ����
	int time;		// �ð� (�� ������)
	char direc;		// ȸ������. L : ����, D : ������
	public Move(int time, char direc) {
		super();
		this.time = time;
		this.direc = direc;
	}
}

public class Main {
	static int N,K,L;
	static int[][] map;		// 0:��, 1:���, 2:��
	// ���� : �����¿�
	static Loc[] dir={new Loc(-1,0),new Loc(1,0),new Loc(0,-1),new Loc(0,1)};
	static Queue<Move> q=new LinkedList<Move>();	// ���� ��ȯ ������
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		map[1][1]=2;		// ���� ���� ��ġ�� 1�� 1��
		K=Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			String s[]=br.readLine().split(" ");
			map[Integer.parseInt(s[0])][Integer.parseInt(s[1])]=1;
		}
		L=Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {		// ���� ��ȯ ����
			String s[]=br.readLine().split(" ");
			q.add(new Move(Integer.parseInt(s[0]),s[1].charAt(0)));
		}
		
		System.out.println(solve());
	}

	// �� ���Ŀ� ������ �������� ����
	public static int solve() {
		int res=0;
		int time=2;
		int compareTime=0;
		Move pop=null;		// ������ȯ ����
		Loc curDir=dir[3];	// ������ ���� ������ ������
		Loc curP=new Loc(1,1);	// ���� ���� ��ġ�� 1�� 1��
		Loc nextP=new Loc(2,1);	// ���� ���� ��ġ
		Loc tailP=null;			// ���� ���� ��ġ
		Queue<Loc> baem=new LinkedList<Loc>();	// ���� ��ġ���� ��Ÿ���� ���� ť
		baem.add(new Loc(1,1));		// ���� ���� ��ġ
		
		while(true) {
			pop=q.peek();	// q�� head �˻�(not remove)
			
			if(pop!=null && compareTime==pop.time) {		// ������ ��ȯ�ؾ� �ϴ� ���. ��, ���� ��ȯ�� ���̻� ���� �ʾƵ� �����ؾ� �Ѵ�.
				pop=q.poll();		// ���� ��ȯ�� ť���� ����
				
				switch(pop.direc) {
				case 'L':
					if(curDir==dir[0]) {	// ��->��
						curDir=dir[2];
					} else if(curDir==dir[1]) {	// ��->��
						curDir=dir[3];
					} else if(curDir==dir[2]) {	// ��->��
						curDir=dir[1];
					} else if(curDir==dir[3]) {	// ��->��
						curDir=dir[0];
					}
					break;
				case 'D':
					if(curDir==dir[0]) {	// ��->��
						curDir=dir[3];
					} else if(curDir==dir[1]) {	// ��->��	
						curDir=dir[2];
					} else if(curDir==dir[2]) {	// ��->��
						curDir=dir[0];
					} else if(curDir==dir[3]) {	// ��->��
						curDir=dir[1];
					}
					break;
				}
			}
			
			// ������ ������ �����Ѵ�.
			nextP.row=curP.row+curDir.row;
			nextP.col=curP.col+curDir.col;
			
			// ���̰ų� ���� ������ res=time �� break
			if(isWall(nextP.row,nextP.col) || isBaem(nextP.row,nextP.col)) {
				res=compareTime+2;
				break;
			}
			
			baem.add(new Loc(nextP.row,nextP.col));	// ���� �߰�
			
			// ����� ���� ��쿡�� ������ ������ �ʾƵ� �ǹǷ� �ƹ��͵� ���� �ʰ�, ����� �ƴ� ��츸 ó���� �ش�.
			if(map[nextP.row][nextP.col]==0) {	// ����� �ƴ� ���(0)
				// ������ ��ġ�� ĭ�� ť���� ������.
				tailP=baem.poll();
				// ������ ��ġ�ߴ� ĭ�� �� ĭ���� �����.
				map[tailP.row][tailP.col]=0; 
			}
			
			map[nextP.row][nextP.col]=2;	// ���� �̵��� ĭ�� 2�� �����.
			curP=nextP;			// ���� ĭ�� ����Ű���� �ٲ۴�.
			
			time++;
			compareTime++;
		}
		
		return res;
	}
	
	// ���̸� return true
	public static boolean isWall(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
	
	// ���̸� return true
	public static boolean isBaem(int row, int col) {
		if(map[row][col]==2)
			return true;
		return false;
	}
}





