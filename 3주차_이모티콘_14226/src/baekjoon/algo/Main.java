/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package baekjoon.algo;

/*  -> ���� �´� �ڵ��ε�, �ð��ʰ�����. �� ������?  ->  �湮���� ��, visit[cur] �� true�� �ٲ���� �ϴµ� �װ� ������.. �̷� �˸�û��
 *  ���� �ۼ��� �ڵ�� �������δ� �´� �� ������, �ð��ʰ� ������ �𸣰ڴ�.
 *  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 13549 ���� ���� �ƶ��̴�. BFS�� Ž���ϵ�, �ð��� ���� 1�� ������ �ƴ϶�, 2�� �����ϴ� �͵� �ֱ� ������ (degree ���ϴ� ������ �ٸ��� ������)
// �켱���� ť�� ����� ���̴�.
// 1�� ���� -> Ŭ�����忡 �ִ� ������ ���ϰ�, ���� �������� ��ȭ ����. ������ ȭ�鿡 �ִ� ���� ��� �����Ѵ�. 
// 2�� ���� -> ���� ���� + Ŭ�� ���� ����. Ŭ�� ���� ������ 0���̸� �ٿ��ֱ� �Ұ���
//			=====������ ����, Ŭ�� ������ �����̴�. bfs�� �����ϸ鼭 Ŭ�� ������ ���� �ٲ� �ٵ�, ���� �����ϴ� ���¿����� Ŭ�����忡 ����� �̸�Ƽ�� ������ ����ؾ� �Ѵ�.
// 3�� ���� -> -1��

// 3���� ���� ���� �� �ִ�.
// ȭ�� ���� + Ŭ�� ���� ���� (Ŭ�� ���� ���� != ȭ�� ���� �� ���) : +1��
// ȭ�� ���� * 2 (Ŭ�� ���忡 ������ �Ŀ� ȭ�鿡 �־�� �ϹǷ� +2��) 
// ȭ�� ���� -1 : +1��

public class Main {
	private static int[][] visit = new int[2001][2001]; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s=Integer.parseInt(br.readLine());
		System.out.println(solve(s));
	}
	
	public static int solve(int s) {
		Queue<XDegree> q = new LinkedList<XDegree>();
		q.add(new XDegree(1,0,0));
		XDegree cur=null;
		visit[1][0]=1;
		
		
		// ã�� ������ �ݺ�
		while(!q.isEmpty()) {
			cur=q.poll();
			
			System.out.println(cur.emoCnt);
			if(cur.emoCnt==s)
				break;
			
			// ���� �������� Ŭ�� ���� ������ �ٸ� ���� �ִ�.
						
			// 1. Ŭ�� ���忡 ���� (��ü ������ ����������, �ð� 1�� ���� �Ǹ� Ŭ�� ������ ������ ���� �̸�Ƽ�� ������ �ٲ��.)
			if(visit[cur.emoCnt][cur.clipCnt]!=1)
				q.add(new XDegree(cur.emoCnt,cur.time+1, cur.emoCnt));	// ���������Ƿ� Ŭ�����忡�� ���� �̸�Ƽ�� ������ ��
			
			visit[cur.emoCnt][cur.clipCnt]=1;
			
			// 2. ȭ�� ���� + Ŭ�� ���� ����
			if(cur.clipCnt!=0 && cur.emoCnt<s && cur.emoCnt+cur.clipCnt<=2000 && visit[cur.emoCnt+cur.clipCnt][cur.clipCnt]!=1)
				q.add(new XDegree(cur.emoCnt+cur.clipCnt,cur.time+1, cur.clipCnt)); // Ŭ�� ����� ����
			
			// 3. ȭ�� ���� -1
			if(cur.emoCnt-1>=0 && visit[cur.emoCnt-1][cur.clipCnt]!=1)
				q.add(new XDegree(cur.emoCnt-1,cur.time+1, cur.clipCnt)); // Ŭ�� ����� ����
			
		}
		
		return cur.time;
	}
	
	private static class XDegree {
		private int emoCnt;
		private int time;
		private int clipCnt;
		
		public XDegree(int emoCnt, int time, int clipCnt) {
			this.emoCnt=emoCnt;
			this.time=time;
			this.clipCnt=clipCnt;
		}
	}
}
