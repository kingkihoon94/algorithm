import java.io.*;
import java.util.*;

public class BOJ_12100_2048 {
	static int N;
	static int[][] originMap;
	static int[][] copyMap;
	static int[] move_list = new int[5];
	static int answer = 2;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		originMap = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
				answer = answer > originMap[i][j] ? answer : originMap[i][j];
			}
		}//draw originMap.
		
		dfs(0);
		
		System.out.println(answer);
	}//end main.
	
	private static void dfs(int cnt) {
		if(cnt == 5) {
			solve();
			return;
		}//return.
		for(int k=0; k<4; k++) {
			move_list[cnt] = k;
			dfs(cnt+1);
		}//end for.
	}//end dfs.
	
	private static void solve() {
		copyMap = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copyMap[i][j] = originMap[i][j];
			}
		}//draw copyMap.
		
		for(int i=0; i<5; i++) {
			if(move_list[i] == 0) moveUp();
			else if(move_list[i] == 1) moveDown();
			else if(move_list[i] == 2) moveLeft();
			else moveRight();
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				answer = answer < copyMap[i][j] ? copyMap[i][j] : answer;
			}
		}//end for.
	}//end solve.
	
	private static void moveUp() {
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(copyMap[i][j] != 0) {
					int num = copyMap[i][j];
					copyMap[i][j] = 0;
					int sx = i;
					while(true) {
						if(sx==0) {
							copyMap[sx][j] = num;
							break;
						}
						if(copyMap[sx-1][j] != 0) {
							if(copyMap[sx-1][j] == num && !visited[sx-1][j]) {
								visited[sx-1][j] = true;
								copyMap[sx-1][j] *= 2;
							}
							else copyMap[sx][j] = num;
							break;
						}
						sx--;
					}//end while.
				}
			}
		}
	}//end moveUp.
	private static void moveDown() {
		visited = new boolean[N][N];
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<N; j++) {
				if(copyMap[i][j] != 0) {
					int num = copyMap[i][j];
					copyMap[i][j] = 0;
					int sx = i;
					while(true) {
						if(sx==N-1) {
							copyMap[sx][j] = num;
							break;
						}
						if(copyMap[sx+1][j] != 0) {
							if(copyMap[sx+1][j] == num && !visited[sx+1][j]) {
								visited[sx+1][j] = true;
								copyMap[sx+1][j] *= 2;
							}
							else copyMap[sx][j] = num;
							break;
						}
						sx++;
					}//end while.
				}
			}
		}
	}//end moveDown.
	private static void moveLeft() {
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(copyMap[i][j] != 0) {
					int num = copyMap[i][j];
					copyMap[i][j] = 0;
					int sy = j;
					while(true) {
						if(sy==0) {
							copyMap[i][sy] = num;
							break;
						}
						if(copyMap[i][sy-1] != 0) {
							if(copyMap[i][sy-1] == num && !visited[i][sy-1]) {
								visited[i][sy-1] = true;
								copyMap[i][sy-1] *= 2;
							}
							else copyMap[i][sy] = num;
							break;
						}
						sy--;
					}//end while.
				}
			}
		}
	}//end moveLeft.
	private static void moveRight() {
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=N-1; j>=0; j--) {
				if(copyMap[i][j] != 0) {
					int num = copyMap[i][j];
					copyMap[i][j] = 0;
					int sy = j;
					while(true) {
						if(sy==N-1) {
							copyMap[i][sy] = num;
							break;
						}
						if(copyMap[i][sy+1] != 0) {
							if(copyMap[i][sy+1] == num && !visited[i][sy+1]) {
								visited[i][sy+1] = true;
								copyMap[i][sy+1] *= 2;
							}
							else copyMap[i][sy] = num;
							break;
						}
						sy++;
					}//end while.
				}
			}
		}
	}//end moveRight.
}//end class.
