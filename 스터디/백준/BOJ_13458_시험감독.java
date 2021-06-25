import java.io.*;
import java.util.*;


public class BOJ_13458_시험감독 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] tList = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tList[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int mSupervisor = Integer.parseInt(st.nextToken());
		int sSupervisor = Integer.parseInt(st.nextToken());
		
		long answer = 0L;
		for(int tester : tList) {
			answer++;
			if(tester-mSupervisor <= 0) continue;
			else if((tester-mSupervisor)%sSupervisor == 0) answer += (long)((tester-mSupervisor)/sSupervisor);
			else answer += (long)((tester-mSupervisor)/sSupervisor + 1);
		}//end for.
		
		System.out.println(answer);
	}//end main.
}//end class.
