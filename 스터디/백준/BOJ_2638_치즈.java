package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2638_ДЎБо {
	
	static class Cheese{
		int x;
		int y;
		public Cheese(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Cheese.
	
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		ArrayList<Cheese> list = new ArrayList<Cheese>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) list.add(new Cheese(i,j));
			}//end for2.
		}//end for1.
		
		int answer = 0;
		while(true) {
			if(list.size()==0) break;
			
			Queue<Cheese> q = new LinkedList<Cheese>();
			boolean[][] visited = new boolean[N][M];
			q.add(new Cheese(0,0));
			visited[0][0] = true;
			while(!q.isEmpty()) {
				Cheese c = q.poll();
				map[c.x][c.y] = 2;
				for(int k=0; k<4; k++) {
					int nx = c.x + direction[k][0];
					int ny = c.y + direction[k][1];
					if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && map[nx][ny] != 1) {
						visited[nx][ny] = true;
						q.add(new Cheese(nx,ny));
					}
				}//end for.
			}//end while.
			
			ArrayList<Cheese> dieList = new ArrayList<Cheese>();
			ArrayList<Cheese> liveList = new ArrayList<Cheese>();
			
			for(int i=0; i<list.size(); i++) {
				Cheese c = list.get(i);
				int cnt = 0;
				for(int k=0; k<4; k++) {
					int nx = c.x + direction[k][0];
					int ny = c.y + direction[k][1];
					if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] == 2) cnt++;
				}
				if(cnt>=2) dieList.add(c);
				else liveList.add(c);
			}//end for.
			
			for(int i=0; i<dieList.size(); i++) {
				Cheese c = dieList.get(i);
				map[c.x][c.y] = 0;
			}//end dieList.
			
			list = liveList;
			answer++;
		}//end while.
		
		System.out.println(answer);
	}//end main.
}//end class.
