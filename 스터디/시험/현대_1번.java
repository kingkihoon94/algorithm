package algorithm;

import java.util.*;
import java.io.*;

public class Çö´ë_1¹ø {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int car_srow = -1;
		int car_erow = -1;
		int land_srow = -1;
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			boolean no_x = true;
			for(int j=0; j<line.length();j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'X') {
					if(car_srow == -1) car_srow = i;
					no_x = false;
				}
				else if(map[i][j] == '#' && land_srow == -1) land_srow = i;
			}
			if(no_x && car_erow == -1) car_erow = i-1; 
		}//end for.
		
		System.out.println(car_srow + " , " + car_erow + " , " + land_srow);
		int pass_length = land_srow - car_erow -1;
		for(int row = car_erow; row>=car_srow; row--) {
			for(int col=0; col<M; col++) {
				map[row+pass_length][col] = map[row][col];
				map[row][col] = '.';
			}//end for1.
		}//end for2.
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
		
	}//end main.
}//end class.