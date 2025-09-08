package btest.택시호출서비스;
import java.util.*;

class UserSolution {

    // ----- 고정 파라미터 -----
    private static final int BUCKETS = 10; // 10x10 버킷

    // ----- 내부 타입 -----
    private static class Taxi {
        int x, y;
        int moveDist; // 픽업까지 + 라이드 누적
        int rideDist; // 라이드(손님 태운 뒤) 누적
        Taxi(int x, int y) { this.x = x; this.y = y; }
    }

    /** GET_BEST 용 힙 노드
     *  rideDist 내림차순, 같으면 idx 오름차순.
     *  stale 제거를 위해 rideSnapshot(스냅샷) 보관.
     */
    private static class Node implements Comparable<Node> {
        final int rideSnapshot; // 스냅샷용 rideDist
        final int idx;          // 택시 번호(1..M)
        Node(int rideSnapshot, int idx) { this.rideSnapshot = rideSnapshot; this.idx = idx; }
        @Override public int compareTo(Node o) {
            if (this.rideSnapshot != o.rideSnapshot)
                return Integer.compare(o.rideSnapshot, this.rideSnapshot); // 내림차순
            return Integer.compare(this.idx, o.idx); // 오름차순
        }
    }

    // ----- 상태 -----
    private int N, M, L;       
    private int divN;           // = L (버킷 셀 크기)
    private List<Integer>[][] buckets; // [by][bx] -> taxiNo(1..M)
    private Taxi[] taxis;             // 1..M
    private PriorityQueue<Node> heap; // GET_BEST용

    // ----- 유틸 -----
    private int bx(int x) { return Math.min(BUCKETS - 1, x / divN); } // 0..9
    private int by(int y) { return Math.min(BUCKETS - 1, y / divN); } // 0..9
    private static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public void init(int N, int M, int L, int[] mXs, int[] mYs)
    {
        this.N = N; this.M = M; this.L = L;
        this.divN = Math.max(1, L); // 안전장치

        // 10x10 버킷 초기화
        buckets = new ArrayList[BUCKETS][BUCKETS];
        for (int y = 0; y < BUCKETS; y++)
            for (int x = 0; x < BUCKETS; x++)
                buckets[y][x] = new ArrayList<>();

        // 택시/힙 초기화 (★ 1-based)
        taxis = new Taxi[M + 1];
        heap = new PriorityQueue<>();

        for (int i = 1; i <= M; i++) {
            int x = mXs[i - 1], y = mYs[i - 1];
            taxis[i] = new Taxi(x, y);
            buckets[by(y)][bx(x)].add(i);
            // rideDist 스냅샷으로 힙에 삽입(초기 0)
            heap.add(new Node(0, i));
        }
    }

    public int pickup(int mSX, int mSY, int mEX, int mEY)
    {
        int cbx = bx(mSX), cby = by(mSY);

        int bestIdx = -1;
        int bestDist = L + 1; // 후보는 dist ≤ L

        // 출발 버킷 주변 3x3 탐색 + 최종 dist ≤ L 필터
        for (int dy = -1; dy <= 1; dy++) {
            int nby = cby + dy;
            if (nby < 0 || nby >= BUCKETS) continue;
            for (int dx = -1; dx <= 1; dx++) {
                int nbx = cbx + dx;
                if (nbx < 0 || nbx >= BUCKETS) continue;

                for (int idx : buckets[nby][nbx]) {
                    Taxi t = taxis[idx];
                    int d = manhattan(t.x, t.y, mSX, mSY);
                    if (d > L) continue; // ★ L 초과 후보 제외
                    if (d < bestDist || (d == bestDist && idx < bestIdx)) {
                        bestDist = d;
                        bestIdx = idx;
                    }
                }
            }
        }

        if (bestIdx == -1) return -1; // 후보 없음

        Taxi ch = taxis[bestIdx];

        // 기존 버킷에서 제거
        buckets[by(ch.y)][bx(ch.x)].remove((Integer) bestIdx);

        // 이동/라이드 갱신
        int ride = manhattan(mSX, mSY, mEX, mEY);
        ch.moveDist += bestDist + ride;
        ch.rideDist += ride;

        // 목적지로 이동
        ch.x = mEX; ch.y = mEY;

        // 새 버킷에 등록 + GET_BEST 힙에 최신 스냅샷 삽입
        buckets[by(ch.y)][bx(ch.x)].add(bestIdx);
        heap.add(new Node(ch.rideDist, bestIdx));

        return bestIdx; // ★ 1-based ID 반환
    }

    public Solution.Result reset(int mNo)
    {
        Solution.Result res = new Solution.Result();

        if (mNo < 1 || mNo > M) return res;

        Taxi t = taxis[mNo];

        // ★ 리셋 전 상태를 반환
        res.mX = t.x;
        res.mY = t.y;
        res.mMoveDistance = t.moveDist;
        res.mRideDistance = t.rideDist;

        // 내부 상태 리셋
        t.moveDist = 0;
        t.rideDist = 0;

        // 힙 재등록(rideDist 스냅샷 0)
        heap.add(new Node(0, mNo));
        return res;
    }

    public void getBest(int[] mNos)
    {
        // 상위 mNos.length개: rideDist 내림차순, idx 오름차순
        Arrays.fill(mNos, -1);

        List<Node> backup = new ArrayList<>();
        int filled = 0;

        while (filled < mNos.length && !heap.isEmpty()) {
            Node top = heap.poll();
            Taxi cur = taxis[top.idx];

            if (cur == null) continue;

            // ★ stale 제거: 현재 rideDist와 스냅샷이 다르면 폐기
            if (cur.rideDist != top.rideSnapshot) continue;

            // 같은 호출 내 중복 방지
            boolean dup = false;
            for (int i = 0; i < filled; i++) {
                if (mNos[i] == top.idx) { dup = true; break; }
            }
            if (dup) { backup.add(top); continue; }

            mNos[filled++] = top.idx; // ★ 1-based로 채움
            backup.add(top);          // 다른 호출에서도 동일 결과가 되도록 복구
        }

        // 힙 복구
        for (Node n : backup) heap.add(n);
    }
}
