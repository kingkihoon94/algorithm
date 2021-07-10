package algorithm;

import java.io.*;
import java.util.*;

public class Çö´ë_3¹ø {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double time = System.currentTimeMillis();
		
		for(int testcase=999999; testcase<=1000000; testcase++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(2);
			list.add(4);
			list.add(5);
			list.add(6);
			int N = testcase;
			int idx = 1;
			int start = 7;
			while(list.size()<N) {
				int right = list.get(idx+1);
				for(int i=start+1; i<start+right; i++) {
					list.add(i);
				}
				start +=right;
				idx++;
			}//end while.
			System.out.println(N + " : " + list.get(N-1));
		}//end TestCase.
		
		System.out.println(System.currentTimeMillis() - time);
	}//end main.
}//end class.

