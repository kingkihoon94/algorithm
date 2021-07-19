package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_16639_괄호추가하기 {
	
	static ArrayList<Integer> numList = new ArrayList<Integer>();
	static ArrayList<Character> operList = new ArrayList<Character>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		
		int[][] maxList = new int[N/2+1][N/2+1]; // row , col : row에서 col 까지 사용했을때 가장 큰 값 저장.
		int[][] minList = new int[N/2+1][N/2+1]; // row , col : row에서 col 까지 사용했을때 가장 작은 값 저장.
		
		for (int i=0; i<N/2+1; i++) {
            for (int j=0; j<N/2+1; j++) {
                maxList[i][j] = Integer.MIN_VALUE;
                minList[i][j] = Integer.MAX_VALUE;
            }//end for2.
        }//end for1.
		
		for (int i=0; i<N/2+1; i++) {
			maxList[i][i] = line.charAt(i * 2) - '0';
			minList[i][i] = line.charAt(i * 2) - '0';
        }//end initialize.
		
		for(int cnt=1; cnt<N/2+1; cnt++) { //cnt 만큼 뽑아서 연산 진행.
			for(int idx=0; idx<N/2+1-cnt; idx++) { //연산 시작 idx.
				for (int mid=1; mid<=cnt; mid++) {
					
					int op = (idx+mid-1)*2+1;
					int j=cnt-mid+1;
                    int[] list = new int[4];

                    list[0] = calc(maxList[idx][idx+mid-1], maxList[idx+mid][idx+cnt], line.charAt(op));
                    list[1] = calc(maxList[idx][idx+mid-1], minList[idx+mid][idx+cnt], line.charAt(op));
                    list[2] = calc(minList[idx][idx+mid-1], maxList[idx+mid][idx+cnt], line.charAt(op));
                    list[3] = calc(minList[idx][idx+mid-1], minList[idx+mid][idx+cnt], line.charAt(op));

                    Arrays.sort(list);

                    maxList[idx][idx+cnt] = maxList[idx][idx+cnt] < list[3] ? list[3] : maxList[idx][idx+cnt];
                    minList[idx][idx+cnt] = minList[idx][idx+cnt] > list[0] ? list[0] : minList[idx][idx+cnt];
                }//end for3.
            }//end for2.
        }//end for1.
		
		System.out.println(maxList[0][N/2]);
		
	}//end main.
	
	private static int calc(int num1, int num2, char op) {
        if(op == '+') return num1+num2;
        else if(op == '-') return num1-num2;
        else return num1*num2;
    }//end calc.
	
}//end class.
