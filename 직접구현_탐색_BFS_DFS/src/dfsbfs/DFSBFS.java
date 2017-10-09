/**
 * @ClassName : DFS
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 8.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package dfsbfs;

public class DFSBFS {
	static int[] visited=new int[1<<10];	//1024��
	static int n=100;
	static int[][] graph=new int[n][n];	// ���� ��� �׷���
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// stack���ε� ���� �����ѵ� �� �̻����� (������ s�� ������ ���� a, b�� ���� �� a�κ��� dfs�� �ߴµ� ,
	// s-a-c-f-b �� ����Ǵ� sub tree�� �ִٰ� ġ��. b�� dfs�� ���ؼ� f�� ����� �ɷ� Ž���� ��������
	// b�� �������� s�͵� �����ϴ�.
	// �� �� stack���� �ϸ� push�� �� visited�� 1�� �ϴµ� �׷��� s-a-c-f-b�� �ƴ϶� s-a-c-f�� Ž���ϰ� �ȴ�.
	// ��ͷ� �ϸ� s-a-c-f-b�� Ž���ȴ�.
	public static void dfs(int cur){ 
		visited[cur]=1;
		System.out.print(cur+ " ");
		for(int i=0; i<n; i++) {
			if(graph[cur][i]==1 && visited[i]==0) {
				dfs(i);
				System.out.println();	// ���ο� ����׷��� Ž���� �ٹٲ�
			}
		}
	} 
	
	// bfs�� queue�� ����
	
}
