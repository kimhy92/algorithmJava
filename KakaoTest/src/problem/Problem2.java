/**
 * @ClassName : Problem2
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package problem;

public class Problem2 {
	public static void main(String[] args) {
		System.out.println(solution("1S2D*3T"));
		System.out.println(solution("1D2S#10S"));
		System.out.println(solution("1D2S0T"));
	}
	
	// ���Ǵ� ���� -> +, *, -, ^(������. 2����, 3����)
	public static int solution(String dartResult) {
	      int answer = 0;
	      
	      char subChar=0;
	      int[] score = {0,0,0};
	      int scoreIndex=0;
	      
	      for(int i=0; i<dartResult.length(); i++) {
	    	  subChar=dartResult.charAt(i);
	    	  
	    	  
	    	  // 1. subChar�� 0~10�� ������ ���
	    	  if(subChar>=48 && subChar<=57) {
	    		  if(dartResult.charAt(i+1)=='0') {		// 10�� ���
	    			  score[scoreIndex]=10;
	    			  i++;
	    			  continue;
	    		  }
	    		  score[scoreIndex]=subChar-48;	// 10�� �ƴϸ� 0~9�� ������ score�� ����
	    	  }
	    	  // 2. subChar�� S, D, T �� ���
	    	  else if(subChar=='S' || subChar=='D' || subChar=='T') {
	    		  
	    		  // Single�� ��� 1�����̹Ƿ� ������ �ʿ� ����.
	    		  if(subChar=='D') {
	    			  // 2����
	    			  score[scoreIndex]*=score[scoreIndex];
	    		  }
	    		  else if(subChar=='T') {
	    			  // 3����
	    			  score[scoreIndex]*=score[scoreIndex]*score[scoreIndex];
	    		  }
	    		  
	    		  scoreIndex++;
	    	  }
	    	  // 3. subChar�� *, #�� ���
	    	  else if(subChar=='*' || subChar=='#') {
	    		  scoreIndex--;
	    		  if(subChar=='*') {
	    			  if(scoreIndex==0)
	    				  score[scoreIndex]*=2;
	    			  else {
	    				  score[scoreIndex]*=2;
	    				  score[scoreIndex-1]*=2;
	    			  }
	    		  }
	    		  else if(subChar=='#') {
	    			  score[scoreIndex]*=-1;
	    		  }
	    		  scoreIndex++;
	    	  }
	      }
	      
	      for(int tmp : score)
	    	  answer+=tmp;
	      
	      return answer;
	  }
}
