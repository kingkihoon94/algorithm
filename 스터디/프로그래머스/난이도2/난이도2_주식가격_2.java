import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];
        Stack<Integer> stack = new Stack();
        int time = 0;
        while(time != N){
            if(stack.isEmpty()){
                stack.push(time);
                time++;
            }
            else{
                int now = stack.peek();
                if(prices[time] >= prices[now]){
                    stack.push(time);
                    time++;
                }
                else{
                    answer[now] = time-now;
                    stack.pop();
                }
            }
        }//end while.
        
        while(!stack.isEmpty()){
            int now = stack.pop();
            //System.out.println(now);
            answer[now] = N-now-1;
        }//end stack pop.
        
        return answer;
    }//end main.
}//end solution.
