/**
 * @ClassName : Solve
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package d.two;

import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		int t=Integer.parseInt(sc.nextLine());
		System.out.println("\nt : " + t);
		int n;
		int[] ary;
		for(int x=0; x<t; x++) {
			System.out.println("x : " + x);
			n=Integer.parseInt(sc.nextLine());
			ary=new int[n];
			
			for(int i=0; i<n; i++)
				ary[i]=sc.nextInt();
			sc.nextLine();
			
			System.out.println(n);
			for(int i=0; i<n; i++)
				System.out.print(ary[i]+" ");

			System.out.println("\n==========");
		}
		
		
	}
}
