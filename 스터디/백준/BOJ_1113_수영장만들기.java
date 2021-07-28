package algorithm;

import java.util.*;
import java.awt.Point;
import java.io.*;

public class BOJ_1113_수영장만들기 {
	
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int answer = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		int maxH = 0; //입력값 1~9.
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j) - '0';
				maxH = maxH < map[i][j] ? map[i][j] : maxH;
			}//end for2.
		}//end for1.
		
		for(int h=2; h<=maxH; h++) {
			visited = new boolean[N][M];
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<M-1; j++) {
					if(!visited[i][j] && map[i][j] < h) {
						bfs(i,j,h);
					}//물을 넣을 수 있다고 판단한 시작점에서 bfs를 돌릴것이다.
				}//end for3. (가장자리는 다 뺀다.)
			}//end for2. (가장자리는 다 뺀다.)
		}//end for1.
		
		System.out.println(answer);
	}//end main.
	private static void bfs(int sx, int sy,  int h) {
		boolean can_make = true;
		Queue<Point> q = new LinkedList<Point>();
		visited[sx][sy] = true;
		q.add(new Point(sx,sy));
		int section = 0;
		while(!q.isEmpty()) {
			Point p = q.poll();
			section++;
			if(p.x ==0 || p.x == N-1 || p.y == 0 || p.y == M-1) {
				can_make = false;
				continue;
			}
			for(int k=0; k<4; k++) {
				int nx = p.x + direction[k][0];
				int ny = p.y + direction[k][1];
				if(!visited[nx][ny] && map[nx][ny] < h) {
					visited[nx][ny] = true;
					q.add(new Point(nx,ny));
				}
			}
		}//end while.
		if(can_make) answer += section;
	}//end bfs.
}//end class.

