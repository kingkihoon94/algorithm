package algorithm;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ_15591_MOOTUBE {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Point>> list = new ArrayList<ArrayList<Point>>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Point>());
		}//initialize list.
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			list.get(L).add(new Point(R,D));
			list.get(R).add(new Point(L,D));
		}//end for.
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			int cnt = 0;
			boolean[] visited = new boolean[N+1];
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(S);
			visited[S] = true;
			
			while(!q.isEmpty()) {
				int now = q.poll();
				for(int k=0; k<list.get(now).size(); k++) {
					Point p = list.get(now).get(k);
					if(!visited[p.x] && p.y >= K) {
						cnt++;
						visited[p.x] = true;
						q.add(p.x);
					}
				}//end for.
			}
			sb.append(cnt).append("\n");
		}//end for.
		System.out.print(sb);
	}//end main.
}//end class.
