/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 28.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 무방향 그래프가 주어졌을 때, 연결그래프가 몇 개인지 구한다.
// dfs 사용. 비트마스크는 n의 범위가 1000까지라서사용할 수 없음
// 비트마스크 -> 정수형 변수에 비트 연산을 활용하여 방문여부등을 체크하는 방식. 즉, 비트단위로 연산에 사용하는 방식
public class Main {
	static int n, m;	// n:정점 개수, m:간선 개수
	static int[][] graph;
	static int[] visited;
	int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		graph = new int[n+1][n+1];	// index 1부터 n까지 사용
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
			if(visited[i]==0) {	// 방문하지 않은 경우
				stack.push(i);
				visited[i]=1;
				cnt++;
				
				// dfs 탐색 실시
				while(!stack.isEmpty()) {
					popData=stack.pop();
					for(int j=1; j<=n; j++) {
						if(graph[popData][j]!=0 && visited[j]==0 ) {	// 스택에서 pop한 정점과 j정점사이의 간선이 있고, j정점을 방문하지 않았다면
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
