import java.io.*;
import java.util.*;


public class BOJ_17070_�������ű��1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+2][N+2];
		int[][][] dp = new int[3][N+2][N+2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw map.
		dp[0][1][2] = 1;
		dp[1][1][2] = 0;
		dp[2][1][2] = 0;
		//dp�� Ǯ������ 3�������� �и����� ���߿� ��ģ��. dp[0] = ��-->�� / dp[1] = ��-->�� / dp[2] �밢��.
		for(int i=1; i<=N; i++) {
			for(int j=2; j<=N; j++) {
				if(map[i][j+1] != 1) dp[0][i][j+1] = dp[0][i][j] + dp[2][i][j];
				if(map[i+1][j] != 1) dp[1][i+1][j] = dp[1][i][j] + dp[2][i][j];
				if(map[i+1][j+1] != 1 && map[i+1][j] != 1 && map[i][j+1] != 1) dp[2][i+1][j+1] = dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
			}
		}
		int answer = dp[0][N][N] + dp[1][N][N] + dp[2][N][N];
		System.out.println(answer);
	}//end main.
}//end class.
