/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 12.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package success.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ������ ����� �ڵ�
public class Main{
    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int cnt=Integer.parseInt(br.readLine());
        int [] nums=new int[cnt];
        String str=br.readLine();
        StringTokenizer st=new StringTokenizer(str);
        for(int i=0;i<cnt;++i){
        	nums[i]=Integer.parseInt(st.nextToken());
        }
        System.out.println(fastMaxSum(nums));
    }  
    //O(N^3)
    public static int findMaxSum(int[] ary){
   	int N=ary.length,ret=Integer.MIN_VALUE;
    	for(int i=0;i<N;++i){
    		for(int j=i;j<N;++j){
    			int sum=0;
    			for(int k=i;k<=j;++k){
    				sum+=ary[k];
    			}
    			ret=ret>sum?ret:sum;
    		}
    	}
    	return ret;
    }
    //O(N^2)
    public static int betterMaxSum(int[] ary){
    	int N=ary.length,ret=Integer.MIN_VALUE;
    	
    	for(int i=0;i<N;++i){
    		int sum=0;
    		for(int j=i;j<N;++j){
    			sum+=ary[j];
    			ret=ret>sum?ret:sum;
    		}
    	}
    	
    	return ret;
    }
    //O(NlgN)
    public static int fastMaxSum(int[] ary, int lo, int hi){
    	//������� : ������ ���̰� 1�� ���
    	if(lo==hi)
    		return ary[lo];
    	
    	//�迭�� lo~mid / mid+1 ~ hi�� ������.
    	int mid=(lo+hi)/2;
    	
    	//�� �κп� ��� ���� �ִ� �ִ� �� ������ ã�´�.
    	int left=Integer.MIN_VALUE,right=Integer.MIN_VALUE,sum=0;
    	for(int i=mid;i>=lo;--i){
    		sum+=ary[i];
    		left=left>sum?left:sum;
    	}
    	
    	sum=0;
    	for(int j=mid+1;j<=hi;++j){
    		sum+=ary[j];
    		right=right>sum?right:sum;
    	}
    	int first=fastMaxSum(ary, lo, mid);
    	int second=fastMaxSum(ary, mid+1, hi);
    	int single = first>second?first:second;
    	return left+right>single?left+right:single;
    }
    //O(N)
    public static int fastMaxSum(int[] ary){
    	int N=ary.length,ret=Integer.MIN_VALUE,pSum=0;
    	for(int i=0;i<N;++i){
    		pSum=(pSum>0?pSum:0)+ary[i];
    		ret=pSum>ret?pSum:ret;
    	}
    	return ret;
    }
}