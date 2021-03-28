import java.util.*;
import java.io.*;

public class BOJ_3954_Brainfuck {
	
	static int[] memory;
	static char[] code;
	static char[] input;
	static int memory_length;
	static int code_length;
	static int input_length;
	static int pointer;
	static int code_idx;
	static int input_idx;
	static Stack<Integer> stack = new Stack<Integer>();
	static int[] pair_list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int test=0; test<T; test++) {
			st = new StringTokenizer(br.readLine());
			memory_length = Integer.parseInt(st.nextToken());
			code_length = Integer.parseInt(st.nextToken());
			input_length = Integer.parseInt(st.nextToken());
			memory = new int[memory_length];
			code = new char[code_length];
			pair_list = new int[code_length];
			
			String tmp = br.readLine();
			for(int i=0; i<code_length; i++) {
				code[i] = tmp.charAt(i);
				if(code[i] == '[') stack.push(i);
				//스택에 쌓기
				else if(code[i] == ']') {
					int left = stack.pop();
					pair_list[left] = i;
					pair_list[i] = left;
				}//스택을 빼며 '[' 와 짝짓기.
			}//코드 입력받기.
			tmp = br.readLine();
			input = tmp.toCharArray();
			//////////////////////기본 구조 잡기./////////////////
			
			pointer = 0;
			code_idx = 0;
			input_idx = 0;
			int time = 0;
			boolean flag = false;
			
			while(time <50000000) {
				start();
				if(code_idx == code_length) {
					flag = true;
					break;
				}//끝나는 조건.
				time++;
			}//end while.
			
			if(flag) System.out.println("Terminates");
			else {
				int loop_end = code_idx;
				while(time >= 0) {
					start();
					loop_end = loop_end < code_idx ? code_idx : loop_end;
					time--;
				}
				
				System.out.println("Loops " + pair_list[loop_end] + " " + loop_end);
			}
		}//end TestCase.
	}//end main.
	private static void start() {
		if(code[code_idx] == '-') {
			memory[pointer] = memory[pointer] == 0 ? 255 : memory[pointer] - 1;
		}
		else if(code[code_idx] == '+') {
			memory[pointer] = memory[pointer] == 255 ? 0 : memory[pointer] + 1;
		}
		else if(code[code_idx] == '<') {
			pointer = pointer == 0 ? memory_length -1 : pointer-1;
		}
		else if(code[code_idx] == '>') {
			pointer = pointer == memory_length - 1 ? 0 : pointer+1;
		}
		else if(code[code_idx] == '[') {
			if(memory[pointer] == 0) {
				code_idx = pair_list[code_idx];
			}
		}
		else if(code[code_idx] == ']') {
			if(memory[pointer] != 0) { //루프가 걸린것임.
				code_idx = pair_list[code_idx];
			}
		}
		else if(code[code_idx] == '.') {
			//System.out.println(memory[pointer]);
		}
		else {
			memory[pointer] = input_idx == input_length ? 255 : input[input_idx++];
		}
		code_idx++;
	}
}//end class.
