package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2234_성곽 {
	
	static int[][] direction = {{0,-1},{-1,0},{0,1},{1,0}};
	static int N;
	static int M;
	static int[][] map;
	static int[][] section_map;
	static boolean[][] visited;
	static HashMap<Integer, Integer> hashmap = new HashMap<Integer,Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		section_map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw map.
		
		int section = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					bfs(i,j,++section);
				}
			}
		}
		
		ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
		for(int i=0; i<=section; i++) {
			list.add(new HashSet<Integer>());
		}//initialize.
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int nowSection = section_map[i][j];
				for(int k=0; k<4; k++) {
					int nx = i + direction[k][0];
					int ny = j + direction[k][1];
					if(nx>=0 && nx<N && ny>=0 && ny<M && section_map[nx][ny] != nowSection) {
						list.get(nowSection).add(section_map[nx][ny]);
					}
				}
			}
		}
		
		int answer1 = section;
		int answer2 = 0;
		int answer3 = 0;
		
		for(int i=1; i<=section; i++) {
			answer2 = answer2 < hashmap.get(i) ? hashmap.get(i) : answer2;
			for(Integer num : list.get(i)) {
				answer3 = answer3 < hashmap.get(i) + hashmap.get(num) ? hashmap.get(i) + hashmap.get(num) : answer3;
			}
		}
		
		System.out.println(answer1);
		System.out.println(answer2);
		System.out.println(answer3);
		
	}//end main.
	private static void bfs(int sx, int sy, int section) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {sx,sy});
		visited[sx][sy] = true;
		int size = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			size++;
			section_map[now[0]][now[1]] = section;
			for(int k=0; k<4; k++) {
				int canMove = (map[now[0]][now[1]] >> k) & 1;
				if(canMove == 0) {
					int nx = now[0] + direction[k][0];
					int ny = now[1] + direction[k][1];
					if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new int[] {nx,ny});
					}//구간을 벗어나지 않고 방문하지 않은곳으로 뻗어나간다.
				}//해당 길 열려있는경우.
			}//4방탐색.
		}//end while.
		hashmap.put(section, size);
	}//end bfs.
}//end class.
