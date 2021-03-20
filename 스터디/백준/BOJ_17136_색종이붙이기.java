import java.util.*;
import java.awt.Point;
import java.io.*;

public class BOJ_17136_색종이붙이기 {
	static int[][] map = new int[11][11];
	static boolean[][] visited = new boolean[10][10];
	static int[] paper_list = {0,5,5,5,5,5};
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw map.
		dfs(0);
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}//end main.
	
	private static void dfs(int cnt) {
		if(cnt > answer) return ;
		
		for(int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					int length = max_length(new Point(i,j));
					for(int k=length; k>=1; k--) {
						if(paper_list[k] > 0) {
							draw(new Point(i,j),k);
							dfs(cnt+1);
							clear(new Point(i,j),k);
						}
					}
					visited[i][j] = false;
					return ;
				}
			}
		}//색종이 붙일 수 있는 시작 칸 찾기.
		answer = cnt < answer ? cnt : answer;
	}//end dfs.
	
	private static int max_length(Point p) {
		int cnt = 1;
		while(cnt <= 5) {
			for(int i=0; i<cnt; i++) {
				for (int j=0; j<cnt; j++) {
					if(map[p.x+i][p.y+j] != 1) return cnt-1;
				}
			}
			cnt++;
		}
		return cnt-1;
	}//end max_length.
	
	private static void draw(Point p , int length) {
		paper_list[length]--;
		int dx = p.x;
		int dy = p.y;
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				map[dx+i][dy+j] = 0;
			}
		}
	}//end draw.
	private static void clear(Point p , int length) {
		paper_list[length]++;
		int dx = p.x;
		int dy = p.y;
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				map[dx+i][dy+j] = 1;
			}
		}
	}//end clear.
	
}//end class.
