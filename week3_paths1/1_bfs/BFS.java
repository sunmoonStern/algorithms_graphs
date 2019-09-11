import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayDeque;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        int n = adj.length;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = n;
        }
        // s is the origin
        Queue<Integer> q = new ArrayDeque<Integer>();
        dist[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int j = 0; j < adj[u].size(); j++) {
                int v = adj[u].get(j);
                if (dist[v] == n) {
                    q.add(v);
                    dist[v] = dist[u] + 1;
                }
            }
        }
        if (dist[t] == n) dist[t] = -1;
        return dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

