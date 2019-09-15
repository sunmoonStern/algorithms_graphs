import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    static final int MAX_DIST = 10000000;
    static final int NILL = -1;
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int n = adj.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = MAX_DIST;
            prev[i] = NILL;
        }
        dist[0] = 0;
        int indexLastUpdated = -1;
        for (int i = 0; i <= n; i++) {
            for (int u = 0; u < n; u++) {
                for (int j = 0; j < adj[u].size(); j++) {
                    int v = adj[u].get(j);
                    if (dist[v] > dist[u] + cost[u].get(j)) {
                        dist[v] = dist[u] + cost[u].get(j);
                        prev[v] = u;
                        if (i == n) {
                            indexLastUpdated = v;
                        }
                    }
                }
            }
        }
        if (indexLastUpdated == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

