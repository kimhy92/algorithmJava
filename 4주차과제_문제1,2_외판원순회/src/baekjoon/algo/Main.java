/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 28.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 2098��, 10971�� ����. TSP (Traveling Salesman Problem) - ���ǿ� ��ȸ ����. �⺻
// 2098 : �ð����� 1�� / �޸� 128MB
// 10971 : �ð����� 2�� / �޸� 256MB
public class Main {
	static int n;
	static int[][] graph;
	static int[][] cache;
	static int INFINITY=100000000;
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		graph = new int[n][n];
		cache = new int[n][1<<n];
		
		// init
		for(int i=0; i<n; i++) {
			Arrays.fill(graph[i], 0);
			StringTokenizer st = new StringTokenizer(br.readLine());;
			for(int j=0; j<n; j++)
				graph[i][j]=Integer.parseInt(st.nextToken());
		}
		
		System.out.println(tsp(0,1));
	}
	
	public static int tsp(int current, int visited) {
		if(visited== (1<<n)-1)	// ��� �湮�� ���, ���� �������� ������������ ���ư��� ���. ���� ���ٸ� IMPOSSIBLE�� ����
			return graph[current][0]!=0?graph[current][0]:INFINITY;
		
		if(cache[current][visited]!=-1)
			return cache[current][visited];
		
		int res=INFINITY;
		
		for(int i=0; i<n; i++) {
			if((visited & (1<<i)) !=0 ) continue;	// �湮�� �����̸� continue
			if(graph[current][i] == 0 ) continue;	// ���� �������� i������ ���� ���� ���ٸ� continue
			
			// ���� �������� i������ ���� ���� �ִ� ���, min( ������ ������ �湮�ϴ� �� ��� �ּ� ��� �� ��������� �ּҰ� , ������ ������ �湮�ϴ� �� ��� �ּ� ��� )
			// ù��° parameter : ������ ������ �湮�ϴ� �� ��� �ּ� ���� �߿��� ������� ���� �ּҰ�
			// �ι�° parameter : ������ ������ �湮�ϴ� �� ��� �ּ� ��� (Ư�� ���� i�� ���� ���� �ּ� ����� �ǹ�)
			// 				   i������ ���������� ���� �������� ��� �湮�ϰ� 0�� ������ ���ư��� �� ��� �ּ� ��� + ���翡�� i�������� ��� )
			// tspRecur(i, visited|(1<<i)) -> i������ ����������, ������ ������ ��� �湮�ϰ� 0�� ������ ���ư��� �ּ� ���. �� ��, visited | (1<<i)��, i�� �湮�ߴٰ� ǥ���ϱ� ����
			// graph[current][i]�� �����ִ� ������, tspRecur(i,~)�� i�� ���������� ����ϱ� ������, ���� �������� i������ ���� ��뵵 �����ִ� ��.
			res = Math.min(res, tsp(i, visited | (1<<i)) + graph[current][i]);
		}
		return res=cache[current][visited];
	}
}
