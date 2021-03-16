import java.io.*;
import java.util.*;

public class Test {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		System.out.println("hello " + N);
		System.out.println("이것은 첫번쨰 " + N);
	}//end Main.
}//end Class.

