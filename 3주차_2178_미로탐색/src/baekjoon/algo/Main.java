/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
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
		int n, m; // n:��, m:��
		Queue<RC> q = new LinkedList<RC>(); // bfs Ž���� ť

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// �����¿� �˻��� ���� ��
		int checkRow[]={-1,+1,0,0};
		int checkCol[]={0,0,-1,+1};

		int[][] map = new int[n][m]; // n�� m���� 2���� �迭
		
		String line = null;
		// map init
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			for (int j = 0; j < m; j++) {
				if(line.charAt(j)=='1')	// ���̸� -2
					map[i][j]=-2;
				else if(line.charAt(j)=='0')	// ���̸� -1
					map[i][j]=-1;
			}
		}

		// 0�� 0������, n-1�� m-1���� ��ġ�� �̵��� �� �ִ� �Ÿ��� ���Ѵ�.
		// ���� ���ǻ�, ������ ������ �� �ִٴ� ������ �����Ƿ�, ���� ���ٴ� ���ܴ� ó���� �ʿ� ����.
		q.add(new RC(0, 0));
		map[0][0]=1;	// ���� ĭ�� ������� �����϶�� �Ͽ����Ƿ� 1�� �������ش�.
		RC getData = null;
		int tmpRow=0, tmpCol=0;
		while (!q.isEmpty()) { // �� �̻� ���� �����Ͱ� ���� ������ �ݺ�
			getData = q.poll(); // �����͸� �д´�.
			// �� �濡 ���ؼ� �����¿� 4���� ���� �˻��Ѵ�. �� ĭ�� �迭 �ε��� ���� ���̰�, ���̶�� ���� ���� �� +1�� ���ش�.
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
