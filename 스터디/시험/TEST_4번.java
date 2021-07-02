package algorithm;

import java.io.*;
import java.util.*;

public class TEST_4번 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		String[] statements = new String[M];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			statements[i] = st.nextToken();
		}//end input.
		int answer = solve(statements);
		System.out.println("answer : " + answer);
	}//end main.
	
	private static int solve(String[] statements) {
		int idx = 0;
		for(int i=0; i<statements.length; i++) {
			statements[i] = reverse(statements[i]);
			if(check(statements)) {
				idx = i;
				break;
			}
			statements[i] = reverse(statements[i]);
		}
		return idx;
	}//end solve.
	
	private static String reverse(String s) {
		String reverse = "";
		reverse += s.charAt(1);
		reverse += s.charAt(0);
		return reverse;
	}//end reverse.
	
	private static boolean check(String[] statements) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for(int i=0; i<26; i++) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(i);
			map.put(i, tmp);
		}//end initialize.
		
		boolean can_end = true;
		for(String statement : statements) {
			int L = statement.charAt(0) - 'A';
			int R = statement.charAt(1) - 'A';
			ArrayList<Integer> pL = map.get(L);
			if(pL.contains(R)) {
				can_end = false; 
				break;
			}//비교. 왼쪽 값보다 위에있는 값들 중에 오른쪽값이 들어있는지.
			ArrayList<Integer> pR = map.get(R);
			for(int i=0; i<pL.size(); i++) {
				pR.add(pL.get(i));
			}
		}//end output.
		return can_end;
	}//end check.

}//end class.
