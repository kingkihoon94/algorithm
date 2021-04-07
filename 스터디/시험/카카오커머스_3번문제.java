import java.util.*;
import java.io.*;

public class PORG_시험3번문제 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Integer>());
		}//end for.
		for(int i=0 ; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			list.get(left).add(right);
			list.get(right).add(left);
		}//end for.
		int[] parent = new int[N+1];
		int[] cnt = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		visited[1] = true;
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int k=0; k<size; k++) {
				int now = q.poll();
				for(int i=0; i<list.get(now).size(); i++) {
					int next = list.get(now).get(i);
					if(!visited[next]) {
						visited[next] = true;
						cnt[next] = time+1;
						parent[next] = now;
						q.offer(next);
					}
				}
			}
			time++;
		}//end while.
		for(int i=1; i<=N; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println("");
		for(int i=1; i<=N; i++) {
			System.out.print(cnt[i] + " ");
		}
		System.out.println("");
	}//end main.
}//end class.
