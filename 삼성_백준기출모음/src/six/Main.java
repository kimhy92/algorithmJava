/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 9.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
��� : ���
���� Ǭ Ǯ�� :
1. �켱 �������� ���� : �� ������ ���� ���� �� �� ���� ����� �簢��
2. ������ ��� ������ ���ؼ� ���������κ��� �ٸ� �簢������ ���� �������ִ��� figures �迭�� ����
	ex) figures[0][0]�� ���� (0,1), (0,2), (0,3)�̴�. �̴�  ��������  �̷��� 4���� �簢���� ���� ��Ʈ�ι̳�(����)�̴�.
		�� ��, ���� ������ �簢���� �������� �Ǹ� ������ �簢������ ���������� ���� 0��1��, 0��2��, 0��3����ŭ �������ִ� ���� �ǹ��Ѵ�.
3. main������ �켱 input�� �޴´�.
4. solve() : �켱 paper�� ��� ��ǥ�� �������� �ȴ�. �׸��� �� ���������κ��� ������ ��� ������ ���ؼ� paper[][]�� ���� ������ ���� ���Ѵ�.
			��, ������ paper[][] ������ ������ ���� üũ���ش�. ���� ������ �簢���� ��� paper �ȿ� �ִٸ� ���� ���ϰ�,
			������ �ִ밪�� ���Ѵ�.
5. paper�� ��� ���� ���������� ��Ƽ� ���ϱ� ������, O(n)=n*m*19=n^2*19�� �ȴ�. (������ ������ ������ 19���̰�, �����ǥ��n����)
=============== �ؼ� ã�ƺ� ���,
paper�� ��� �簢���� ���������� �� ���� ������, ���� ������ ��� ������ ���� üũ�ѰŰ�
�̰� ���������ʰ� dfs�� ��
�׸��� �� ������ dfs�� �ȵǼ� ���� üũ�Ͽ��ٰ� ��

 */
public class Main {
	static int n,m;
	static int[][] paper;
	static int max=-1;		// paper�� ��� ���� 1�̻� 1000�����̹Ƿ� max�� �ʱⰪ�� -1
	// ������ ��� ������ ��ǥ. �������� ����. �������� ���� ���� �� �� ���� ����� ���簢��
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
		// O(n) = n^2*19 ? (������ �������� 19��)
		// paper�� �� ��ǥ�� �������� ������ ��� ������ sum�� ���Ѵ�.
		// �� �� max���� ���Ѵ�.
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
			// ������ ��� �簢���� paper �ȿ� ����ִ��� üũ�ϰ�, �ƴ� ��� return -1
			// 0�� <= row <= n-1��, 0�� <= col <= m-1��
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












































