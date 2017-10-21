/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 14.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package fail.carcenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 창구
class Desk {
	int time;			// 처리시간
	boolean isEmpty;	// 비어 있는지 여부
	int no;				// 창구번호
	Queue<Integer> wait=new LinkedList<Integer>();
	public Desk(int time, boolean isEmpty, int no) {
		super();
		this.time = time;
		this.isEmpty = isEmpty;
		this.no = no;
	}
}
// 고객
class Customer{
	int no;	// 고객번호
	int a;	// 이용한 접수 창구
	int b;	// 이용한 정비 창구
	public Customer(int no, int a, int b) {
		super();
		this.no = no;
		this.a = a;
		this.b = b;
	}
}



// 2477. 차량 정비소
public class Solution {
	static int tcCnt;	// 테스트 케이스 개수
	/*
	 n : 접수 창구 개수
	 m : 정비 창구 개수
	 k : 차량 정비소를 방문한 고객의 수
	 ai : 지갑을 두고 간 고객이 이용한 접수 창구 번호
	 bi : 지갑을 두고 간 고객이 이용한 정비 창구 번호
	 */
	static int n,m,k,ai,bi;
	/*
	a : 각 접수창구의 처리시간 a[i]는 i번째 접수창구의 처리시간
	b : 각 정비창구의 처리시간 b[i]는 i번째 정비창구의 처리시간
	t : 고객들의 도착시간. t[i]는 i번째 고객의 도착시간
	 */
	static Desk[] a,b;
	static int[] t;
	static int sum;
	static boolean isNone;	// 문제 조건과 일치하는 고객이 없으면 true, 있으면 false
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		tcCnt=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tcCnt; i++) {
			sum=0;
			isNone=true;
			StringTokenizer st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			ai=Integer.parseInt(st.nextToken());
			bi=Integer.parseInt(st.nextToken());
			
			a=new Desk[n+1];
			b=new Desk[m+1];
			t=new int[k+1];
			
			StringTokenizer st2=new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++)	// 접수 창구
				a[j]=new Desk(Integer.parseInt(st2.nextToken()),true,j);
			StringTokenizer st3=new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++)	// 정비 창구
				b[j]=new Desk(Integer.parseInt(st3.nextToken()),true,j);
			StringTokenizer st4=new StringTokenizer(br.readLine());
			for(int j=1; j<=k; j++)	// 고객들의 도착시간
				t[j]=Integer.parseInt(st4.nextToken());
			
			// input 값 확인
			for(int j=1; j<=n; j++)
				System.out.print(a[j].time+" ");
			System.out.println();
			
			for(int j=1; j<=m; j++)
				System.out.print(b[j].time+" ");
			System.out.println();
			
			for(int j=1; j<=k; j++)
				System.out.print(t[j]+" ");
			System.out.println();
			
			// ====================solve====================
			int time=0;	// 걸린 시간
			Customer[] cus=new Customer[k+1];
			for(int j=1; j<=k; j++)
				cus[j]=new Customer(j,0,0);	// 고객번호,이용한 접수창구번호, 이용한 정비창구번호
			while(true) {
				for(int j=1; j<=k; j++) {	// 모든 고객들의 이용한 접수창구번호를 구한다.
					for(int x=1; x<=n; x++) {
						if(a[x].isEmpty)
							cus[j].no=a[x].no;
					}
					if(cus[j].no==0) {	// 비어 있는 접수창구가 없는 경우,
												
					}
				}
				
				time++;
				break;
			}
			
			
			// ====================output====================
			System.out.print("#"+i+" ");
			if(isNone)
				System.out.println(-1);
			else
				System.out.println(sum);
			System.out.println("===========");

		}
	}

}
