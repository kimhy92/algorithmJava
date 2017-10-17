/**
 * @ClassName : Solve
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package d.one;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=Integer.parseInt(sc.nextLine());
		for(int i=1; i<=t; i++) {
			int sum=0;

			for(int j=0; j<10; j++) {
				int num=sc.nextInt();
				if(num%2==1)
					sum+=num;
			}
			System.out.println("#"+i+" "+sum);
		}
		sc.close();
	}
}
