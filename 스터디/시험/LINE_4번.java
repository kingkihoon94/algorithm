package algorithm;

import java.io.*;
import java.util.*;

public class LINE_4¹ø {
	static class Drink implements Comparable<Drink>{
		int cnt;
		int weight;
		int price;
		double price_per_weight;
		public Drink(int cnt, int weight, int price) {
			this.cnt = cnt;
			this.weight = weight;
			this.price = price;
			this.price_per_weight = (double)price/weight;
		}//Constructor.
		@Override
		public int compareTo(Drink drink) {
			return (int)(drink.price_per_weight - this.price_per_weight);
		}
	}//class Drink.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int max_weight = 0;
		
		PriorityQueue<Drink> pq = new PriorityQueue<Drink>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			Drink drink = new Drink(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			pq.add(drink);
			max_weight = max_weight < drink.weight ? drink.weight : max_weight;
		}//end input.
		
		System.out.println(max_weight);
		int P = Integer.parseInt(br.readLine());
		
		int[] prev =  new int[P+max_weight+1];
		
		while(!pq.isEmpty()) {
			Drink drink = pq.poll();
			int[] now = new int[P+max_weight+1];
			int cnt = 1;
			int idx = drink.weight;
			while(idx<=P+max_weight && cnt <= drink.cnt) {
				now[idx] = drink.price * cnt++;
				idx += drink.weight;
			}//end while.
			for(int i=0; i<=P+max_weight; i++) {
				System.out.print(now[i] + " ");
			}
			System.out.println("");
//			for(int i=0; i<=P+max_weight; i++) {
//				if(i-drink.weight>=0) {
//					now[i] = now[i] < prev[i-drink.weight] + now[i-drink.weight] ? prev[i-drink.weight] + now[i-drink.weight] : now[i];
//				}
//				System.out.print(now[i] + " ");
//			}
			System.out.println("");
			for(int i=0; i<=P+max_weight; i++) {
				prev[i] = now[i];
			}
		}
		
	}//end main.
}//end class.



class Solution {
    public String solution(String[] prj, int n, int k) {
    	
        String answer = "";
        return answer;
    }
}
