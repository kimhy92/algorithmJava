/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 초과 뜬다.
public class Main {	
	public static void main(String[] args) throws IOException {
		int subin=0;
		int sister=0;				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		
		int[] res=solve(subin,sister);
		for(int tmp : res)
			System.out.println(tmp);
	}
	
	public static int[] solve(int subin, int sister) {
		Queue<XDegree> q = new LinkedList<XDegree>();
		q.add(new XDegree(subin,0));
		XDegree cur=null;
		int[] res={0,0};
		
		// 찾을 때까지 반복
		while(!q.isEmpty()) {
			cur=q.poll();
			
			if(cur.x==sister) {
				res[0]=cur.d;
				
				XDegree tmp=null;
				while(!q.isEmpty()) {
					tmp=q.poll();
					if(tmp.x==sister)
						res[1]++;
				}
				break;
			}
			
			// 가지치기. 이전에 방문하지 않은 경우에만 추가한다
			// index가 0일 때 -1하면 index에러 나니까 체크. 최대값도 체크
			if(cur.x-1>=0)
				q.add(new XDegree(cur.x-1,cur.d+1));
			if(cur.x<sister && cur.x+1<=100000)
				q.add(new XDegree(cur.x+1,cur.d+1));
			if(cur.x<sister && cur.x*2<=100000)
				q.add(new XDegree(cur.x*2,cur.d+1));
		}
		
		return res;
	}
	
	private static class XDegree {
		private int x;
		private int d;
		public XDegree(int x, int d) {
			this.x=x;
			this.d=d;
		}
	}
}

/*
 * 아래 코드는 통과되는 코드. 잘 짠 듯.
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
