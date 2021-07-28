package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_1765_�߽ο������ϱ� {
	static int[] teamNumber;
	static int[] enemyNumber;
	
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		answer = N;
		
		teamNumber = new int[N+1];
		enemyNumber = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			teamNumber[i] = i;
		}//end initialize.
		
		int edges = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int edge=0; edge<edges; edge++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			if(type == 'E'){
              if(enemyNumber[L] == 0) enemyNumber[L] = R;
              else union(enemyNumber[L], R);
              if(enemyNumber[R] == 0) enemyNumber[R] = L;
              else union(enemyNumber[R], L);
			}
			else union(L, R);
		}//end for.
		System.out.println(answer);
	}//end main.
	
	private static void union(int L, int R) { //�׻� L�� R���� �۴�(�Է°��� �׷��� �־����ٰ� ���.)
		
		int l = find(L);
		int r = find(R);
		if(l != r) {
            teamNumber[r] = l;
            answer--;
        }//���� �������ϱ� ó���� N���� �����ִµ� �ϳ��� ���δ�.
	}//end union.
	
	private static int find(int num) {
		if(teamNumber[num] == num) return num;
		else return teamNumber[num] = find(teamNumber[num]);
	}//end find.
}//end class.
