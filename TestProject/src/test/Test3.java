/**
 * @ClassName : Test3
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package test;

public class Test3 {
	static boolean[][] test;
	public static void main(String[] args) {
		for(int i=0; i<3; i++) {
			test=new boolean[4][4];
			
			for(int j=0; j<4; j++) {
				for(int k=0; k<4 ;k++)
					System.out.print(test[j][k]);
				System.out.println();
			}
			System.out.println("===================");
		}

	}

}
