import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        for (int i = 0; i < adj.length; i++) {
            Deque<Integer> visitedNodes = new ArrayDeque<Integer>();
            visitedNodes.push(i);
            int res = DFS(i, adj, visitedNodes);
            if (res == 1) return 1;
        }
        return 0;
    }

    private static int DFS(int nodeIndex, ArrayList<Integer>[] adj, Deque<Integer> visitedNodes) {
        for (int i = 0; i < adj[nodeIndex].size(); i++) {
            int ngb = adj[nodeIndex].get(i);
            if (visitedNodes.contains(ngb)) return 1;
            visitedNodes.push(ngb);
            int res = DFS(ngb, adj, visitedNodes);
            if (res == 1) return 1;
            visitedNodes.pop();
        }
        return 0;
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
        System.out.println(acyclic(adj));
    }
}

