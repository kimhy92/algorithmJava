/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package baekjoon.algo;

/*  -> ���� �´� �ڵ��ε�, �ð��ʰ�����. �� ������?  ->  �湮���� ��, visit[cur] �� true�� �ٲ���� �ϴµ� �װ� ������.. �̷� �˸�û��
 *  ���� �ۼ��� �ڵ�� �������δ� �´� �� ������, �ð��ʰ� ������ �𸣰ڴ�.
 *  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1697 ���� ������,
// ����� �� ��θ� ���� ����ؾ� �Ѵ�.
// ��� ��쿡 ���ؼ� �� ��θ� ��� �����ϵ��� �ߴ�. (�޸𸮸� ��ȿ�������� �������)

public class Main {	
	private static int[] visit = new int[100001];	// �湮���� ���� ���� -1, �湮�� ���� ���� �ڽ��� ������ �� index�� �ǹ�
	
	public static void main(String[] args) throws IOException {
		int subin=0;
		int sister=0;
		Arrays.fill(visit, -1);	// �迭 ���� ��� -1�� �ʱ�ȭ
		
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
		visit[subin]=subin;	//�湮 �Ͽ����� üũ
		int[] check={-1,1,0};
		int tmpX=0;
		
		// ã�� ������ �ݺ�
		while(!q.isEmpty()) {
			cur=q.poll();
			if(cur.x==sister)	// �о ������ ã������ break�ϰ�, ������� �� �� �ɷȴ��� ��ȯ
				break;
			
			// visit[]�� ���� -1�̸� �湮���� ���� ��.
			// index�� 0�� �� -1�ϸ� index���� ���ϱ� üũ. �ִ밪�� üũ			
			for(int i=0; i<check.length; i++) {
				tmpX=0;
				if(i==2) tmpX=cur.x*2;
				else tmpX=cur.x+check[i];
				
				if(tmpX>100000 || tmpX<0) continue;	// ���� �̿��� ���̸� continue
				if(visit[tmpX]!=-1) continue;	// �̹� �湮�� ���θ� continue
				
				q.add(new XDegree(tmpX, cur.d+1));
				visit[tmpX]=cur.x;
			}
		}
		
		return cur;
	}
	
	private static class XDegree {
		private int x;		// ��ġ (x��ǥ)
		private int d;		// ���� ��ġ���� �ɸ� �ð�(�׷��������� ������ �����ؼ� degree�� �ߴµ� time�� t�� �� ������ ��)
		
		public XDegree(int x, int d) {
			this.x=x;
			this.d=d;
		}
	}
}


// �� �ڵ�� ���ͳݿ� �ִ� �ڵ��ε�, ������ �ٸ��� visit �迭 �ϳ��� �� �ذ��ߴ�. (nested class ����). visit �迭��
// 0�� �湮���� ���� �Ű� �湮�ϸ� �������� �ɸ��ð� +1�� �־�����, ���� ���������� 1�̶�� �ߴ�.(���� �������� �ð��� �� �������� 0����)
// �׷��� ��ȯ�� �� visit[cur]-1 �� ��ȯ�ߴ�.
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
		// ã�� ������ �ݺ�
		while(!q.isEmpty()) {
			cur=q.poll();
			
			if(cur==sister)
				break;
			
			// ����ġ��. ������ �湮���� ���� ��쿡�� �߰��Ѵ�
			// index�� 0�� �� -1�ϸ� index���� ���ϱ� üũ. �ִ밪�� üũ
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


��ó: http://spillmoon.tistory.com/180 [������ �ϴ� ��α�]
*/
