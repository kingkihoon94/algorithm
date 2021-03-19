import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ_17135_캐슬디펜스 {
	static int N;
	static int M;
	static int D;
	static ArrayList<Point> archer_list = new ArrayList<Point>();
	static int answer = 0;
	static int[][] game_map;
	static int[][] direction = {{0,-1},{-1,0},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken()); //end input N, M, D.
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//Draw Map.
		for(int k1=0; k1<M-2; k1++) {
			archer_list.add(new Point(N,k1));
			for(int k2=k1+1; k2<M-1; k2++) {
				archer_list.add(new Point(N,k2));
				for(int k3=k2+1; k3<M; k3++) {
					archer_list.add(new Point(N,k3));
					game(map);
					archer_list.remove(archer_list.size()-1);
				}
				archer_list.remove(archer_list.size()-1);
			}
			archer_list.remove(archer_list.size()-1);
		}//궁수배치후 시작.
		System.out.println(answer);
	}//end main.
	
	private static void game(int[][] map) {
		game_map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				game_map[i][j] = map[i][j];
			}
		}//Copy Map.
		
		int cnt = 0;
		while(!clear()) {
			ArrayList<Point> enemy_list = new ArrayList<Point>();
			for(int i=0; i<3; i++) {
				Point p = find(archer_list.get(i));
				if(p.x != -1) enemy_list.add(p);
			}
			cnt += remove(enemy_list);
			move();
		}//end while.
		answer = answer < cnt ? cnt : answer;
	}//end game.
	
	private static boolean clear() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(game_map[i][j] != 0) return false;
			}
		}
		return true;
	}//end clear.
	
	private static Point find(Point p) {
		int distance = 1;
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new LinkedList<Point>();
		q.add(p);
		while(!q.isEmpty()) {
			if(distance > D) break;
			int size = q.size();
			for(int i=0; i<size; i++) {
				Point now = q.poll();
				for(int k=0; k<3; k++) {
					int nx = now.x + direction[k][0];
					int ny = now.y + direction[k][1];
					if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny]) {
						visited[nx][ny] = true;
						if(game_map[nx][ny] == 1) return new Point(nx,ny);
						else q.add(new Point(nx,ny));
					}
				}
			}
			distance++;
		}//end while.
		
		return new Point(-1,-1);
	}//end find.
	
	private static int remove(ArrayList<Point> list) {
		int cnt = 0;
		for(int i=0; i<list.size(); i++) {
			Point p = list.get(i);
			if(game_map[p.x][p.y] != 0) {
				cnt++;
				game_map[p.x][p.y] = 0;
			}
		}
		return cnt;
	}//end remove.
	
	private static void move() {
		for(int i=N-1; i>0; i--) {
			for(int j=0; j<M; j++) {
				game_map[i][j] = game_map[i-1][j];
			}
		}
		for(int j=0; j<M; j++) {
			game_map[0][j] = 0;
		}
	}//end move.
	
}//end class.
