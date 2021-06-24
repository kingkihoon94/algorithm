import java.io.*;
import java.util.*;


public class BOJ_13460_구슬탈출2 {
	static char[][] map;
	static int N;
	static int M;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] move_list = {{2,3},{0,1}};
	static int answer = 11; //max값.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int rx = 0;
		int ry = 0;
		int bx = 0;
		int by = 0;
		
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'R') {
					rx = i; ry = j; map[i][j] = '.';
				}
				else if(map[i][j] == 'B') {
					bx = i; by = j; map[i][j] = '.';
				}
			}
		}//draw map.
		
		for(int k=0; k<4; k++) {
			move(rx,ry,bx,by,k,1);
		}//end for.
		
		if(answer == 11) System.out.println(-1);
		else System.out.println(answer);
	}//end main.
	private static void move(int rx, int ry, int bx, int by, int direct, int cnt) {
		//로직 1:blue 먼저 이동해서 blue_end 체크 / 2.red 이동해서 red_end 체크 / 둘의 좌표가 같은경우 가까운거 먼저 자리먹게 처리 / 효율성을 위해 inverse값 잡기.
		if(cnt>=answer) return; //가지치기(현재 구한 answer 보다 cnt 값이 높아지면 할 필요가 없다.)
		
		boolean blue_end = false;
		int bcnt = 0;
		while(true) {
			int nx = bx + direction[direct][0];
			int ny = by + direction[direct][1];
			if(map[nx][ny] == '#') break;
			else if(map[nx][ny] == 'O') {
				blue_end = true;
				break;
			}
			bx = nx;
			by = ny;
			bcnt++;
		}//move BLUE.
		if(blue_end) return;
		//BLUE 끝나는 경우.
		
		boolean red_end = false;
		int rcnt = 0;
		while(true) {
			int nx = rx + direction[direct][0];
			int ny = ry + direction[direct][1];
			if(map[nx][ny] == '#') break;
			else if(map[nx][ny] == 'O') {
				red_end = true;
				break;
			}
			rx = nx;
			ry = ny;
			rcnt++;
		}//move red.
		if(red_end) {
			answer = answer > cnt ? cnt : answer;
			return;
		}//RED 끝나는 경우.
		
		if(rx == bx && ry == by) {
			if(rcnt > bcnt) {
				rx -= direction[direct][0];
				ry -= direction[direct][1];
			}//BLUE가 더 가까운경우.
			else {
				bx -= direction[direct][0];
				by -= direction[direct][1];
			}//RED가 더 가까운경우.
		}//같은곳에 도달할 경우에는 거리가 가까운쪽이 먼저 자리를 가져간다.
		
		int next_idx = direct/2;
		move(rx,ry,bx,by,move_list[next_idx][0],cnt+1);
		move(rx,ry,bx,by,move_list[next_idx][1],cnt+1);
		
	}//end move.
}//end class.
