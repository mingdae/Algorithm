package btest.전기차여행;

import java.util.*;

class UserSolution {

    static final int MAX_N = 500;
    static final int MAX_B = 300;
    static final int INF = 0x3f3f3f3f;

    static class Road {
        int id, to, time, power;
        Road(int id, int to, int time, int power) {
            this.id = id; this.to = to; this.time = time; this.power = power;
        }
    }

    static class VirusState {
        int time, u;
        VirusState(int time, int u) { this.time = time; this.u = u; }
    }

    static class CarState {
        int time, u, battery;
        CarState(int time, int u, int battery) { this.time = time; this.u = u; this.battery = battery; }
    }

    // 자료구조
    Map<Integer, Integer> hash = new HashMap<>();   // roadId -> startCity
    List<Road>[] graph;                             // 인접 리스트
    int[] charge;                                   // 각 도시의 충전량
    int n;                                          // 도시 수

    @SuppressWarnings("unchecked")
    public void init(int N, int[] mCharge, int K, int[] mId, int[] sCity, int[] eCity, int[] mTime, int[] mPower) {
        this.n = N;
        hash.clear();
        graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();
        charge = new int[n];
        System.arraycopy(mCharge, 0, charge, 0, n);

        for (int i = 0; i < K; ++i) {
            graph[sCity[i]].add(new Road(mId[i], eCity[i], mTime[i], mPower[i]));
            hash.put(mId[i], sCity[i]);
        }
    }

    public void add(int mId, int sCity, int eCity, int mTime, int mPower) {
        graph[sCity].add(new Road(mId, eCity, mTime, mPower));
        hash.put(mId, sCity);
    }

    public void remove(int mId) {
        Integer sCity = hash.get(mId);
        if (sCity == null) return;
        List<Road> lst = graph[sCity];
        for (int i = 0; i < lst.size(); ++i) {
            if (lst.get(i).id == mId) {
                lst.remove(i);
                return;
            }
        }
    }

    public int cost(int B, int sCity, int eCity, int M, int[] mCity, int[] mTime) {
        // 1) 바이러스 도달 시간 (다원소 시작점 Dijkstra)
        int[] virusDist = new int[n];
        Arrays.fill(virusDist, INF);

        PriorityQueue<VirusState> virusPQ = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        for (int i = 0; i < M; ++i) {
            virusDist[mCity[i]] = mTime[i];
            virusPQ.offer(new VirusState(mTime[i], mCity[i]));
        }

        while (!virusPQ.isEmpty()) {
            VirusState cur = virusPQ.poll();
            int time = cur.time, u = cur.u;
            if (virusDist[u] < time) continue;

            for (Road rd : graph[u]) {
                int v = rd.to;
                int nextTime = time + rd.time;
                if (virusDist[v] > nextTime) {
                    virusDist[v] = nextTime;
                    virusPQ.offer(new VirusState(nextTime, v));
                }
            }
        }

        // 2) 자동차 이동 (상태: (도시, 배터리), 비용: 시간)
        // dist[u][b] = 시간의 최소값
        int[][] dist = new int[n][B + 1];
        for (int i = 0; i < n; ++i) Arrays.fill(dist[i], INF);

        PriorityQueue<CarState> carPQ = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        dist[sCity][B] = 0;
        carPQ.offer(new CarState(0, sCity, B));

        while (!carPQ.isEmpty()) {
            CarState cur = carPQ.poll();
            int time = cur.time, u = cur.u, battery = cur.battery;

            if (dist[u][battery] < time) continue;
            if (u == eCity) return time;

            // 도로 이동
            for (Road rd : graph[u]) {
                if (battery >= rd.power) {
                    int v = rd.to;
                    int nextTime = time + rd.time;
                    if (virusDist[v] <= nextTime) continue; // 바이러스 도달 시간보다 늦으면 불가

                    int nextBattery = battery - rd.power;
                    if (dist[v][nextBattery] > nextTime) {
                        // 지배 관계 최적화: nextBattery 이하도 nextTime으로 갱신
                        for (int b = nextBattery; b >= 0; --b) {
                            if (dist[v][b] < nextTime) break;
                            dist[v][b] = nextTime;
                        }
                        carPQ.offer(new CarState(nextTime, v, nextBattery));
                    }
                }
            }

            // 충전 (1초 소요)
            if (battery < B) {
                int nextTime = time + 1;
                if (virusDist[u] > nextTime) { // 충전 후 시점에도 바이러스 미도달이어야 함
                    int nextBattery = battery + charge[u];
                    if (nextBattery > B) nextBattery = B;

                    if (dist[u][nextBattery] > nextTime) {
                        for (int b = nextBattery; b >= 0; --b) {
                            if (dist[u][b] < nextTime) break;
                            dist[u][b] = nextTime;
                        }
                        carPQ.offer(new CarState(nextTime, u, nextBattery));
                    }
                }
            }
        }
        return -1;
    }
}
