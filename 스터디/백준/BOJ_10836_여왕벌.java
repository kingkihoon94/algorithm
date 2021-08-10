package algorithm;

import java.io.*;
import java.util.*;


public class BOJ_10836_¿©¿Õ¹ú {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());;
		int[][] map = new int[N][N];
		
		for(int day=1; day<=M; day++) {
			
			int[][] dp = new int[N][N];
			st = new StringTokenizer(br.readLine());
			
			int nextNum = 0;
			int nextIdx = 0;
			
			while(st.hasMoreTokens()) {
				int cnt = Integer.parseInt(st.nextToken());
				for(int i=0; i<cnt; i++) {
					int row = 0;
					int col = 0;
					if(nextIdx<N) {
						row = N-(nextIdx+1);
						col = 0;
					}
					else {
						row = 0;
						col = (nextIdx-N)+1;
					}
					dp[row][col] = nextNum; 
					map[row][col] += nextNum;
					nextIdx++;
				}
				nextNum++;
			}//end initialize row and col.
			
			for(int i=1; i<N; i++) {
				for(int j=1; j<N; j++) {
					dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
					map[i][j] += dp[i][j];
				}//end for2.
			}//end for1.
			
		}//end simulation.
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(map[i][j]+1).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
		
	}//end main.
}//end class.
