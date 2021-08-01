package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_6087_레이저통신 {
	
	static class Lazer implements Comparable<Lazer>{
		int x; // x좌표.
		int y; // y좌표.
		int direct; // 레이저 방향.
		int cnt; // 거울 횟수.
		public Lazer(int x, int y, int direct, int cnt) {
			this.x = x;
			this.y = y;
			this.direct = direct;
			this.cnt = cnt;
		}//Constructor.
		@Override
		public int compareTo(Lazer l) {
			return this.cnt - l.cnt;
		}
	}//class Lazer.
	
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; // 4방탐색.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		int[][] lazer = new int[2][2];
		int top = -1;
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'C') {
					lazer[++top][0] = i;
					lazer[top][1] = j;
				}
			}//end for2.
		}//end for1.
		
		int[][] dp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}//end for2.
		}//end for1.
		
		int answer = 0;
		
		PriorityQueue<Lazer> pq = new PriorityQueue<Lazer>();
		pq.add(new Lazer(lazer[0][0] , lazer[0][1] , -1, -1)); //처음 방향은 4방으로 뻗어나갈때 거울 횟수를 사용하지 않으므로 -1, -1을 넣어 만족하는 코드를 작성한다.
		while(!pq.isEmpty()) {
			Lazer now = pq.poll();
			if(now.x == lazer[1][0] && now.y == lazer[1][1]) {
				answer = now.cnt;
				break;
			}// 도착점에 도달한 경우.
			for(int k=0; k<4; k++) {
				int nx = now.x + direction[k][0];
				int ny = now.y + direction[k][1];
				int ncnt = now.cnt;
				if(now.direct != k) ncnt++;
				if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] != '*' && dp[nx][ny] >= ncnt) {
					dp[nx][ny] = ncnt;
					pq.add(new Lazer(nx,ny,k,ncnt));
				}// 범위 안에 있고, 벽이 아니면서, 해당 좌표로 가는데 거울 갯수가 작거나 같을경우에만 pq에 추가해준다.
			}//end for1.
		}//end while.
		
		System.out.println(answer);
	}//end main.
}//end class.
