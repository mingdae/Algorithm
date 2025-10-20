import java.io.*;
import java.util.*;

public class Main {
    static int N, len;
    static int[] arr, tails, idxArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tails = new int[N];
        idxArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for(int i = 0 ; i < N; i++){
            int pos = Arrays.binarySearch(tails, 0, len, arr[i]);
            if(pos < 0) pos = -(pos+1);
            tails[pos] = arr[i];
            idxArr[i] = pos;
            if(pos == len)  len++;
        }
        
        int[] res = new int[len];
        int k = len - 1;
        for(int i = N - 1; i >= 0; i--){
            if(idxArr[i] == k){
                res[k] = arr[i];
                k--;
            }
        }

        System.out.println(len);
        StringBuilder sb = new StringBuilder();
        for(int x : res){
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());

        
    }
}
