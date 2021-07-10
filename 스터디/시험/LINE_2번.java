package algorithm;

import java.io.*;
import java.util.*;


public class LINE_2¹ø {
	
	static int[] cnt = new int[26];
	static ArrayList<Integer> AlphabetList = new ArrayList<Integer>();
	static ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
	static ArrayList<Integer> totalList = new ArrayList<Integer>();
	static boolean end = false;
	static String answer = "";
	
	public static void main(String[] args) throws IOException{
		long time = System.currentTimeMillis();
		for(int i=0; i<26; i++) {
			list.add(new HashSet<Integer>());
		}//end for.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for(int i=0; i<testcase; i++) {
			String tmp = br.readLine();
			for(int k1=0; k1<tmp.length()-1; k1++) {
				for(int k2=k1+1; k2<tmp.length(); k2++) {
					int c1 = tmp.charAt(k1) - 'A';
					int c2 = tmp.charAt(k2) - 'A';
					list.get(c1).add(c2);
					list.get(c2).add(c1);
				}//end for3.
			}//end for2.
		}//end for1.
		
		for(int i=0; i<26; i++) {
			if(list.get(i).size()>0) AlphabetList.add(i);
		}//end for1.
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		solve(0,N,K);
		System.out.println(answer);
		System.out.println(System.currentTimeMillis() - time);
		
	}//end main.
	
	private static void solve(int idx, int N, int K) {
		if(idx == AlphabetList.size()) {
			end = true;
			for(int i=0; i<totalList.size(); i++) {
				answer += (char)('A' + totalList.get(i));
			}
			return;
		}
		List<Integer> tmpList = new ArrayList<Integer>(list.get(AlphabetList.get(idx)));
		if(N==1) {
			for(int i=0; i<tmpList.size(); i++) {
				if(cnt[tmpList.get(i)]<K) {
					cnt[tmpList.get(i)]++;
					totalList.add(tmpList.get(i));
					solve(idx+1,N,K);
					totalList.remove(totalList.size()-1);
					cnt[tmpList.get(i)]--;
					if(end) return;
				}
			}
		}
		else if(N==2){
			for(int i=0; i<tmpList.size()-1; i++) {
				if(cnt[tmpList.get(i)]<K) {
					cnt[tmpList.get(i)]++;
					totalList.add(tmpList.get(i));
					for(int j=i+1; j<tmpList.size(); j++) {
						if(cnt[tmpList.get(j)]<K) {
							cnt[tmpList.get(j)]++;
							totalList.add(tmpList.get(j));
							solve(idx+1,N,K);
							totalList.remove(totalList.size()-1);
							cnt[tmpList.get(j)]--;
							if(end) return;
						}
					}
					totalList.remove(totalList.size()-1);
					cnt[tmpList.get(i)]--;
				}
			}
		}
		else {
			for(int i=0; i<tmpList.size()-2; i++) {
				if(cnt[tmpList.get(i)]<K) {
					cnt[tmpList.get(i)]++;
					totalList.add(tmpList.get(i));
					for(int j=i+1; j<tmpList.size()-1; j++) {
						if(cnt[tmpList.get(j)]<K) {
							cnt[tmpList.get(j)]++;
							totalList.add(tmpList.get(j));
							for(int k=j+1; k<tmpList.size(); k++) {
								if(cnt[tmpList.get(k)]<K) {
									cnt[tmpList.get(k)]++;
									totalList.add(tmpList.get(k));
									solve(idx+1,N,K);
									totalList.remove(totalList.size()-1);
									cnt[tmpList.get(k)]--;
									if(end) return;
								}
							}
							totalList.remove(totalList.size()-1);
							cnt[tmpList.get(j)]--;
						}
					}
					totalList.remove(totalList.size()-1);
					cnt[tmpList.get(i)]--;
				}
			}
		}
	}//end solve.
}//end class.



