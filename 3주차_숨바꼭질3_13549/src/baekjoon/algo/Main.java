/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
// 1697 ������ ������, �켱���� ť�� ����ؼ�
// Ž�� ������ �������ָ� �ȴ�.
// �����̵��� �ð��� 1�� ��°� �ƴ϶� 0�� ��� ������, ������ ť�� �ٸ��� �ִ���
// �����̵��� �� �� ���� Ž���ؼ� bfs�� �����ϸ� �ȴ�.
// �켱���� ť ����, Comparable �������̽� �����ϸ� �ȴ�.
public class Main {
	private static boolean[] visit = new boolean[1000001]; 
	public static void main(String[] args) throws IOException {
		int subin=0;
		int sister=0;				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		
		System.out.println(solve(subin,sister));
	}
	
	public static int solve(int subin, int sister) {
		Queue<XDegree> q = new PriorityQueue<XDegree>();
		q.add(new XDegree(subin,0));
		XDegree cur=null;
		visit[subin]=true;

		
		// ã�� ������ �ݺ�
		while(!q.isEmpty()) {
			cur=q.poll();
			visit[cur.x]=true;
			
			if(cur.x==sister)
				break;
			
			// ����ġ��. ������ �湮���� ���� ��쿡�� �߰��Ѵ�
			// index�� 0�� �� -1�ϸ� index���� ���ϱ� üũ. �ִ밪�� üũ
			if(cur.x-1>=0 && !visit[cur.x-1])
				q.offer(new XDegree(cur.x-1,cur.d+1));
			if(cur.x<sister && cur.x+1<=100000 && !visit[cur.x+1])
				q.offer(new XDegree(cur.x+1,cur.d+1));
			if(cur.x<sister && cur.x*2<=100000 && !visit[cur.x*2])
				q.offer(new XDegree(cur.x*2,cur.d));
			
		}
		
		return cur.d;
	}
	
	private static class XDegree implements Comparable<XDegree> {
		private int x;
		private int d;
		public XDegree(int x, int d) {
			this.x=x;
			this.d=d;
		}
		
		public int compareTo(XDegree target) {
			if(this.d > target.d)
				return 1;
			else if(this.d < target.d)
				return -1;
			return 0;
		}
	}
}

/*
 * �Ʒ� �ڵ�� ����Ǵ� �ڵ�. �� § ��.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, table[], cnt = 0,total =0,temp[];
    static boolean check[];

    static Queue<Integer> que;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
         table = new int[100000+1];
        check = new boolean[100000+1];
        temp = new int[100000+1];
        que = new LinkedList<Integer>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        que.add(N);
        StringBuilder sb = new StringBuilder();

        if(M!=N){
            sb.append(BFS()).append("\n");
            sb.append(total);

        }
        else {
            sb.append(0).append("\n");
            sb.append(1);


        }
        System.out.println(sb);

    }
    public static int BFS() {
        while (!que.isEmpty()) {
            cnt++;
            int size = que.size();

            for (int j = 0; j < size; j++) {
                int idx = que.poll();
                int[] vec = {idx - 1, idx + 1, 2 * idx};
                for (int i = 0; i < vec.length; i++) {
                    if (vec[i] < 0 || vec[i] > 100000) continue;
                    if (!check[vec[i]]||temp[vec[i]]==cnt) {
                        check[vec[i]]=true;
                        temp[vec[i]]=cnt;
                        que.add(vec[i]);
                    }
                    if (vec[i] == M) {
                        total++;
                        break;
                    }
                }
            }
            if(total !=0){
                return cnt;
            }

        }
        return -1;
    }
}

*/
