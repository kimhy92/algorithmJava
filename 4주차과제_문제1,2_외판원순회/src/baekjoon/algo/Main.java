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
import java.util.Arrays;
import java.util.StringTokenizer;

// 2098번, 10971번 문제. TSP (Traveling Salesman Problem) - 외판원 순회 문제. 기본
// 2098 : 시간제한 1초 / 메모리 128MB
// 10971 : 시간제한 2초 / 메모리 256MB
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
		if(visited== (1<<n)-1)	// 모두 방문한 경우, 현재 지점에서 시작지점으로 돌아가는 비용. 길이 없다면 IMPOSSIBLE을 리턴
			return graph[current][0]!=0?graph[current][0]:INFINITY;
		
		if(cache[current][visited]!=-1)
			return cache[current][visited];
		
		int res=INFINITY;
		
		for(int i=0; i<n; i++) {
			if((visited & (1<<i)) !=0 ) continue;	// 방문한 마을이면 continue
			if(graph[current][i] == 0 ) continue;	// 현재 마을에서 i마을로 가는 길이 없다면 continue
			
			// 현재 마을에서 i마을로 가는 길이 있는 경우, min( 나머지 마을을 방문하는 데 드는 최소 비용 중 현재까지의 최소값 , 나머지 마을을 방문하는 데 드는 최소 비용 )
			// 첫번째 parameter : 나머지 마을을 방문하는 데 드는 최소 비용들 중에서 현재까지 구한 최소값
			// 두번째 parameter : 나머지 마을을 방문하는 데 드는 최소 비용 (특정 마을 i로 갔을 때의 최소 비용을 의미)
			// 				   i마을을 시작점으로 남은 마을들을 모두 방문하고 0번 마을로 돌아가는 데 드는 최소 비용 + 현재에서 i마을까지 비용 )
			// tspRecur(i, visited|(1<<i)) -> i마을을 시작점으로, 나머지 마을을 모두 방문하고 0번 마을로 돌아가는 최소 비용. 이 때, visited | (1<<i)는, i도 방문했다고 표시하기 위함
			// graph[current][i]를 더해주는 이유는, tspRecur(i,~)가 i를 시작점으로 계산하기 때문에, 현재 마을에서 i마을로 가는 비용도 더해주는 것.
			res = Math.min(res, tsp(i, visited | (1<<i)) + graph[current][i]);
		}
		return res=cache[current][visited];
	}
}
