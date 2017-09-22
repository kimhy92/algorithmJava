/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� ����� �� ��
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(solve(n));
	}
	
	public static int solve(int n) {
		if(n==reverseNum(n))	// �Է¹��� ���ڰ� �縰��� �ѹ��� ��� �� ���� �״�� ����
			return n;
		
		for(int i=0; i<3; i++) {	// 3������ �����ؼ� �縰��� �ѹ��� ���;� �Ѵ�.
			// F(n) ���. ���� ���ڿ� ���� F(n) ���
			n+=reverseNum(n);
			
			if(n==reverseNum(n))	// �縰��� �ѹ��̸� �� ���� ����
				return n;
			
			if(n>=10000)	// ���� �������� ����� 10000�̻��� �Ǹ� �Ұ���
				return -1;
		}
		
		return -1;	// ���� �� ���� ���
	}
	
	// Integer�� reverse �޼ҵ� ���� �Ŵ� ã�ƺ���
	public static int reverseNum(int num) {
		String res="";
		
		while(num!=0) {
			res += num%10;
			num/=10;
		}
		
		return Integer.parseInt(res);
	}
}















