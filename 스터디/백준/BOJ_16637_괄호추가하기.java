import java.io.*;

public class BOJ_16637_��ȣ�߰��ϱ� {
	static int[] operand = new int[10]; //�ִ� 10��.
	static char[] operator = new char[9]; //�ִ� 9��.
	static int N; //����.
	static int answer = Integer.MIN_VALUE; //answer ���� ���� ������ �ʱ�ȭ.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		for(int i=0; i<N; i++) {
			if(i%2==0) operand[i/2] = line.charAt(i) - '0';
			else operator[i/2] = line.charAt(i);
		}
		dfs(0,operand[0]); //dfs ����, �Ķ����(���� idx , ���� sum).
		System.out.println(answer);
	}//end main.
	
	private static void dfs(int idx,  int sum) {
		if(idx == N/2) {
			answer = answer < sum ? sum : answer;
			return ;
		}//���� ����.
		
		dfs(idx+1 , cal(sum, operand[idx+1], operator[idx])); //��ȣ���Ұ��. ���� ����(1ĭ�̵�).

		if(idx+1 < N/2) {
			int right_num = cal(operand[idx+1] , operand[idx+2], operator[idx+1]); //��ȣ�Ұ��. ������ ���� ����� ���ʰ� ���. (2ĭ�̵�).
			dfs(idx+2 , cal(sum,right_num,operator[idx]));
		}//��ȣ�Ұ��. ��ȣ �Ϸ��� �ǳ� ����� �ƴϿ��� �Ѵ�.
	}//end dfs.
	
	private static int cal(int op1, int op2, char operator) {
		if(operator == '+') return op1 + op2;
		else if(operator == '-') return op1 - op2;
		else return op1 * op2;
	}//end cal.
	
}//end class.
