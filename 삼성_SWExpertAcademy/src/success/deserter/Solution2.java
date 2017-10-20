/**
 * @ClassName : Solution2
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package success.deserter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
�� ������ ó������ dfsŽ���� ����ġ�� Ȱ���ؼ� �Ϸ� �ߴµ�, �׷��� �ϸ� �� �ȴ�. (Solution.java -> dfs�� Ǯ�ٰ� �ʹ� ������ �� �˰� ������ ������. ���ش� ����)
�켱 ����ġ�⸦ ���� ������ �ð����� �ʰ� �߻�
����ġ�⸦ �� �� -> ������ �湮���� ���� ����ð��� ���� �湮�� ���� ����ð����� ���� ������ ����ġ�Ⱑ �����ѵ�,
�� �� üũ�ϴ� ���� �� �ſ� �����ϰ� ��ȿ�����̸� �����ϴ� ���Ҵ�.

bfs�� �ؾ� �Ѵ�.(Solution2 ����)
bfs�� �ϸ� ���°���Ʈ���� �ð������̴ϱ� ���߿� �ͳ��� �ߺ��湮�ص� �ð��� �� �ɷȴٴ°� ������ �ȴ�.
�׷��� �� �κ��� ���� üũ�� �ʿ���� visit�� �˸� �ȴ�.

dfs�� bfs�� ���� ����Ž���� �ϴ� ��� -> �� ���� ����ð� �Ȱ����� ������� �� �ִ�.
������ ����ġ�⸦ �Ѵ�? -> dfs�� bfs�� ����ġ�� ������ �޶��� �� �ִ�. �� ������ �� ��� �Ѵ�.
 */

class Location {
	int row, col, l;

	public Location(int row, int col, int l) {
		super();
		this.row = row;
		this.col = col;
		this.l = l;
	}
}

public class Solution2 {
	/*
	N : ���� �ͳ� ������ ���� ũ��
	M : ���� ũ��
	R : ��Ȧ �Ѳ��� ��ġ������� ���� ��ġ
	C : ���� ��ġ
	L : Ż�� �� �ҿ�� �ð�
	 */
	static int N,M,R,C,L;
	static int[][] map;
	static boolean[][] isVisited;	// �湮�ߴ��� üũ�ϴ� �迭
	static int[][][] road={		// road[i-1][0][0] = �ͳ� Ÿ���� i�� ��, ���� row,col�� �������� ������ �� �ִ� ��  
			{{-1,0},{1,0},{0,-1},{0,1}},	// �����¿�
			{{-1,0},{1,0}},					// ����
			{{0,-1},{0,1}},					// �¿�
			{{-1,0},{0,1}},					// ���
			{{1,0},{0,1}},					// �Ͽ�
			{{1,0},{0,-1}},					// ����
			{{-1,0},{0,-1}}// ����
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int x=1; x<=tc; x++) {
			String[] s=br.readLine().split(" ");
			
			N=Integer.parseInt(s[0]);
			M=Integer.parseInt(s[1]);
			R=Integer.parseInt(s[2]);
			C=Integer.parseInt(s[3]);
			L=Integer.parseInt(s[4]);
			map=new int[N][M];
			isVisited=new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				s=br.readLine().split(" ");
				for(int j=0; j<M; j++)
					map[i][j]=Integer.parseInt(s[j]);
			}
			
			// ���� ��ġ. (1�ð��� ������ ��Ȧ�̰�, 1<=L<=20 �̱� ������, ���°���Ʈ���� root node�� ������ġ�� �ͳη� ��Ƶ� ����)
			System.out.println("#"+x+" " + solve());
		}
	}

	/*
	return : Ż�ֹ��� ���� �� �ִ� ������ ����� ��
	 */
	public static int solve() {
		// �湮�� �ͳ� ������ üũ�ؼ� ����
		int cnt=0;
		
		Queue<Location> q=new LinkedList<Location>();
		
		q.offer(new Location(R,C, 1));	// ���� ��ġ�� �ִ´�.
		Location pop=null;
		while(!q.isEmpty()) {
			pop=q.poll();
			if(pop.l>L)
				break;
			
			if(isVisited[pop.row][pop.col])
				continue;
			
			isVisited[pop.row][pop.col]=true;
			cnt++;
			
			for(int i=0; i<road[map[pop.row][pop.col]-1].length; i++) {
				int nextRow=pop.row+road[map[pop.row][pop.col]-1][i][0];
				int nextCol=pop.col+road[map[pop.row][pop.col]-1][i][1];
				// �ͳ��� �̾��� �����鼭, ���� Ž���Ϸ��� ���� map �迭 ���� �����̰�, �ͳ��̸鼭 ���� ����Ǿ� �ִ� ��쿡 ť�� �ִ´�.
				if(checkRange(nextRow,nextCol) && isConntedTunnel(map[nextRow][nextCol], nextRow, nextCol, pop.row, pop.col) && !isVisited[nextRow][nextCol]) {
					q.offer(new Location(nextRow,nextCol, pop.l+1));
				}
			}
		}
		
		return cnt;
	}
	
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=M)
			return false;
		return true;
	}
	
	public static boolean isConntedTunnel(int roadType, int nextRow, int nextCol, int row, int col) {
		// �켱, �������� Ž���Ϸ��� ���� �ͳ��� �ƴ϶�� return false
		if(roadType<1 || roadType>7)
			return false;
		
		// �ͳ��� ��쿡�� ���� ����Ǿ� �ִ��� Ȯ���Ѵ�.
		// 1. map[row][col] -> map[nextRow][nextCol] �� �����ִ���,
		// 2. map[nextRow][nextCol] -> map[row][col] �� �����ִ���
		// �̷��� 2�� üũ�ؾ� �ϴµ�, 1��° ���� �̹� �����ִٰ� ������ �ȴ�. (road �迭�� �����ִ� ��츸 �־����Ƿ�)
		// ���� 2��° ���Ǹ� �˻��ϸ� �ȴ�.
		for(int i=0; i<road[roadType-1].length; i++) {
			if(nextRow+road[roadType-1][i][0]==row && nextCol+road[roadType-1][i][1]==col)
				return true;
		}
		
		return false;
	}
}
