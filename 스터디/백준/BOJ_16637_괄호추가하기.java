import java.io.*;

public class BOJ_16637_괄호추가하기 {
	static int[] operand = new int[10]; //최대 10개.
	static char[] operator = new char[9]; //최대 9개.
	static int N; //갯수.
	static int answer = Integer.MIN_VALUE; //answer 가장 작은 값으로 초기화.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		for(int i=0; i<N; i++) {
			if(i%2==0) operand[i/2] = line.charAt(i) - '0';
			else operator[i/2] = line.charAt(i);
		}
		dfs(0,operand[0]); //dfs 시작, 파라미터(현재 idx , 현재 sum).
		System.out.println(answer);
	}//end main.
	
	private static void dfs(int idx,  int sum) {
		if(idx == N/2) {
			answer = answer < sum ? sum : answer;
			return ;
		}//종료 조건.
		
		dfs(idx+1 , cal(sum, operand[idx+1], operator[idx])); //괄호안할경우. 순차 진행(1칸이동).

		if(idx+1 < N/2) {
			int right_num = cal(operand[idx+1] , operand[idx+2], operator[idx+1]); //괄호할경우. 오른쪽 먼저 계산후 왼쪽과 계산. (2칸이동).
			dfs(idx+2 , cal(sum,right_num,operator[idx]));
		}//괄호할경우. 괄호 하려면 맨끝 계산이 아니여야 한다.
	}//end dfs.
	
	private static int cal(int op1, int op2, char operator) {
		if(operator == '+') return op1 + op2;
		else if(operator == '-') return op1 - op2;
		else return op1 * op2;
	}//end cal.
	
}//end class.
