import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int arrLen = citations.length;
        int answer = 0;
        Arrays.sort(citations);
        
        for(int i = 0; i < arrLen ; i++){
            int h = arrLen - i;
            
            if(citations[i] >= h){
                answer = h;
                break;
            }
        }
        return answer;
    }
}