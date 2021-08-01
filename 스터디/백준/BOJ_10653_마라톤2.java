package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_10653_마라톤2 {
	static int[][] checkpoint;
	static int[][] dp;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		checkpoint = new int[N+1][2];
		dp = new int[N+1][K+1];
		dist = new int[N+1][N+1]; // i 에서 j 까지 절대거리.
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			checkpoint[i][0] = Integer.parseInt(st.nextToken());
			checkpoint[i][1] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}//end for1.
		
		for(int i=1; i<N; i++) {
			for(int j=i+1; j<=N; j++) {
				dist[i][j] = getDistance(checkpoint[i][0], checkpoint[i][1], checkpoint[j][0], checkpoint[j][1]);
			}//end for2.
		}//end for1.
		
		System.out.println(topDown(N,K)); //N체크포인트까지 가는데 K개 생략한 최소거리.
	}//end main.
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}//end getDistance.
	
	private static int topDown(int N, int K) {
		if(dp[N][K]!=-1)return dp[N][K];
		if(N==1) return 0; //1에서 1은 거리 0이다.
		
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<=K; i++) {
			if(N-i>1) min = Math.min(topDown(N-i-1,K-i) + dist[N-i-1][N], min); //i: i개만큼 생략할것이 남은상태.
		}
		return dp[N][K] = min;
	}//end topDown.
	
}//end class.
