/**
 * @ClassName : Problem2
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package problem;

public class Problem2 {
	public static void main(String[] args) {
		System.out.println(solution("1S2D*3T"));
		System.out.println(solution("1D2S#10S"));
		System.out.println(solution("1D2S0T"));
	}
	
	// 사용되는 연산 -> +, *, -, ^(제곱수. 2제곱, 3제곱)
	public static int solution(String dartResult) {
	      int answer = 0;
	      
	      char subChar=0;
	      int[] score = {0,0,0};
	      int scoreIndex=0;
	      
	      for(int i=0; i<dartResult.length(); i++) {
	    	  subChar=dartResult.charAt(i);
	    	  
	    	  
	    	  // 1. subChar가 0~10의 숫자인 경우
	    	  if(subChar>=48 && subChar<=57) {
	    		  if(dartResult.charAt(i+1)=='0') {		// 10인 경우
	    			  score[scoreIndex]=10;
	    			  i++;
	    			  continue;
	    		  }
	    		  score[scoreIndex]=subChar-48;	// 10이 아니면 0~9의 점수를 score에 저장
	    	  }
	    	  // 2. subChar가 S, D, T 인 경우
	    	  else if(subChar=='S' || subChar=='D' || subChar=='T') {
	    		  
	    		  // Single인 경우 1제곱이므로 연산할 필요 없다.
	    		  if(subChar=='D') {
	    			  // 2제곱
	    			  score[scoreIndex]*=score[scoreIndex];
	    		  }
	    		  else if(subChar=='T') {
	    			  // 3제곱
	    			  score[scoreIndex]*=score[scoreIndex]*score[scoreIndex];
	    		  }
	    		  
	    		  scoreIndex++;
	    	  }
	    	  // 3. subChar가 *, #인 경우
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
