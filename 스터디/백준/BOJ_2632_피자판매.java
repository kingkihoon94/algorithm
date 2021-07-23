package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2632_피자판매 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Total = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N1 = Integer.parseInt(st.nextToken());
		int N2 = Integer.parseInt(st.nextToken());
		
		int[] pizza1 = new int[N1];
		int[] pizza2 = new int[N2];
		int total1 = 0;
		int total2 = 0;
		for(int i=0; i<N1; i++) {
			pizza1[i] = Integer.parseInt(br.readLine());
			total1 += pizza1[i];
		}//end pizza1.
		for(int i=0; i<N2; i++) {
			pizza2[i] = Integer.parseInt(br.readLine());
			total2 += pizza2[i];
		}//end pizza2.
		
		HashMap<Integer,Integer> map1 = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> map2 = new HashMap<Integer,Integer>();
		map1.put(total1, 1);
		map2.put(total2, 1);
		for(int i=0; i<N1; i++) {
			int now = i;
			int sum = 0;
			int cnt = 0;
			while(cnt<N1-1) {
				sum += pizza1[now];
				now++;
				if(now==N1) now = 0;
				cnt++;
				if(map1.containsKey(sum)) {
					map1.put(sum, map1.get(sum)+1);
				}
				else map1.put(sum, 1);
			}//end while.
		}//end map1.
		
		for(int i=0; i<N2; i++) {
			int now = i;
			int sum = 0;
			int cnt = 0;
			while(cnt<N2-1) {
				sum += pizza2[now];
				now++;
				if(now==N2) now = 0;
				cnt++;
				if(map2.containsKey(sum)) {
					map2.put(sum, map2.get(sum)+1);
				}
				else map2.put(sum, 1);
			}//end while.
		}//end map2.
		
		int answer = 0;
		if(map1.containsKey(Total)) answer+= map1.get(Total);
		if(map2.containsKey(Total)) answer+= map2.get(Total);
		for(int i=1; i<Total; i++) {
			int L = i;
			int R = Total-i;
			int tmp = 0;
			if(map1.containsKey(L) && map2.containsKey(R)) tmp = map1.get(L) * map2.get(R);
			answer += tmp;
		}//end for1.
		
		System.out.println(answer);
		
	}//end main.
}//end class.
