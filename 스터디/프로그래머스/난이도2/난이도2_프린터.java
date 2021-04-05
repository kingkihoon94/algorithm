import java.util.*;

class Solution {
    static class Print{
        int location;
        int priority;
        public Print(int location, int priority){
            this.location = location;
            this.priority = priority;
        }
    }//Class Print.
    
    public int solution(int[] priorities, int location) {
        Queue<Print> queue = new LinkedList<Print>();
        int answer = 0;
        for (int i = 0; i < priorities.length; i++) {
			queue.add(new Print(i, priorities[i])); //queue 초기화.
		}
        while(!queue.isEmpty()){
            boolean flag = false;
            Print tmp_print = queue.poll();
            for(Print p : queue){
                if(tmp_print.priority < p.priority){
                    flag = true;
                    break;
                }
            }//queue 내의 print 다 비교.
            if(flag){
                queue.add(tmp_print);
            }
            else{
                answer++;
                if(tmp_print.location == location) break;
            }
        }//end while.
        
        return answer;
    }//end main.
}//end solution.
