package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_10159_���� {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] bigMap = new boolean[N+1][N+1]; // bigMap[i][j] == true --> i�� j���� ũ��.
		boolean[][] smallMap = new boolean[N+1][N+1]; // smallMap[i][j] == true --> i�� j���� �۴�.
		
		StringTokenizer st;
		
		for(int i=0; i<M; i++) {
			 st = new StringTokenizer(br.readLine());
			 int big = Integer.parseInt(st.nextToken());
			 int small = Integer.parseInt(st.nextToken());
			 
			 bigMap[big][small] = true; 
			 smallMap[small][big] = true;
		}//end for.

		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(bigMap[i][k] && bigMap[k][j]) bigMap[i][j] = true; 
					//������ ū ���� ū ���� ������ ũ��.
					if(smallMap[i][k] && smallMap[k][j]) smallMap[i][j] = true;
					//������ ���� ���� ���� ���� ������ �۴�.
				}//end for j.
			}//end for i.
		}//end for k.
		
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			int answer = 0;
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				else {
					if(!bigMap[i][j] && !smallMap[i][j]) answer++; 
					//ū���� �������� �𸣴� ���϶� answer����.
				}
			}
			sb.append(answer).append("\n");
		}//end for.
		
		System.out.print(sb);
		
	}//end main.
}//end class.
