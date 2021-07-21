package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_4256_트리 {
	
	static int[] preList;
	static int[] inList;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; test++) {
			int N = Integer.parseInt(br.readLine());	
			preList = new int[N];
			inList = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				preList[i] = Integer.parseInt(st.nextToken());
			}//end for2.
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				inList[i] = Integer.parseInt(st.nextToken());
			}//end for2.
			solve(0,0,N); //root , start, end.
			sb.append("\n");
		}//end for1.
		System.out.print(sb);
	}//end main.
	private static void solve(int root, int start, int end) {
		for(int i=start; i<end; i++) {
			if(preList[root] == inList[i]) {
				solve(root+1, start, i); //현재 루트보다 왼쪽에 오는것들 처리.
				solve(root+i-start+1, i+1, end);//현재 루트보다 오른쪽에 오는것들 처리.
				sb.append(preList[root]).append(" ");
				break;
			}
		}//end for.
	}//end solve.
}//end class.
