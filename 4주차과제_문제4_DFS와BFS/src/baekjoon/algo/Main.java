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
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m,start;
	static int[][] graph;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		start=Integer.parseInt(st.nextToken());
		
		graph = new int[n+1][n+1];	// index 1���� ���
		visited = new int[n+1];
		
		// init
		for(int i=0; i<=n; i++) {
			Arrays.fill(graph[i], 0);
			visited[i]=0;
		}
		
		// input
		for(int i=0; i<m; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			graph[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())]=1;
		}
		
		// solve
		dfs();
		for(int i=0; i<=n; i++)
			visited[i]=0;
		bfs();
	}
	
	// back tracking ���� �ʰ� �ٷ� ���. back tracking ������ ���� ������ ��������� ������ �޸� �ʿ�
	public static void dfs() {
		for(int i=1; i<=n; i++)
			if(visited[i]==0)
				dfsRecur(i);
		System.out.println();
	}
	
	public static void dfsRecur(int cur) {
		visited[cur]=1;
		System.out.print(cur+" ");
		for(int i=1; i<=n; i++)
			if(graph[cur][i]!=0 && visited[i]==0)
				dfsRecur(i);
	}
	
	// back tracking ���� �ʰ� �ٷ� ���. back tracking ������ ���� ������ ��������� ������ �޸� �ʿ�
	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int popData=0;
		q.add(start);
		visited[start]=1;
		
		while(!q.isEmpty()) {
			popData=q.poll();
			
			System.out.print(popData+" ");
			for(int i=1; i<=n; i++) {
				if(graph[popData][i]!=0 && visited[i]==0) {
					q.add(i);
					visited[i]=1;
				}
			}
		}
	}
}

/* non_recursive
// back tracking ���� �ʰ� �ٷ� ���. back tracking ������ ���� ������ ��������� ������ �޸� �ʿ�
	public static void dfs() {
		Stack<Integer> st = new Stack<Integer>();
		int popData=0;
		st.push(start);
	
		while(!st.isEmpty()) {
			popData=st.pop();
			
			// push�� �� visited�� üũ�� ���� ������
			// ������ ��쿡,
			// 1�� 2,4,3�� ���� ������ �����̿��� push�� �� üũ�� �ȴ�.
			// ������ 4�� ���, 1�� �����ϱ⵵ ������ 2�͵� �����ϱ� ������
			// 1������ �����ϴ� ���� �ƴ϶� 2 ���� �����ؾ� �Ѵ�. (dfs�̱� ������)
			// �׷��� push �� �� üũ�ع����� 1 2 4 3 ���� �湮�ϴ� ���� �ƴ϶� 1 2 3 4 �� �Ǿ� ������.
			// ���� dfs������ pop�� �� visited�� �ٲپ��ְ�,
			// bfs�� enqueue�� �� visited�� �ٲپ� �ָ� �� ��
			if(visited[popData]==0)
				visited[popData]=1;
			else
				continue;
			
			System.out.print(popData+" ");
			// ������ ������ ���� ���� ���, �� ���� ���� �������� �湮
			// dfs�� ��� ���� �������� �湮�Ϸ���, pop�� �Ǵ� ���� �� ���� ���̾�� �ϹǷ� ū ���ں��� �˻��Ѵ�.
			for(int i=n; i>=1; i--) {
				if(graph[popData][i]!=0 && visited[i]==0)
					st.push(i);
			}
		}
		System.out.println();
	}


*/