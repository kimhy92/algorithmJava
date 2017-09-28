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
import java.util.Stack;
import java.util.StringTokenizer;

// ������ �׷����� �־����� ��, ����׷����� �� ������ ���Ѵ�.
// dfs ���. ��Ʈ����ũ�� n�� ������ 1000�����󼭻���� �� ����
// ��Ʈ����ũ -> ������ ������ ��Ʈ ������ Ȱ���Ͽ� �湮���ε��� üũ�ϴ� ���. ��, ��Ʈ������ ���꿡 ����ϴ� ���
public class Main {
	static int n, m;	// n:���� ����, m:���� ����
	static int[][] graph;
	static int[] visited;
	int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		graph = new int[n+1][n+1];	// index 1���� n���� ���
		visited = new int[n+1];
		
		for(int i=0; i<m; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			graph[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())]=1;
		}
		
		System.out.println(solve());
	}
	
	public static int solve() {		
		int cnt=0;
		Stack<Integer> stack = new Stack<Integer>();
		int popData=0;
		
		for(int i=1; i<=n; i++) {
			if(visited[i]==0) {	// �湮���� ���� ���
				stack.push(i);
				visited[i]=1;
				cnt++;
				
				// dfs Ž�� �ǽ�
				while(!stack.isEmpty()) {
					popData=stack.pop();
					for(int j=1; j<=n; j++) {
						if(graph[popData][j]!=0 && visited[j]==0 ) {	// ���ÿ��� pop�� ������ j���������� ������ �ְ�, j������ �湮���� �ʾҴٸ�
							stack.push(j);
							visited[j]=1;
						}
					}
				}
			}
		}
		
		return cnt;
	}
}
