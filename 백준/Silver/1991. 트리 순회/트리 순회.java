import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Map<String, String> leftChild = new HashMap<>();
    static Map<String, String> rightChild = new HashMap<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        

        for(int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String lChild = st.nextToken();
            String rChild = st.nextToken();
            leftChild.put(root, lChild);
            rightChild.put(root, rChild);
        }
        preOrder("A");
        System.out.println();
        inOrder("A");
        System.out.println();
        postOrder("A");
        
    }

    public static void preOrder(String root){
        String lChild = leftChild.get(root);
        String rChild = rightChild.get(root);
        
        System.out.print(root);
        if(lChild != null && !lChild.equals(".")){
            preOrder(lChild);
        }
        if(rChild != null && !rChild.equals(".")){
            preOrder(rChild);    
        }
    }

    public static void inOrder(String root){
        String lChild = leftChild.get(root);
        String rChild = rightChild.get(root);

        if(lChild != null && !lChild.equals(".")){
            inOrder(lChild);
        }
        System.out.print(root);
        if(rChild != null && !rChild.equals(".")){
            inOrder(rChild);    
        }
    }

    public static void postOrder(String root){
        String lChild = leftChild.get(root);
        String rChild = rightChild.get(root);
        
        
        if(lChild != null && !lChild.equals(".")){
            postOrder(lChild);
        }
        if(rChild != null && !rChild.equals(".")){
            postOrder(rChild);    
        }
        System.out.print(root);
    }
}
