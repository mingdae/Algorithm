package task;
import java.io.*;
import java.util.*;

/*
 * [문제 분석]
 * - N개 원자(1~1000), K에너지(1~100)
 * - 좌표(x, y) (-1000~1000)
 * - 상하좌우 이동, 한 방향으로만 계속 이동
 * - 충돌 시 보유한 에너지 방출, 소멸
 * - 원자들이 소멸하면서 방출하는 에너지의 총합 계산
 * - 시간 순서대로 진행 -> 0.5초 단위로 충돌 할 수도 있음
 * - 범위를 벗어나면 충돌 할 일 없음 -> 최대 범위, 최대 진행 시간 설정 가능
 * 
 * 
 * [문제 풀이]
 * - 시간 순서대로 진행하면서 좌표에 대한 hash 키값 생성
 * - HashMap 에서 카운트 -> 카운트가 2 이상이면 충돌
 * - 충돌 처리
 * 	 1) 에너지 값 total 에 더해주고
 *   2) 해당 원자에 충돌 표시(alive)
 * 
 * - 문제에서는 범위 제한이 없다고 하지만, 사실상 범위 밖을 나가면 충돌 할 일이 없음
 *   다시 돌아올 일도 X
 * 
 */


public class SW5648_AtomExtinction_오민재 {

    static class Atom {
        int x, y, dir, power;
        boolean alive = true;
        Atom(int x, int y, int dir, int power) {
            this.x = x; this.y = y; this.dir = dir; this.power = power;
        }
    }
    
    // 방향 상(0), 하(1), 좌(2), 우(3)
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};
    
    // 해시 키 값 만들기
    static final int LIMIT = 2000;
    static final int OFFSET = 2000;
    static final int W = 4001; 

    static int key(int x, int y) {
        return (x + OFFSET) * W + (y + OFFSET);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            List<Atom> atoms = new ArrayList<>(N);

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                
                // 0.5초 계산을 위한 *2처리 -> (-2000~2000)
                x *= 2;
                y *= 2;

                atoms.add(new Atom(x, y, dir, power));
            }

            long ans = simulate(atoms);
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static long simulate(List<Atom> atoms) {
        long total = 0L;
        int aliveCnt = atoms.size();

        int maxSteps = 4005;
        
        // 시간 순서로 진행, 최대 maxSteps(4005번), 충돌하지 않은 원자 없으면 종료
        for (int step = 0; step < maxSteps && aliveCnt > 0; step++) {
            HashMap<Integer, Integer> cnt = new HashMap<>(atoms.size() * 2);
            for (Atom a : atoms) {
            	// 이미 충돌한 원소
                if (!a.alive) continue;
                
                a.x += dx[a.dir];
                a.y += dy[a.dir];
                
                // 범위 밖을 넘은 원소는 충돌 처리
                if (a.x < -LIMIT || a.x > LIMIT || a.y < -LIMIT || a.y > LIMIT) {
                    a.alive = false;
                    aliveCnt--;
                    continue;
                }
                
                int key = key(a.x, a.y);
                
                // Map 안에서 Value값으로 카운트
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }

            if (aliveCnt <= 0) break;

            for (Atom a : atoms) {
                if (!a.alive) continue;
                int c = cnt.getOrDefault(key(a.x, a.y), 0);
                if (c >= 2) {
                    total += a.power;
                    a.alive = false;
                    aliveCnt--;
                }
            }
        }
        return total;
    }
}
