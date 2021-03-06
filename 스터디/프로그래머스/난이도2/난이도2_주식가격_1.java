import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (prices[j] < prices[i]) {
                    answer[i] = j-i;
                    break;
                }//작은경우 찾은경우.
                if (j==N-1) answer[i] = j-i;
            }
        }
        return answer;
    }
}
