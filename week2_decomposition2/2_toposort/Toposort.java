import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < adj.length; i++) {
            dfs(adj, used, order, i);
        }
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        if (used[s] == 0) {
            explore(adj, used, order, s);
        }
    }

    private static void explore(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        used[s] = 1;
        for (int i = 0; i < adj[s].size(); i++) {
            int n = adj[s].get(i);
            if (used[n] == 0) {
                explore(adj, used, order, n);
            }
        }
        // post-visit
        order.add(0, s);
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
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

