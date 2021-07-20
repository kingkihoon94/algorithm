package algorithm;

import java.awt.Point;
import java.io.*;
import java.util.*;


public class BOJ_3109_»§Áý {
	
	static boolean make = false;
	static int N;
	static int M;
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = tmp.charAt(j);
			}//end for2.
		}//end for1.
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			make = false;
			buildBridge(i,0);
			if(make) answer++;
		}
		System.out.println(answer);
	}//end main.
	
	private static void buildBridge(int row, int col) {
		visited[row][col] = true;
		if(col == M-1) {
			make = true;
			return;
		}
		if(row>=1 && map[row-1][col+1] != 'x' && !visited[row-1][col+1]) buildBridge(row-1, col+1);
		if(!make && map[row][col+1] != 'x' && !visited[row][col+1]) buildBridge(row, col+1);
		if(!make && row<N-1 && map[row+1][col+1] != 'x' && !visited[row+1][col+1]) buildBridge(row+1, col+1);
	}//end buildBridge.
	
}//end class.
