package btest.커피점과제과점;


import java.util.*;

class UserSolution {

    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    private int N;
    private ArrayList<Edge>[] graph;
    private static final long INF = Long.MAX_VALUE / 4;

    // 1) 초기화: 그래프 구성
    public void init(int N, int K, int[] sBuilding, int[] eBuilding, int[] mDistance) {
        this.N = N;
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int u = sBuilding[i], v = eBuilding[i], w = mDistance[i];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
    }

    // 2) 간선 추가: 양방향
    public void add(int sBuilding, int eBuilding, int mDistance) {
        graph[sBuilding].add(new Edge(eBuilding, mDistance));
        graph[eBuilding].add(new Edge(sBuilding, mDistance));
    }

    // 3) 계산: 멀티소스 다익스트라 2회 + 스캔
    public int calculate(int M, int[] mCoffee, int P, int[] mBakery, int R) {
        long[] distC = multiSourceDijkstra(mCoffee, M, R);
        long[] distB = multiSourceDijkstra(mBakery, P, R);

        Set<Integer> coffeeSet = new HashSet<>();
        Set<Integer> bakerySet = new HashSet<>();
        
        for(int i = 0 ; i < M; i++) {
        	coffeeSet.add(mCoffee[i]);
        }
        
        for(int i = 0 ; i < P; i++) {
        	bakerySet.add(mBakery[i]);
        }
        
        long best = INF;
        for (int v = 0; v < N; v++) {
        	if(coffeeSet.contains(v) || bakerySet.contains(v))
        		continue;
        	if (distC[v] <= R && distB[v] <= R) {       // 두 제한 모두 만족
                long sum = distC[v] + distB[v];
                if (sum < best) best = sum;
            }
        }
        return best == INF ? -1 : (int) best;           // 문제 조건상 int 범위 내
    }

    // 멀티소스 다익스트라: seeds 전체를 거리 0으로 시작
    private long[] multiSourceDijkstra(int[] seeds, int count, int R) {
        long[] dist = new long[N];
        Arrays.fill(dist, INF);

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        for (int i = 0; i < count; i++) {
            int s = seeds[i];
            if (dist[s] > 0) {          // 동일 ID 중복 방지
                dist[s] = 0;
                pq.offer(new long[]{s, 0});
            }
        }

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long du = cur[1];
            if (du != dist[u]) continue;        // stale 제거
            if (du >= R) continue;              // 양의 가중치 → 더 가봐야 R 초과

            for (Edge e : graph[u]) {
            	if(e.w > R)	continue;
                long nd = du + e.w;
                if (nd <= R && nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.offer(new long[]{e.to, nd});
                }
            }
        }
        return dist;
    }
}
