package SW_BTest;
import java.util.*;

class UserSolution {

    // ---------- 상태 ----------
    static int N;
    static int[][] map;

    // 구조물별 설치 후보 캐시: key = "M|v0,v1,..."
    static Map<String, List<Placement>> placementCache;

    // 하나의 설치 위치(연속 M칸 구간)
    static class Placement {
        int y, x;        // 시작 좌표
        boolean horiz;   // true: 가로 M칸, false: 세로 M칸
        boolean okOrig;  // 정방향 구조물로 설치 가능?
        boolean okRev;   // 역방향(뒤집기) 구조물로 설치 가능?
        Placement(int y, int x, boolean horiz, boolean okOrig, boolean okRev) {
            this.y = y; this.x = x; this.horiz = horiz; this.okOrig = okOrig; this.okRev = okRev;
        }
    }

    // ----------- API -----------
    public void init(int n, int[][] mMap) {
        N = n;
        map = new int[N][N];
        for (int i = 0; i < N; i++) System.arraycopy(mMap[i], 0, map[i], 0, N);
        placementCache = new HashMap<>();
    }

    public int numberOfCandidate(int M, int[] mStructure) {
        List<Placement> list = getPlacements(M, mStructure);
        return list.size(); // 같은 위치 집합은 1회만 카운트
    }

    public int maxArea(int M, int[] mStructure, int mSeaLevel) {
        List<Placement> list = getPlacements(M, mStructure);
        if (list.isEmpty()) return -1;

        int best = -1;
        final int len = M;

        for (Placement p : list) {
            // 정방향
            if (p.okOrig) {
                int target = getTargetAt(p, mStructure, false);
                int area = simulateSafeArea(p.y, p.x, len, p.horiz, target, mSeaLevel);
                if (area > best) best = area;
            }
            // 역방향(뒤집기)
            if (p.okRev) {
                int target = getTargetAt(p, mStructure, true);
                int area = simulateSafeArea(p.y, p.x, len, p.horiz, target, mSeaLevel);
                if (area > best) best = area;
            }
        }
        return best;
    }

    // ---------- 설치 후보 생성(캐시) ----------
    private List<Placement> getPlacements(int M, int[] s) {
        String key = keyOf(M, s);
        List<Placement> cached = placementCache.get(key);
        if (cached != null) return cached;

        List<Placement> res = new ArrayList<>();

        // M=1: 모든 칸 가능 (정/역 동일)
        if (M == 1) {
            for (int y = 0; y < N; y++)
                for (int x = 0; x < N; x++)
                    res.add(new Placement(y, x, true, true, true));
            placementCache.put(key, res);
            return res;
        }

        // 가로 구간 검사
        for (int y = 0; y < N; y++) {
            for (int x = 0; x <= N - M; x++) {
                boolean okOrig = canInstallSumCheck(y, x, true, s, false);
                boolean okRev  = canInstallSumCheck(y, x, true, s, true);
                if (okOrig || okRev) res.add(new Placement(y, x, true, okOrig, okRev));
            }
        }
        // 세로 구간 검사
        for (int y = 0; y <= N - M; y++) {
            for (int x = 0; x < N; x++) {
                boolean okOrig = canInstallSumCheck(y, x, false, s, false);
                boolean okRev  = canInstallSumCheck(y, x, false, s, true);
                if (okOrig || okRev) res.add(new Placement(y, x, false, okOrig, okRev));
            }
        }

        placementCache.put(key, res);
        return res;
    }

    // 설치 가능성: (map + 구조물)이 전부 같은 값 T가 되는지 직접 확인
    private boolean canInstallSumCheck(int sy, int sx, boolean horiz, int[] s, boolean reversed) {
        int M = s.length;
        // 첫 칸의 합을 기준 T로
        int firstH = map[sy][sx];
        int firstS = reversed ? s[M - 1] : s[0];
        int T = firstH + firstS;

        for (int k = 0; k < M; k++) {
            int y = horiz ? sy : sy + k;
            int x = horiz ? sx + k : sx;
            int sk = reversed ? s[M - 1 - k] : s[k];
            if (map[y][x] + sk != T) return false;
        }
        return true;
    }

    // (정/역)에 따른 target 고도 계산
    private int getTargetAt(Placement p, int[] s, boolean reversed) {
        if (reversed) return map[p.y][p.x] + s[s.length - 1];
        else          return map[p.y][p.x] + s[0];
    }

    private String keyOf(int M, int[] s) {
        StringBuilder sb = new StringBuilder();
        sb.append(M).append('|');
        for (int i = 0; i < M; i++) {
            if (i > 0) sb.append(',');
            sb.append(s[i]);
        }
        return sb.toString();
    }

    // ---------- 침수 시뮬레이션 ----------
    // 설치 구간: (sy,sx)부터 길이 len, 방향 horiz, 설치 후 해당 구간의 고도는 'target'
    // 바깥 경계에서 시작, 고도 <= (mSeaLevel - 1) 인 칸만 물이 연결되어 침수됨.
    // 안전 지역 = 침수되지 않은 모든 칸 수
    private int simulateSafeArea(int sy, int sx, int len, boolean horiz, int target, int mSeaLevel) {
        final int threshold = mSeaLevel - 1;
        boolean[][] vis = new boolean[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int flooded = 0;

        // 경계에서 시작
        for (int i = 0; i < N; i++) {
            if (!vis[i][0] && cellHeight(i, 0, sy, sx, len, horiz, target) <= threshold) {
                vis[i][0] = true; q.add(new int[]{i, 0}); flooded++;
            }
            if (!vis[i][N - 1] && cellHeight(i, N - 1, sy, sx, len, horiz, target) <= threshold) {
                vis[i][N - 1] = true; q.add(new int[]{i, N - 1}); flooded++;
            }
        }
        for (int j = 0; j < N; j++) {
            if (!vis[0][j] && cellHeight(0, j, sy, sx, len, horiz, target) <= threshold) {
                vis[0][j] = true; q.add(new int[]{0, j}); flooded++;
            }
            if (!vis[N - 1][j] && cellHeight(N - 1, j, sy, sx, len, horiz, target) <= threshold) {
                vis[N - 1][j] = true; q.add(new int[]{N - 1, j}); flooded++;
            }
        }

        // BFS 확산 (낮은 칸만)
        final int[] DY = {-1, 1, 0, 0};
        final int[] DX = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d], nx = x + DX[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                if (vis[ny][nx]) continue;
                if (cellHeight(ny, nx, sy, sx, len, horiz, target) <= threshold) {
                    vis[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                    flooded++;
                }
            }
        }

        // 안전 지역 = 전체 - 침수된 칸
        return N * N - flooded;
    }

    // 설치된 구간 내부면 target, 아니면 원본
    private int cellHeight(int y, int x, int sy, int sx, int len, boolean horiz, int target) {
        if (horiz) {
            if (y == sy && x >= sx && x < sx + len) return target;
        } else {
            if (x == sx && y >= sy && y < sy + len) return target;
        }
        return map[y][x];
    }
}