import java.util.*;
import java.awt.Point;
import java.io.*;

public class BOJ_17472_다리만들기2 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int island = 0;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] bridge;
	static boolean[] island_check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw map.
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					island++;
					bfs(i,j,island);
				}
			}
		}//bfs.
		
		bridge = new int[island+1][island+1];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					int start_island = map[i][j];
					for(int k=0; k<4; k++) {
						int nx = i + direction[k][0];
						int ny = j + direction[k][1];
						if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] == 0) {
							make_bridge(start_island, nx, ny, k);
						}
					}
				}
			}
		}//다리 건설.
		
		int answer = 0;
		island_check = new boolean[island+1];
		island_check[1] = true;
		while(!all_check()) {
			int min = Integer.MAX_VALUE;
			int end_island = 0;
			for(int i=2; i<=island; i++) {
				if(!island_check[i] && bridge[1][i] != 0 && min > bridge[1][i]){
					min = bridge[1][i];
					end_island = i;
				}
			}//현재 섬에서 가장 짧은 다리를 지어 합칠 수 있는 경우를 찾는다.
			
			if(end_island == 0) {
				answer = -1;
				break;
			}//다 안합쳤으나 다리를 짓지 못하는 경우 -1 출력.
			
			island_check[end_island] = true;
			answer += min;
			for(int i=2; i<=island; i++) {
				if(bridge[end_island][i] != 0) {
					if(bridge[1][i] == 0) bridge[1][i] = bridge[end_island][i];
					else bridge[1][i] = bridge[end_island][i] < bridge[1][i] ? bridge[end_island][i] : bridge[1][i];
				}
			}//합쳐진 섬 다리 데이터 합치기. (Prim 알고리즘 참고.)
		}//end while.
		
		System.out.println(answer);
	}//end main.
	
	private static void bfs(int dx, int dy, int island) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(dx, dy));
		while(!q.isEmpty()) {
			Point p = q.poll();
			visited[p.x][p.y] = true;
			map[p.x][p.y] = island;
			for(int k=0; k<4; k++) {
				int nx = p.x + direction[k][0];
				int ny = p.y + direction[k][1];
				if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] != 0 && !visited[nx][ny]) {
					q.offer(new Point(nx,ny));
				}
			}
		}
	}//end bfs.
	
	private static void make_bridge(int start, int dx, int dy, int direct) {
		int cnt = 1;
		while(true) {
			dx += direction[direct][0];
			dy += direction[direct][1];
			if(dx <0 || dx>=N || dy<0 || dy>=M) break;
			if(map[dx][dy] != 0) {
				if(cnt ==1) break;
				int end = map[dx][dy];
				if(start != end) {
					if(bridge[start][end] == 0) bridge[start][end] = cnt;
					else bridge[start][end] = bridge[start][end] > cnt ? cnt : bridge[start][end];
				}
				break;
			}
			cnt++;
		}
	}//end make_bridge.
	
	private static boolean all_check() {
		for(int i=1; i<=island; i++) {
			if(!island_check[i]) return false;
		}
		return true;
	}//end all_check.
	
}//end class.
