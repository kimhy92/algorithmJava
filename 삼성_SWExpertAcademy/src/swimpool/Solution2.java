/**
 * @ClassName : Solution2
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 17.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package swimpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// �� �����ϰ� Ǭ �� (���� ������ Ǭ �ͺ��� �ξ� ����)
public class Solution2 {
	static int[] fee=new int[4];		// 1�� �̿��, 1�� �̿��, 3�� �̿��, 1�� �̿���� ���
	static int[] usePlan=new int[12];	// �̿� ��ȹ (���� �̿��ϼ�)
	static int min;	// �̿���� �ִ밡���� 3õ���̰�, ���� �ִ� �̿밡���� 1�� ��� �̿��ϸ鼭 ��� 1�� �̿������ �̿��ϴ�
						// 3000*365�̴�. ���� �ִ밪���� ū ���� �����ϱ� ���� *2 ����
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc; i++) {
			min=3000*365*2;
			String[] s=br.readLine().split(" ");
			for(int j=0; j<fee.length; j++)
				fee[j]=Integer.parseInt(s[j]);
			s=br.readLine().split(" ");
			for(int j=0; j<usePlan.length; j++) {
				usePlan[j]=Integer.parseInt(s[j]);;
			}
			min=fee[3];	// 1�� �̿� ����� ���� ��
			solve(0,0);
			System.out.println("#"+i+" "+min);
		}
	}
	
	/*
	month : ����� �� (12�������� ����ؾ���)
	sum : ��������� ��� ��
	 */
	public static void solve(int month, int sum) {
		if(month>=12) {
			min=Math.min(min, sum);
			return;
		}
		
		if(usePlan[month]==0) {	// ���� �޿� �̿��ϼ��� 0���̸� ����� ������ �ʴ´�.
			solve(month+1,sum);
		}
		else {
			solve(month+1,sum+usePlan[month]*fee[0]);	// 1�� �̿��
			solve(month+1,sum+fee[1]);	// 1�� �̿��
			solve(month+3,sum+fee[2]);
		}
	}
}
