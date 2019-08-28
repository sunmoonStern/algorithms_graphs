import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayDeque;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = n;
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        int s = 0;
        dist[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int j = 0; j < adj[u].size(); j++) {
                int v = adj[u].get(j);
                if (dist[v] == n) {
                    q.add(v);
                    dist[v] = dist[u] + 1;
                } else {
                    if ((dist[u] - dist[v]) % 2 == 0) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

