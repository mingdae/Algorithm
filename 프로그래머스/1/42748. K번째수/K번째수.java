import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int arrLen = array.length;
        int cmdLen = commands.length;
        int[] answer = new int[cmdLen];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < cmdLen; i++){
            pq.clear();
            int start = commands[i][0] - 1;
            int end  = commands[i][1] - 1;
            int k = commands[i][2] - 1;
            for(int idx = start; idx <= end; idx++){
                pq.offer(array[idx]);
            }
            
            while(k > 0){
                pq.poll();
                k--;
            }
            answer[i] = pq.peek();
        }
        return answer;
    }
}