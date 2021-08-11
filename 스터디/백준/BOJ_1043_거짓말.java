package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_1043_������ {
	
	static int N,M;
    static int answer = 0;
    static boolean[] truth_table;
    static ArrayList<Integer>[] party;
    static ArrayList<Integer>[] people;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		truth_table = new boolean[N+1]; //1�� ���� �����ϹǷ� ũ�� 1 �����ʿ�.
	    people = new ArrayList[N+1];
	    party = new ArrayList[M];
	    for(int i=1; i<=N; i++) {
            people[i] = new ArrayList<Integer>();
        }//end initialize.
        for(int i=0; i<M; i++) {
            party[i] = new ArrayList<Integer>();
        }//end initialize.
		
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for(int i=0; i<cnt; i++) {
			int num = Integer.parseInt(st.nextToken());
			truth_table[num] = true;
		}//end for. (1�ܰ� - ó������ ������ �˰� �ִ� ��� ó��.)
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			for(int j=0; j<length; j++) {
				int num = Integer.parseInt(st.nextToken());
				people[num].add(i); //�ش� �ο��� ���� ��Ƽ ����س���.
				party[i].add(num); //�ش� ��Ƽ�� ���� �ο� ����س���.
			}//end for2.
		}//end for1.
		
		solve();
		System.out.println(answer);
		
	}//end main.
	
	private static void solve() {
		Queue<Integer> q = new LinkedList<Integer>(); //��Ƽ�� bfs�� ������.
		boolean[] know_truth_party = new boolean[M];
		
		for(int i=1; i<=N; i++) {
            if(truth_table[i]) {   // �ƴ� ������� ������ ��Ƽ���� 
                for(int j=0; j<people[i].size(); j++) {
                	int partyNum = people[i].get(j);
                    if(!know_truth_party[partyNum]) {
                    	know_truth_party[partyNum] = true;
                    	q.add(partyNum);
                    }
                }//end for2.
            }
        }//end for1. (�ʱ� q�� �ֱ�.)
		
		while(!q.isEmpty()) {
			int now = q.poll(); //���� Ȯ���ϴ� ��Ƽ��ȣ.
			for(int peopleNum : party[now]) {
				for(int next : people[peopleNum]) {  //���� ����� ��Ƽ��ȣ.
					if(!know_truth_party[next]) {
						know_truth_party[next] = true;
						q.add(next);
					}
				}//end for2.
			}//end for1.
		}//end while.
		
		for(int i=0; i<M; i++) {
			if(!know_truth_party[i]) answer++;
		}//end for.
		
	}//end solve.
}//end class.
