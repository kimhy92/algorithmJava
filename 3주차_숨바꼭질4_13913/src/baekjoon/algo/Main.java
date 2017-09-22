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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1697 번과 같으나,
// 출력할 때 경로를 같이 출력해야 한다.
// 모든 경우에 대해서 그 경로를 모두 저장하도록 했다. (메모리를 비효율적으로 사용했음)

public class Main {	
	private static int[] visit = new int[100001];	// 방문하지 않은 곳은 -1, 방문한 곳의 값은 자신의 이전의 방 index를 의미
	
	public static void main(String[] args) throws IOException {
		int subin=0;
		int sister=0;
		Arrays.fill(visit, -1);	// 배열 값을 모두 -1로 초기화
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		
		XDegree res=solve(subin,sister);
		System.out.println(res.d);
		XDegree cur=res;
		String route="";
		while(true) {
			route = cur.x+" "+route;
			if(cur.x==subin)
				break;
			cur.x=visit[cur.x];
		}
		System.out.println(route);
	}
	
	public static XDegree solve(int subin, int sister) {
		Queue<XDegree> q = new LinkedList<XDegree>();
		q.add(new XDegree(subin,0));
		XDegree cur=null;
		visit[subin]=subin;	//방문 하였으니 체크
		int[] check={-1,1,0};
		int tmpX=0;
		
		// 찾을 때까지 반복
		while(!q.isEmpty()) {
			cur=q.poll();
			if(cur.x==sister)	// 읽어서 동생을 찾았으면 break하고, 현재까지 몇 초 걸렸는지 반환
				break;
			
			// visit[]의 값이 -1이면 방문하지 않은 것.
			// index가 0일 때 -1하면 index에러 나니까 체크. 최대값도 체크			
			for(int i=0; i<check.length; i++) {
				tmpX=0;
				if(i==2) tmpX=cur.x*2;
				else tmpX=cur.x+check[i];
				
				if(tmpX>100000 || tmpX<0) continue;	// 범위 이외의 값이면 continue
				if(visit[tmpX]!=-1) continue;	// 이미 방문한 곳인면 continue
				
				q.add(new XDegree(tmpX, cur.d+1));
				visit[tmpX]=cur.x;
			}
		}
		
		return cur;
	}
	
	private static class XDegree {
		private int x;		// 위치 (x좌표)
		private int d;		// 현재 위치까지 걸린 시간(그래프에서의 차수라 생각해서 degree라 했는데 time인 t가 더 적절할 듯)
		
		public XDegree(int x, int d) {
			this.x=x;
			this.d=d;
		}
	}
}


// 이 코드는 인터넷에 있는 코드인데, 나랑은 다르게 visit 배열 하나로 다 해결했다. (nested class 없이). visit 배열에
// 0은 방문하지 않은 거고 방문하면 기존까지 걸린시간 +1을 주었으며, 최초 시작지점을 1이라고 했다.(원래 시작점은 시간이 안 지났으니 0초임)
// 그래서 반환할 때 visit[cur]-1 로 반환했다.
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	private static int[] visit = new int[100001];
	public static void main(String[] args) throws IOException {
		int subin=0;
		int sister=0;				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		
		int res=solve(subin,sister);
		System.out.println(res);
	}
	
	public static int solve(int subin, int sister) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(subin);
		visit[subin]=1;
		
		int cur=0;
		// 찾을 때까지 반복
		while(!q.isEmpty()) {
			cur=q.poll();
			
			if(cur==sister)
				break;
			
			// 가지치기. 이전에 방문하지 않은 경우에만 추가한다
			// index가 0일 때 -1하면 index에러 나니까 체크. 최대값도 체크
			if(cur-1>=0 && visit[cur-1]==0) {
				q.add(cur-1);
				visit[cur-1]=visit[cur]+1;
			}
			if(cur<sister && cur+1<=100000 && visit[cur+1]==0) {
				q.add(cur+1);
				visit[cur+1]=visit[cur]+1;
			}
			if(cur<sister && cur*2<=100000 && visit[cur*2]==0) {
				q.add(cur*2);
				visit[cur*2]=visit[cur]+1;
			}
		}
		
		return visit[cur]-1;
	}
}*/

/*
public class BOJ1697 {
  static int N, K;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();
     
    move(N);
    sc.close();
  }
  public static void move(int X){
    Queue queue = new Queue(200001);
    int[] visit = new int[200001];
    visit[X] = 1;
    queue.push(X);
     
    while(!queue.isEmpty()){
      int x = queue.pop();
      if(x == K){
        System.out.println(visit[x]-1);
      } else {
        if(x-1 >= 0 && visit[x-1] == 0){
          visit[x-1] = visit[x] + 1;
          queue.push(x-1);
        }
        if(x+1 <= 100000 && visit[x+1] == 0){
          visit[x+1] = visit[x] + 1;
          queue.push(x+1);
        }
        if(x*2 <= 100000 && visit[x*2] == 0){
          visit[x*2] = visit[x] + 1;
          queue.push(x*2);
        }
      }
    }
  }
}
class Queue {
  private int front;
  private int rear;
  private int[] storage;
   
  public Queue(int size){
    front = -1;
    rear = -1;
    storage = new int[size];
  }
  public void push(int item){
    storage[++rear] = item;
  }
  public int pop(){
    return storage[++front];
  }
  public boolean isEmpty(){
    return front == rear;
  }
}


출처: http://spillmoon.tistory.com/180 [정리를 하는 블로그]
*/
