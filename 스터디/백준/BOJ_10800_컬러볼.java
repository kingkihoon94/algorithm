package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_10800_�÷��� {
	
	static class Ball{
		int idx;
		int color;
		int weight;
		public Ball(int idx, int color, int weight) {
			this.idx = idx;
			this.color = color;
			this.weight = weight;
		}//Constructor.
	}//class Ball.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Ball> list = new ArrayList<Ball>();
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int weight= Integer.parseInt(st.nextToken());
			list.add(new Ball(i,color,weight));
		}//end for1. (input �ޱ�.)
		
		Collections.sort(list, new Comparator<Ball>() {
			@Override
            public int compare(Ball b1 , Ball b2) {
                return b1.weight - b2.weight;
            }
		});
		
		int[] answer = new int[N];
		int[] colorList = new int[N+1]; //������ 1���� �����ϹǷ� +1 ���� �ʿ�.
		int sum = 0;
		int prevIdx = 0;
		for(int i=0; i<N; i++) {
			Ball nowBall = list.get(i);
			Ball prevBall = list.get(prevIdx);
			while (prevBall.weight < nowBall.weight) {
                sum += prevBall.weight;  //���� �� ũ�� ����.
                colorList[prevBall.color] += prevBall.weight;  // �������� ���� ũ�� ����.
                prevBall = list.get(++prevIdx);
            }//���� ���� ���� ������ ������쿡��ó��.
			answer[nowBall.idx] = sum - colorList[nowBall.color];
		}//end for1.
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(answer[i]).append("\n");
		}//end StringBuilder.
		System.out.print(sb);
	}//end main.
}//end class.
