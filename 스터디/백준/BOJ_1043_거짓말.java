package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_1043_거짓말 {
	
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
		
		truth_table = new boolean[N+1]; //1번 부터 시작하므로 크기 1 증가필요.
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
		}//end for. (1단계 - 처음부터 진실을 알고 있는 사람 처리.)
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			for(int j=0; j<length; j++) {
				int num = Integer.parseInt(st.nextToken());
				people[num].add(i); //해당 인원이 들어가는 파티 기록해놓기.
				party[i].add(num); //해당 파티에 들어가는 인원 기록해놓기.
			}//end for2.
		}//end for1.
		
		solve();
		System.out.println(answer);
		
	}//end main.
	
	private static void solve() {
		Queue<Integer> q = new LinkedList<Integer>(); //파티로 bfs를 돌릴것.
		boolean[] know_truth_party = new boolean[M];
		
		for(int i=1; i<=N; i++) {
            if(truth_table[i]) {   // 아는 사람들이 참여한 파티정보 
                for(int j=0; j<people[i].size(); j++) {
                	int partyNum = people[i].get(j);
                    if(!know_truth_party[partyNum]) {
                    	know_truth_party[partyNum] = true;
                    	q.add(partyNum);
                    }
                }//end for2.
            }
        }//end for1. (초기 q값 넣기.)
		
		while(!q.isEmpty()) {
			int now = q.poll(); //현재 확인하는 파티번호.
			for(int peopleNum : party[now]) {
				for(int next : people[peopleNum]) {  //다음 뻗어나갈 파티번호.
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
