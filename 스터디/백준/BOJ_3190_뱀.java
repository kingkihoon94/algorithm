import java.util.*;
import java.awt.Point;
import java.io.*;

public class BOJ_3190_뱀 {
	
	static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1]; //최대 101*101. (가지고노는건 100*100)
		int apple = Integer.parseInt(br.readLine());
		for(int i=0; i<apple; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}//end for.
		int direct = Integer.parseInt(br.readLine());
		int[][] direct_list = new int[direct][2];
		for(int i=0; i<direct; i++) {
			st = new StringTokenizer(br.readLine());
			direct_list[i][0] = Integer.parseInt(st.nextToken());
			char type = st.nextToken().charAt(0);
			if(type == 'L') direct_list[i][1] = -1;
			else direct_list[i][1] = 1;
		}//end for.
		
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(1,1));
		map[1][1] = 2;
		int snake_direct = 1; //오른쪽 시작.
		int sx = 1;
		int sy = 1;
		int flag = 0;
		int time = 0;
		while(true) {
			sx += direction[snake_direct][0];
			sy += direction[snake_direct][1];
			time++; //움직이면서 타임 증가.
			if(sx == 0 || sx == N+1 || sy == 0 || sy == N+1 || map[sx][sy] == 2) break;
			if(map[sx][sy] == 0) {
				Point p = q.poll();
				map[p.x][p.y] = 0; 
			}//길이 안늘어날경우.
			q.add(new Point(sx,sy));
			map[sx][sy] = 2;
			if(time == direct_list[flag][0]) {
				snake_direct += direct_list[flag++][1];
				if(snake_direct ==-1) snake_direct = 3;
				else if(snake_direct ==4) snake_direct = 0;
				if(flag == direct_list.length) flag = 0; //더이상 회전이 일어나지 않게하는 처리.
			}//end if.
		}//end while.
		System.out.println(time);
	}//end main.
}//end class.
