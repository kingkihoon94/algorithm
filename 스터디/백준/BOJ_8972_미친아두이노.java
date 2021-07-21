package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_8972_��ģ�Ƶ��̳� {
	
	static class Arduino{
		int x;
		int y;
		public Arduino(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Arduino.
	
	static int[][] direction = {{0,0},{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		Arduino my = new Arduino(0,0);
		ArrayList<Arduino> tmp = new ArrayList<Arduino>();
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j]=='I') {
					my.x = i; my.y = j;
				}
				else if(map[i][j]=='R') tmp.add(new Arduino(i, j));
			}
		}//end input.
		
		Arduino[] enemyList = new Arduino[tmp.size()];
		for(int i=0; i<tmp.size(); i++) {
			enemyList[i] = tmp.get(i);
		}//end make enemyList.
		
		String order = br.readLine();
		
		int time = -1;
		
ex:	for(int test=1; test<=order.length(); test++) {
			int direct = order.charAt(test-1) - '0';
			
			my.x += direction[direct][0];
			my.y += direction[direct][1];
			
			if(map[my.x][my.y] == 'R') {
				time = test;
				break ex;
			}//��ģ�Ƶ��̳븦 �������� �����°��.
			
			map[my.x-direction[direct][0]][my.y-direction[direct][1]] = '.';
			map[my.x][my.y] = 'I';
			
			HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
			int[][] nextEnemy = new int[enemyList.length][2];
			
			for(int i=0; i<enemyList.length; i++) {
				Arduino enemy = enemyList[i];
				
				if(enemy.x == -1) continue;
				
				int min = Integer.MAX_VALUE;
				int idx = 0;
				
				for(int k=1; k<10; k++) {
					if(k==5) continue;
					int dist = Math.abs(my.x - (enemy.x + direction[k][0])) + Math.abs(my.y - (enemy.y + direction[k][1]));
					if(dist < min) {
						min = dist;
						idx = k;
					}
				}//end for3.
				int nx = enemy.x + direction[idx][0];
				int ny = enemy.y + direction[idx][1];
				if(nx == my.x && ny == my.y) {
					time = test;
					break ex;
				}//��ģ �Ƶ��̳밡 �������� �� �Ƶ��̳븦 ������ ���.
				
				String key = nx+","+ny;
				if(hashmap.containsKey(key)) {
					int prev = hashmap.get(key);
					nextEnemy[i][0] = -1;
					nextEnemy[prev][0] = -1;
				}//���� �� ��ǥ�� ��ģ�Ƶ��̳밡 �����.
				else {
					hashmap.put(key, i);
					nextEnemy[i][0] = nx;
					nextEnemy[i][1] = ny;
				}//�� ��ǥ�� ���� ������ �������.
			}//end for2.
			
			for(int i=0; i<enemyList.length; i++) {
				if(enemyList[i].x == -1) continue;
				map[enemyList[i].x][enemyList[i].y] = '.';
				enemyList[i].x = nextEnemy[i][0];
				enemyList[i].y = nextEnemy[i][1];
				if(enemyList[i].x != -1) {
					map[enemyList[i].x][enemyList[i].y] = 'R';
				}
			}//end nextEnemy.
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println("");
			}
			System.out.println(test + "------------------");
			
		}//end for1.
		if(time != -1) System.out.println("kraj " + time);
		else {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println("");
			}
		}
	}//end main.
}//end class.
