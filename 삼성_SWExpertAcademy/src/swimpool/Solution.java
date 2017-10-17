/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 17.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package swimpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int[] fee=new int[4];		// 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권의 요금
	static int[] usePlan=new int[12];	// 이용 계획 (월별 이용일수)
	static int[] tickets=new int[12];	// 각 월별로 어떤 티켓이 사용되었는지 저장할 배열
										// 1:1일 이용권, 2:1달 이용권, 3:3달 이용권
	static int min=3000*365*2;	// 이용권의 최대가격은 3천원이고, 따라서 최대 이용가격은 1년 모두 이용하면서 모두 1일 이용권으로 이용하는
						// 3000*365이다. 따라서 최대값보다 큰 수를 선언하기 위해 *2 선언
	/*
	 * 첫 번째 접근 : Greedy로 가능한가?
	최소 요금으로 모든 걸 계산해본다. -> 최적해 x
	하루하루 끊어서 생각? -> 아닌듯
	Greedy는 아닌 것 같다.
	
	 * 두 번째 접근 : 완전탐색
	요금을 낼 수 있는 모든 경우의 조합을 검사해서, 그 중 최소 요금을 찾는다. -> 최적해 보장
	
	 * 세 번째 접근 : 완전탐색
	한 달을 기준으로, 며칠 이상은 어떤 조합으로 해야 최적인지 구한다. 이렇게 다 구해서
	12달의 이용 계획 배열을 검사해서 답을 구한다.
	이 방법도 결국 완전탐색임. 모든 조합 검사 필요
	
	나의 문제 재정의
	- 기간의 합이 12를 초과하지 않는 모든 부분집합의 경우에 대해서 요금을 게산하고 최소값을 구한다.
	- 부분 집합 문제에서 추가로 생각할 것
		1달 이용권을 1개 쓰더라도 어떤 달에 쓰느냐에 따라 결과가 달라지는 것에 유의한다.
		3달 이용권은 '연속된 달'에 해당함
	
	내가 풀 때 유의했던 것
	- 이용일수가 0일인 달도 제외하고 계산하면 안 된다. (3달 이용권 때문)
	- 11월, 12월에도 3달 이용권 끊을 수 있다.
	- 하지만 1년 이용권은 매년 1월 1일에 시작한다고 적혀있기 때문에, 도중에 1년 이용권을 쓰는 것은 고려하지 않아도 된다.
		즉, 매 달 1일 이용권, 1달 이용권, 3달 이용권 중 어떻게 조합하면 될지 고려해서 최소값 구하고, 마지막에 1년 이용권과 비교하면 된다.
	- 일단 부분집합으로 가능한 모든 경우의 수는 3^12 인 것으로 생각된다. 하지만, 기간의 합이 12를 초과하면 계산하지 않아도 된다는 걸 이용한다.
		-> 이 때, 1일 이용권을 쓰는 달도 1달로 취급한다. 1일 이용권과 1달 이용권은 요금만 다른 것이지 해당 월에 어떤 이용권을 적용할 것인가의
		단위는 1달이기 때문에 기간 계산시에는 동일하게 취급한다. 단, 요금 계산을 다르게 해야 한다.
	- 3달 이용권을 쓸 때에, '연속된 달' 에 해당한다는 것에 유의한다.
	- 요금 계산시, 3달 이용권은 한 번만 계산해야 하는 것에 유의한다.
	- 요금 게산시, 1달 이용권,3달 이용권은 이용일수가 1일 이상일 때에만 더한다.
	- 메모제이션 적용??
	   -> 1달 이용권 1개 쓰고, 나머지 달은 모두 1일 이용권을 쓴다고 하더라도, 각 달의 이용일수가 다르기 때문에 모두 다른 경우로 취급된다.
	   		따라서 최초에는 메모제이션을 적용하지 않고 풀어본다.?
	   		가능한 모든 경우의 수에는 이 모든 경우가 포함되어 있다.
	- tickets[] 배열은 항상 복구 해 주어야 한다. 재귀 호출에 의해 모든 배열의 값이 바뀌기 때문
	*/
	
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
				usePlan[j]=Integer.parseInt(s[j]);
				tickets[j]=0;
			}
			System.out.println("#"+i+" "+solve());
		}
	}
	
	public static int solve() {
		return Math.min(getFee(1), fee[3]);	// 1년 이용권과 비교해서 더 작은 값을 리턴
	}
	
	/*
	term : 현재 체크하고 있는 월. 13이 되면 요금을 계산하고 리턴 (12월까지이므로)
	-> 이 문제는 최적값만 딱 구하면 되니까 전달인자에 지금까지 계산한 요금을 넘겨주면,
	문제를 매우 훨씬 간단하게 풀 수 있다.
	이걸 안 넘겨줘서 나는 배열을 복사해서 저장하고 복구하고 다시 호출하고 이렇게 했는데, 최적값만 구하면 되면
	지금까지의 요금 넘겨주면 문제를 더 간단하게 풀 수 있다.(Solution2 참조)
	단, 이 때 매개변수가 배열이라면 call by reference 이기 때문에 복구해 주어야 하는 것에 유의하기
	복사해가는 일반 변수일 때만 가능
	 */
	public static int getFee(int term) {
		if(term>=13) {
			int res=0;
			// tickets 배열을 이용해서 요금 계산하고 리턴
			for(int i=0; i<usePlan.length; i++) {
				if(tickets[i]==1 && usePlan[i]>=1) {		// 1일 이용권-> 해당월의 이용일수 * 1일 이용권 요금
					res+=(fee[0]*usePlan[i]);
				} else if(tickets[i]==2 && usePlan[i]>=1) {	// 1달 이용권
					res+=fee[1];
				} else if(tickets[i]==3) {	// 3달 이용권. 3달 이용권은 한번만 카운트해야함
					boolean isUse=false;
					// 현재 달을 포함해서 3달 동안 이용일수가 1일이라도 넘는게 한 달이라도 있는지 체크
					for(int j=i; j<i+3 && j<tickets.length; j++)
						if(usePlan[j]>=1)
							isUse=true;
					
					// 한 번이라도 이용할 경우에만 요금 추가
					if(isUse)
						res+=fee[2];
					
					// 더해지거나 더해지지 않았으므로 -1로 선언해서 중복연산되지 않도록 한다.
					for(int j=i; j<i+3 && j<tickets.length; j++)
						tickets[j]=-1;
				}
			}
			return res;
		}
		
		// 기존의 이용권 현황 복사
		int[] tmpTickets=new int[tickets.length];
		cloneAry(tmpTickets,tickets);
		
		// 1일 이용권 이용한다고 가정하고 다음 것 호출. 어차피 뒤에서 덮어쓰기 때문에 복구할 필요 없음? X!! 그 뒤의 값들도 재귀 호출에 의해 변경되니까 다시 복구해 주어야 함
		tickets[term-1]=1;
		min=Math.min(min, getFee(term+1));
		cloneAry(tickets,tmpTickets);
		
		// 1달 이용권 이용한다고 가정하고 다음 것 호출.
		tickets[term-1]=2;
		min=Math.min(min, getFee(term+1));
		cloneAry(tickets,tmpTickets);

		// 3달 이용권 이용한다고 가정하고 다음 것 호출하고 복구.
		for(int i=term-1; i<term+2 && i<tickets.length; i++)
			tickets[i]=3;
		min=Math.min(min, getFee(term+3));
		cloneAry(tickets,tmpTickets);
		
		return min;
	}
	
	// arr1에 arr2를 복사
	public static void cloneAry(int[] arr1, int[] arr2) { 
		for(int i=0; i<arr1.length; i++)
			arr1[i]=arr2[i];
	}
}





