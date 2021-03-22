import java.io.*;
import java.util.*;

public class BOJ_17281_베이스볼 {
	static int N;
	static int[][] result;
	static int answer = Integer.MIN_VALUE;
	static int[] player_list = new int[9];
	static boolean[] visited = new boolean[9];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		result = new int[N][9];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input.
		player_list[3] = 1;
		visited[3] = true;
		dfs(2);
		System.out.println(answer);
	}//end main.
	private static void dfs(int cnt) {
		if(cnt >9) {
			game(); //경기 시작.
			return;
		}//다 골랐을 경우.
		for(int i=0; i<9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				player_list[i] = cnt;
				dfs(cnt+1);
				visited[i] = false;
			}
		}//end for.
	}//end dfs.
	private static void game() {
		int score = 0;
		int out = 0;
		int now = 0;
		int inning = 0;
		int[] field = new int[3];
		while(inning < N) {
			int player = player_list[now];
			int now_play = result[inning][player-1];
			if(now_play == 0) out++;
			else if(now_play == 1) {
				if(field[2] == 1) score++;
				field[2] = field[1];
				field[1] = field[0];
				field[0] = 1;
			}
			else if(now_play == 2) {
				if(field[2] == 1) score++;
				if(field[1] == 1) score++;
				field[2] = field[0];
				field[1] = 1;
				field[0] = 0;
			}
			else if(now_play == 3) {
				if(field[2] == 1) score++;
				if(field[1] == 1) score++;
				if(field[0] == 1) score++;
				field[2] = 1;
				field[1] = 0;
				field[0] = 0;
			}
			else {
				if(field[2] == 1) {
					score++;
					field[2] = 0;
				}
				if(field[1] == 1) {
					score++;
					field[1] = 0;
				}
				if(field[0] == 1) {
					score++;
					field[0] = 0;
				}
				score++;
			}
			now++;
			if(now == 9) now = 0; //로테이션.
			if(out == 3) {
				inning++; //이닝 끝.
				field[2] = 0;
				field[1] = 0;
				field[0] = 0;
				out = 0;
			}//아웃.
			//System.out.println(inning + " , " + out);
		}//end while.
		answer = answer < score ? score : answer;
	}//end game.
}//end class.
