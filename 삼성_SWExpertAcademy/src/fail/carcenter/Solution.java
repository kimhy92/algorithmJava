/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 14.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package fail.carcenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// â��
class Desk {
	int time;			// ó���ð�
	boolean isEmpty;	// ��� �ִ��� ����
	int no;				// â����ȣ
	Queue<Integer> wait=new LinkedList<Integer>();
	public Desk(int time, boolean isEmpty, int no) {
		super();
		this.time = time;
		this.isEmpty = isEmpty;
		this.no = no;
	}
}
// ��
class Customer{
	int no;	// ����ȣ
	int a;	// �̿��� ���� â��
	int b;	// �̿��� ���� â��
	public Customer(int no, int a, int b) {
		super();
		this.no = no;
		this.a = a;
		this.b = b;
	}
}



// 2477. ���� �����
public class Solution {
	static int tcCnt;	// �׽�Ʈ ���̽� ����
	/*
	 n : ���� â�� ����
	 m : ���� â�� ����
	 k : ���� ����Ҹ� �湮�� ���� ��
	 ai : ������ �ΰ� �� ���� �̿��� ���� â�� ��ȣ
	 bi : ������ �ΰ� �� ���� �̿��� ���� â�� ��ȣ
	 */
	static int n,m,k,ai,bi;
	/*
	a : �� ����â���� ó���ð� a[i]�� i��° ����â���� ó���ð�
	b : �� ����â���� ó���ð� b[i]�� i��° ����â���� ó���ð�
	t : ������ �����ð�. t[i]�� i��° ���� �����ð�
	 */
	static Desk[] a,b;
	static int[] t;
	static int sum;
	static boolean isNone;	// ���� ���ǰ� ��ġ�ϴ� ���� ������ true, ������ false
	
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
			for(int j=1; j<=n; j++)	// ���� â��
				a[j]=new Desk(Integer.parseInt(st2.nextToken()),true,j);
			StringTokenizer st3=new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++)	// ���� â��
				b[j]=new Desk(Integer.parseInt(st3.nextToken()),true,j);
			StringTokenizer st4=new StringTokenizer(br.readLine());
			for(int j=1; j<=k; j++)	// ������ �����ð�
				t[j]=Integer.parseInt(st4.nextToken());
			
			// input �� Ȯ��
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
			int time=0;	// �ɸ� �ð�
			Customer[] cus=new Customer[k+1];
			for(int j=1; j<=k; j++)
				cus[j]=new Customer(j,0,0);	// ����ȣ,�̿��� ����â����ȣ, �̿��� ����â����ȣ
			while(true) {
				for(int j=1; j<=k; j++) {	// ��� ������ �̿��� ����â����ȣ�� ���Ѵ�.
					for(int x=1; x<=n; x++) {
						if(a[x].isEmpty)
							cus[j].no=a[x].no;
					}
					if(cus[j].no==0) {	// ��� �ִ� ����â���� ���� ���,
												
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
