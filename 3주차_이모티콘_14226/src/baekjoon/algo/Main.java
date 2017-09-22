/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package baekjoon.algo;

/*  -> 내가 냈던 코드인데, 시간초과였다. 그 이유는?  ->  방문했을 때, visit[cur] 를 true로 바꿔줘야 하는데 그걸 안했음.. 이런 똥멍청이
 *  내가 작성한 코드는 논리적으로는 맞는 것 같은데, 시간초과 이유를 모르겠다.
 *  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 13549 번과 같은 맥락이다. BFS로 탐색하되, 시간이 전부 1초 증가가 아니라, 2초 증가하는 것도 있기 때문에 (degree 변하는 정도가 다르기 때문에)
// 우선순위 큐를 사용할 것이다.
// 1번 연산 -> 클립보드에 있는 개수만 변하고, 실제 개수에는 변화 없다. 무조건 화면에 있는 것을 모두 복사한다. 
// 2번 연산 -> 기존 개수 + 클립 보드 개수. 클립 보드 개수가 0개이면 붙여넣기 불가능
//			=====주의할 것은, 클립 보드의 개수이다. bfs를 진행하면서 클립 보드의 값도 바뀔 텐데, 내가 연산하는 상태에서의 클립보드에 저장된 이모티콘 개수로 고려해야 한다.
// 3번 연산 -> -1개

// 3가지 경우로 나눌 수 있다.
// 화면 개수 + 클립 보드 개수 (클립 보드 개수 != 화면 개수 인 경우) : +1초
// 화면 개수 * 2 (클립 보드에 복사한 후에 화면에 넣어야 하므로 +2초) 
// 화면 개수 -1 : +1초

public class Main {
	private static int[][] visit = new int[2001][2001]; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s=Integer.parseInt(br.readLine());
		System.out.println(solve(s));
	}
	
	public static int solve(int s) {
		Queue<XDegree> q = new LinkedList<XDegree>();
		q.add(new XDegree(1,0,0));
		XDegree cur=null;
		visit[1][0]=1;
		
		
		// 찾을 때까지 반복
		while(!q.isEmpty()) {
			cur=q.poll();
			
			System.out.println(cur.emoCnt);
			if(cur.emoCnt==s)
				break;
			
			// 같은 개수여도 클립 보드 내용이 다를 수가 있다.
						
			// 1. 클립 보드에 복사 (전체 개수는 동일하지만, 시간 1초 증가 되며 클립 보드의 개수가 현재 이모티콘 개수로 바뀐다.)
			if(visit[cur.emoCnt][cur.clipCnt]!=1)
				q.add(new XDegree(cur.emoCnt,cur.time+1, cur.emoCnt));	// 복사했으므로 클립보드에도 현재 이모티콘 개수가 들어감
			
			visit[cur.emoCnt][cur.clipCnt]=1;
			
			// 2. 화면 개수 + 클립 보드 개수
			if(cur.clipCnt!=0 && cur.emoCnt<s && cur.emoCnt+cur.clipCnt<=2000 && visit[cur.emoCnt+cur.clipCnt][cur.clipCnt]!=1)
				q.add(new XDegree(cur.emoCnt+cur.clipCnt,cur.time+1, cur.clipCnt)); // 클립 보드는 유지
			
			// 3. 화면 개수 -1
			if(cur.emoCnt-1>=0 && visit[cur.emoCnt-1][cur.clipCnt]!=1)
				q.add(new XDegree(cur.emoCnt-1,cur.time+1, cur.clipCnt)); // 클립 보드는 유지
			
		}
		
		return cur.time;
	}
	
	private static class XDegree {
		private int emoCnt;
		private int time;
		private int clipCnt;
		
		public XDegree(int emoCnt, int time, int clipCnt) {
			this.emoCnt=emoCnt;
			this.time=time;
			this.clipCnt=clipCnt;
		}
	}
}
