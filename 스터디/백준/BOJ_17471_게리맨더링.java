import java.util.*;
import java.io.*;

public class BOJ_17471_�Ը��Ǵ��� {
	static int N;
	static int[] people_list;
	static boolean[][] map;
	static int answer = Integer.MAX_VALUE;
	static boolean[] section;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		people_list = new int[N];
		map = new boolean[N][N];
		section = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			people_list[i] = Integer.parseInt(st.nextToken());
		}//input people_list.
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for(int j=0; j<tmp; j++) {
				int now = Integer.parseInt(st.nextToken());
				map[i][now-1] = true;
			}
		}//draw map.
		
		for(int i=1; i<=N/2; i++) {
			permutation(i,0,0);
		}
		
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}//end main.
	
	private static void permutation(int length, int cnt , int idx){
		if(cnt == length) {
			int sectionA_start = 0;
			int sectionB_start = 0;
			for(int i=0; i<N; i++) {
				if(section[i]) sectionA_start = i;
				else sectionB_start = i;
			}
			if(check(sectionA_start) && check(sectionB_start)) { //�������� �����°� �ش�Ǵ��� üũ����.
				int cnt_a = 0;
 				int cnt_b = 0;
 				for(int i=0; i<N; i++) {
 					if(section[i]) cnt_a += people_list[i];
 					else cnt_b += people_list[i];
 				}//������ �ο� �� ��.
 				int abs = Math.abs(cnt_a - cnt_b);
 				answer = answer > abs ? abs : answer;
			}
			return ;
		}
		for(int i=idx; i<N; i++) {
 			section[i] = true;
 			permutation(length, cnt+1,i+1);
 			section[i] = false;
 		}
	}//end permutation.
	
	private static boolean check(int start) {
		boolean flag = section[start];
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		while(!q.isEmpty()) {
			int now = q.poll();
			visited[now] = true;
			for(int i=0; i<N; i++) {
				if(map[now][i] && !visited[i] && section[i] == flag) q.offer(i);
			}
		}
		for(int i=0; i<N; i++) {
 			if(section[i] == flag) {
 				if(!visited[i]) return false;
 			}
 		}//���� �������� ������ ������ ���� �湮�� �ȵǾ��ٰ� ǥ�õǾ������� ������ ������ �� ���ٴ� ���̴�.
		return true;
	}//end check.
}//end class.
