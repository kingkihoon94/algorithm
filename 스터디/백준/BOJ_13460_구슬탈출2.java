import java.io.*;
import java.util.*;


public class BOJ_13460_����Ż��2 {
	static char[][] map;
	static int N;
	static int M;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] move_list = {{2,3},{0,1}};
	static int answer = 11; //max��.
	
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
		//���� 1:blue ���� �̵��ؼ� blue_end üũ / 2.red �̵��ؼ� red_end üũ / ���� ��ǥ�� ������� ������ ���� �ڸ��԰� ó�� / ȿ������ ���� inverse�� ���.
		if(cnt>=answer) return; //����ġ��(���� ���� answer ���� cnt ���� �������� �� �ʿ䰡 ����.)
		
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
		//BLUE ������ ���.
		
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
		}//RED ������ ���.
		
		if(rx == bx && ry == by) {
			if(rcnt > bcnt) {
				rx -= direction[direct][0];
				ry -= direction[direct][1];
			}//BLUE�� �� �������.
			else {
				bx -= direction[direct][0];
				by -= direction[direct][1];
			}//RED�� �� �������.
		}//�������� ������ ��쿡�� �Ÿ��� ��������� ���� �ڸ��� ��������.
		
		int next_idx = direct/2;
		move(rx,ry,bx,by,move_list[next_idx][0],cnt+1);
		move(rx,ry,bx,by,move_list[next_idx][1],cnt+1);
		
	}//end move.
}//end class.
