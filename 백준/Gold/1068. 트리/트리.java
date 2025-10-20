import java.io.*;
import java.util.*;

/*
 * - 자기 자신을 포함해서 자신 아래 서브트리의 리프노드 개수 저장
 * - dfs 과정 중 리프노드를 만나면 
 *      => 나 자신 리프 노드개수 +1
 *      => dfs 리턴할때 부모에게 자식의 리프노드개수 누적
 * 
 * - 정답 = 전체 리프노드 개수 - 삭제 노드 서브트리 리프노드 개수
 * 
 */
public class Main {
    static int N, root, delete, ans;
    static ArrayList<Integer>[] graph;
    static int parent[], leafCnt[];
    static boolean[] visited;
    static Queue<Integer> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        visited = new boolean[N];
        parent = new int[N];
        leafCnt = new int[N];
        
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int node = 0; node < N; node++){
            int from = Integer.parseInt(st.nextToken());
            if(from == -1){
                root = node;
                continue;
            }    
            graph[from].add(node);
            graph[node].add(from);
            parent[node] = from;
        }

        delete = Integer.parseInt(br.readLine());
        dfs(root);
        
        System.out.println(leafCnt[root]);
    }

    public static void dfs(int start){
        if(start == delete) return;
        visited[start] = true;
        boolean isLeaf = true;
        for(int next : graph[start]){
            if(visited[next])   continue;
            if(next == delete)  continue;
            isLeaf = false;
            dfs(next);
            leafCnt[start] += leafCnt[next];
        }
        if(isLeaf)  {
            leafCnt[start]++;
        }

    }
}
