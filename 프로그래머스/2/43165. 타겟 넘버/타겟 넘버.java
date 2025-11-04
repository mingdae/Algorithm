import java.util.*;
class Solution {
    public static int T, len, ans;
    public int solution(int[] numbers, int target) {
        T = target;
        len = numbers.length;
        dfs(numbers, 0, 0);
        return ans;
    }
    
    public static void dfs(int[] numbers, int value, int depth){
        if(depth == len){
            if(value == T){
                ans++;
            }
            return;
        }
        
        dfs(numbers, value + numbers[depth], depth+1);
        
        dfs(numbers, value - numbers[depth], depth+1);
            
    }
}