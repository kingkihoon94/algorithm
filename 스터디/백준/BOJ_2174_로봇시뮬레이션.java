package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2174_�κ��ùķ��̼� {
	
	static class Robot{
		int x;
		int y;
		int direct;
		public Robot(int x, int y, int direct) {
			this.x = x;
			this.y = y;
			this.direct = direct;
		}//Constructor.
	}//Class Robot.
	
	static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		//�� ũ�� �ޱ�.
		
		st = new StringTokenizer(br.readLine());
		int robots = Integer.parseInt(st.nextToken());
		int orders = Integer.parseInt(st.nextToken());
		//�κ��� ������ ����� ���� �ޱ�.
		
		Robot[] robot_list = new Robot[robots+1]; 
		for(int i=1; i<=robots; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp_x = Integer.parseInt(st.nextToken());
			int tmp_y = Integer.parseInt(st.nextToken());
			int dx = N - tmp_y;
			int dy = tmp_x -1;
			//���� ��ǥ�� �ٲ��ֱ�.
			char tmp = st.nextToken().charAt(0);
			int direct = 0; 
			if(tmp == 'N') direct = 0;
			else if(tmp == 'E') direct = 1;
			else if(tmp == 'S') direct = 2;
			else direct = 3;
			robot_list[i] = new Robot(dx,dy,direct);
			map[dx][dy] = i; //�ʿ� �κ� �ѹ� ǥ���ϱ�.
		}//�κ�������ŭ ������ǥ�� �ٶ󺸴� ���� �ޱ�.
		
		String answer = "OK";
		
		for(int i=0; i<orders; i++) {
			st = new StringTokenizer(br.readLine());
			if(!answer.equals("OK")) continue;
			int num = Integer.parseInt(st.nextToken());
			char type = st.nextToken().charAt(0);
			int dist = Integer.parseInt(st.nextToken());
			
			if(type == 'L') {
				int cnt = dist%4;
				robot_list[num].direct -= cnt;
				if(robot_list[num].direct < 0) robot_list[num].direct += 4;
			}//�������� ȸ�� ���.
			else if(type == 'R') {
				int cnt = dist%4;
				robot_list[num].direct += cnt;
				if(robot_list[num].direct > 3) robot_list[num].direct -= 4;
			}//���������� ȸ�� ���.
			else {
				int sx = robot_list[num].x;
				int sy = robot_list[num].y;
				int direct = robot_list[num].direct;
				map[sx][sy] = 0;
				int nx = sx + direction[direct][0] * dist;
				int ny = sy + direction[direct][1] * dist;
				
				int time = 0;
				while(time < dist) {
					sx += direction[direct][0];
					sy += direction[direct][1];
					if(sx<0 || sx>=N || sy<0 || sy>=M) {
						answer = error(1,num,0);
						break;
					}
					else if(map[sx][sy] != 0) {
						answer = error(2,num,map[sx][sy]);
						break;
					}
					time++;
				}//end while.
				if(time == dist) {
					map[sx][sy] = num;
					robot_list[num].x = sx;
					robot_list[num].y = sy;
				}
				//�Ѵܰ辿 �����ϸ� �ε����� ���� �ִ��� �ľ��Ѵ�.
			}//dist��ŭ �����̱�.
		}//��ɰ�����ŭ �Է¹޾� �׽�Ʈ�غ���.
		System.out.println(answer);
	}//end main.
	
	private static String error(int type, int num1, int num2) {
		StringBuilder sb = new StringBuilder();
		if(type == 1) {
			sb.append("Robot ").append(num1).append(" crashes into the wall");
		}
		else {
			sb.append("Robot ").append(num1).append(" crashes into robot ").append(num2);
		}
		return sb.toString();
	}
}//end class.
