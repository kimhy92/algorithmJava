/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n, m; // n:행, m:열
		Queue<RC> q = new LinkedList<RC>(); // bfs 탐색용 큐

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 상하좌우 검색을 위한 값
		int checkRow[]={-1,+1,0,0};
		int checkCol[]={0,0,-1,+1};

		int[][] map = new int[n][m]; // n행 m열의 2차원 배열
		
		String line = null;
		// map init
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			for (int j = 0; j < m; j++) {
				if(line.charAt(j)=='1')	// 길이면 -2
					map[i][j]=-2;
				else if(line.charAt(j)=='0')	// 벽이면 -1
					map[i][j]=-1;
			}
		}

		// 0행 0열에서, n-1행 m-1열의 위치로 이동할 때 최단 거리를 구한다.
		// 문제 조건상, 무조건 도착할 수 있다는 가정이 있으므로, 길이 없다는 예외는 처리할 필요 없다.
		q.add(new RC(0, 0));
		map[0][0]=1;	// 시작 칸도 결과값에 포함하라고 하였으므로 1로 설정해준다.
		RC getData = null;
		int tmpRow=0, tmpCol=0;
		while (!q.isEmpty()) { // 더 이상 읽을 데이터가 없을 때까지 반복
			getData = q.poll(); // 데이터를 읽는다.
			// 각 방에 대해서 상하좌우 4개의 방을 검사한다. 각 칸이 배열 인덱스 안의 값이고, 길이라면 현재 방의 값 +1을 해준다.
			for(int i=0; i<checkRow.length; i++) {
				tmpRow = getData.row+checkRow[i];
				tmpCol = getData.col+checkCol[i];
				if(inRange(tmpRow, tmpCol, n, m) && map[tmpRow][tmpCol]==-2) {
					q.add(new RC(tmpRow, tmpCol));
					map[tmpRow][tmpCol] = map[getData.row][getData.col]+1;
				}
			}
		}
		
		System.out.println(map[n-1][m-1]);
	}

	private static boolean inRange(int row, int col, int n, int m) {
		if ((0 <= row && row < n) && (0 <= col && col < m))
			return true;

		return false;
	}

	private static class RC {
		private int row;
		private int col;

		public RC(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}
