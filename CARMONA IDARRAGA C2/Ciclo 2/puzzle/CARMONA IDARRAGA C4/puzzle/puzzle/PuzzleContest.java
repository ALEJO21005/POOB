package puzzle;

import java.util.*;

public class PuzzleContest {
    private static final int N = 505;

    private int[][] a = new int[N][N];
    private int[][] b = new int[N][N];
    private int[][] c = new int[N][N];
    private int[][] d = new int[N][N];
    private int[][] e = new int[N][N];
    private int[][] id = new int[N][N];
    private int[] p = new int[N * N];
    private int[] v1 = new int[N * N];
    private int[] v2 = new int[N * N];
    private int[] s1 = new int[N * N];
    private int[] s2 = new int[N * N];
    private int[] nxt = new int[N * N];
    private boolean[] vis = new boolean[N * N];
    private int n, m;
    private List<Character> moves = new ArrayList<>();

    public boolean solve(char[][] start, char[][] target) {
        n = start.length;
        m = start[0].length;

        // Fill matrix c with start configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (start[i][j] != '.') {
                    c[i][j] = start[i][j] - 'a' + 1;
                }
            }
        }

        // Fill matrix d with target configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (target[i][j] != '.') {
                    d[i][j] = target[i][j] - 'a' + 1;
                }
            }
        }

        System.arraycopy(c, 0, a, 0, c.length);
        if (check()) return true;

        // Try all four directions
        for (char op : new char[]{'L', 'D', 'R', 'U'}) {
            System.arraycopy(c, 0, a, 0, c.length);
            tilt(op);
            if (check()) {
                moves.add(op);
                return true;
            }
        }

        // Try combinations
        for (int o = 1; o <= 3; o += 2) {
            for (int s = 0; s < 4; s++) {
                System.arraycopy(c, 0, a, 0, c.length);
                int nowd = s;
                for (int i = 1; i <= 6; i++) {
                    tilt(new char[]{'L', 'D', 'R', 'U'}[nowd]);
                    moves.add(new char[]{'L', 'D', 'R', 'U'}[nowd]);
                    nowd = (nowd + o) % 4;
                    if (i >= 2 && same()) {
                        return true;
                    }
                }

                System.arraycopy(a, 0, e, 0, a.length);
                int tot = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (a[i][j] != 0) {
                            tot++;
                            v1[tot] = a[i][j];
                            v2[tot] = d[i][j];
                            a[i][j] = id[i][j] = tot;
                        }
                    }
                }
                for (int i = 1; i <= 4; i++) {
                    tilt(new char[]{'L', 'D', 'R', 'U'}[nowd]);
                    nowd = (nowd + o) % 4;
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (a[i][j] != 0) {
                            p[id[i][j]] = a[i][j];
                        }
                    }
                }
                Arrays.fill(vis, false);
                boolean cando = true;
                for (int i = 1; i <= tot; i++) {
                    if (vis[i]) continue;
                    int now = i, len = 0;
                    while (!vis[now]) {
                        len++;
                        s1[len] = v1[now];
                        s2[len] = v2[now];
                        vis[now] = true;
                        now = p[now];
                    }
                    for (int j = 2; j <= len; j++) {
                        int k = nxt[j - 1];
                        while (k > 0 && s2[j] != s2[k + 1]) k = nxt[k];
                        if (s2[j] == s2[k + 1]) k++;
                        nxt[j] = k;
                    }
                    int fir = 0, sec = 0, k = 0;
                    for (int j = 1; j <= len * 2 - 1; j++) {
                        int nowc = (j <= len ? s1[j] : s1[j - len]);
                        while (k > 0 && nowc != s2[k + 1]) k = nxt[k];
                        if (nowc == s2[k + 1]) k++;
                        if (k == len) {
                            if (fir == 0) fir = j;
                            else {
                                sec = j;
                                break;
                            }
                            k = nxt[k];
                        }
                    }
                    if (fir == 0) {
                        cando = false;
                        break;
                    }
                    int nn = len, mm = fir - len;
                    if (sec > 0) {
                        nn = sec - fir;
                        mm = fir - len;
                    }
                    for (int p = 2; p <= nn; p++) {
                        if (nn % p == 0) {
                            int tmp = 1;
                            while (nn % p == 0) {
                                nn /= p;
                                tmp *= p;
                            }
                        }
                    }
                    if (!cando) break;
                }
                if (cando) {
                    System.out.println("OK");
                    return true;
                }
            }
            moves.clear();
        }
        return false;
    }


    private void tilt(char op) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(b[i], 0);
        }
        if (op == 'L') {
            for (int i = 0; i < n; i++) {
                int len = 0;
                for (int j = 0; j < m; j++) {
                    if (a[i][j] != 0) {
                        b[i][len++] = a[i][j];
                    }
                }
            }
        } else if (op == 'R') {
            for (int i = 0; i < n; i++) {
                int len = m;
                for (int j = m - 1; j >= 0; j--) {
                    if (a[i][j] != 0) {
                        b[i][--len] = a[i][j];
                    }
                }
            }
        } else if (op == 'U') {
            for (int j = 0; j < m; j++) {
                int len = 0;
                for (int i = 0; i < n; i++) {
                    if (a[i][j] != 0) {
                        b[len++][j] = a[i][j];
                    }
                }
            }
        } else if (op == 'D') {
            for (int j = 0; j < m; j++) {
                int len = n;
                for (int i = n - 1; i >= 0; i--) {
                    if (a[i][j] != 0) {
                        b[--len][j] = a[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(b[i], 0, a[i], 0, m);
        }
    }

    private boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != d[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean same() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((a[i][j] == 0) != (d[i][j] == 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void simulate(char[][] starting, char[][] ending) {
        System.out.println(solve(starting, ending));
        Puzzle puzzle = new Puzzle(starting, ending);
        System.out.println(moves);
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(moves.get(i));
        }
        puzzle.makeVisible();
    }

    // start {{'.','r','.','.'}, {'r','g','y','b'}, {'.','b','.','.'}, {'.','y','r','.'}}
    // end {{'y','r','b','r'}, {'.','.','y','r'}, {'.','.','.','g'}, {'.','.','.','b'}}
}
