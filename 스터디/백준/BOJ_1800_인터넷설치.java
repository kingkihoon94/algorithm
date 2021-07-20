package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_1800_���ͳݼ�ġ {
	
	static class Node implements Comparable<Node>{
		int end;
		int price;
		public Node(int end, int price) {
			this.end = end;
			this.price = price;
		}//Constructor.
		@Override
		public int compareTo(Node node) {
			return this.price - node.price;
		}
	}//class Node.
	
	static int N;
	static int P;
	static int K;
	static ArrayList<Node>[] list; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}//initialize.
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, value));
            list[end].add(new Node(start, value));
            max = max < value ? value : max;
        }//end for.
		
		int start = 0;
        int answer = -1;

        while(start<=max) {
        	int mid = (start+max)/2;
        	if(check(mid)) {
        		answer = mid;
        		max = mid-1;
        	}//������ ���.
        	else start = mid+1;
        }//end while(�̺�Ž��).
        System.out.println(answer);
	}//end main.
	
	private static boolean check(int price) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		int[] cntList = new int[N+1]; //1������ �ش� i �� ���µ� ����� ���ἱ�� ����.
		
		for (int i=1; i<=N; i++) {
			cntList[i] = Integer.MAX_VALUE;
        }//end initialize.
		
		cntList[1] = 0;
		pq.add(new Node(1,0)); //1������ ����.
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			int now_idx = now.end;
			int now_price = now.price;
			if(cntList[now_idx] < now_price) continue; //�ش� idx�� ���µ� ����� ���� ���� ������ �� ������� ����.
			
			for(Node next : list[now.end]) {
				int next_idx = next.end;
				int next_price = now_price;
				
				if(price < next.price) next_price++;
				
				if(next_price < cntList[next_idx]) {
					cntList[next_idx] = next_price;
					pq.add(new Node(next_idx,next_price));
				}
			}//����Ⱥκ� �Ѹ�������.
		}//end while(�켱����ť).
		return cntList[N] <= K;
	}//end check.
	
}//end class.
