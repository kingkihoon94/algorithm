import java.io.*;
import java.util.*;

public class BOJ_17406_배열돌리기4 {
	static int N;
	static int M;
	static int K;
	static int[][] origin_map;
	static int[][] round_map;
	static Order[] order_list;
	static int[] order_call;
	static boolean[] visited_call;
	static int answer = Integer.MAX_VALUE;
	static class Order{
		int x;
		int y;
		int l;
		
		public Order(int x, int y, int l) {
			this.x = x;
			this.y = y;
			this.l = l;
		}
	}//Class Order.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//end input N,M,K.
		origin_map = new int[N][M];
		order_list = new Order[K];
		order_call = new int[K];
		visited_call = new boolean[K];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				origin_map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw origin_map.
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			order_list[i] = new Order(Integer.parseInt(st.nextToken())-1 , Integer.parseInt(st.nextToken())-1 , Integer.parseInt(st.nextToken()));
		}
		dfs(0);
		System.out.println(answer);
	}//end main.
	
	private static void dfs(int cnt) {
		if(cnt == K) {
			start();
			return;
		}//end if.
		for(int i=0; i<K; i++) {
			if(!visited_call[i]) {
				visited_call[i] = true;
				order_call[cnt] = i;
				dfs(cnt+1);
				visited_call[i] = false;
			}
		}//end for.
	}//end dfs.
	
	private static void start() {
		round_map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				round_map[i][j] = origin_map[i][j];
			}
		}//copy map.
		for(int i=0; i<K; i++) {
			rotate(order_list[order_call[i]]);
		}
		for(int i=0; i<N; i++) {
			int tmp_cnt = 0;
			for(int j=0; j<M; j++) {
				tmp_cnt += round_map[i][j];
			}
			answer = answer > tmp_cnt ? tmp_cnt : answer;
		}
	}//end start.
	
	private static void rotate(Order o) {
		int dx = o.x;
		int dy = o.y;
		int dl = o.l;
		
		for(int l=1; l<=dl; l++) {
			int tmp = round_map[dx-l][dy+l];
			for(int i=dy+l; i>dy-l; i--) {
				round_map[dx-l][i] = round_map[dx-l][i-1];
			}//윗줄 왼 -->오 값 이동.
			for(int i=dx-l; i<dx+l; i++) {
				round_map[i][dy-l] = round_map[i+1][dy-l];
			}//왼쪽줄 밑 --> 위 값 이동. 
			for(int i=dy-l; i<dy+l; i++) {
				round_map[dx+l][i] = round_map[dx+l][i+1];
			}//밑줄 오 --> 왼 값 이동.
			for(int i=dx+l; i>dx-l; i--) {
				round_map[i][dy+l] = round_map[i-1][dy+l];
			}//오른쪽줄 위 --> 밑 값 이동.
			round_map[dx-l+1][dy+l] = tmp; //마지막 값 저장해둔 tmp 값으로 갱신.
		}//Rotate.
		
	}//end rotate.
	
}//end class.
