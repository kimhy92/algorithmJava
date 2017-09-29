/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 29.
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

public class Main {
	static int n;
	static int[][] graph;
	static int[][] res;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		res = new int[n][n];
		visited = new int[n];
		Arrays.fill(visited, 0);
		for(int i=0; i<n; i++) {
			Arrays.fill(res[i], 0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				graph[i][j]=Integer.parseInt(st.nextToken());
		}
		
		solve();
	}
	
	// �����ذ�� ������
	// ���� i�� j�� i->j �� ���� ����� ���� �Ӹ� �ƴ϶�, i->k->j �� ���� ���������� ����� ���� ����ؾ� ��
	// �ڱ� �ڽ����� �ٽ� �� �� �ִ����� ����ؾ� �� (���� �׷����̱� ������ �ڱ� �ڽ����� ���ƿ� �� �ִ���. ��, cycle���� �ƴ���) -> �������� ������ visited[i]�� 1�� ���� ������ �ذ�
	public static void solve() {
		for(int i=0; i<n; i++) {
			Arrays.fill(visited, 0);
			dfsRecur(i, true);
			for(int j=0; j<n; j++)	// ���Ⱑ �ٽ�. ������ dfs�� �ٸ� ��. i�� �������� �湮�� ��� ���� ����Ǿ� �ִ� ���̹Ƿ� res[i][j]=visited[j]
				res[i][j]=visited[j];
		}
				
		for(int[] line : res) {
			for(int ele : line)
				System.out.print(ele+" ");
			System.out.println();
		}
	}
	
	public static void dfsRecur(int cur, boolean isStart) {
		if(!isStart)
			visited[cur]=1;
		for(int i=0; i<n; i++)
			if(graph[cur][i]!=0 && visited[i]==0)
				dfsRecur(i, false);
	}
}
