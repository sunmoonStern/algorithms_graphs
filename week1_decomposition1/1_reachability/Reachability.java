import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int cc = 1;
    private static int reach(ArrayList<Integer>[] adj, int x, int y, int[] connectedComponentsNum) {
        if (connectedComponentsNum[x] == connectedComponentsNum[y]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void doDepthFirstSearch(boolean[] visited, int[] connectedComponentsNum, ArrayList<Integer>[] adj) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                explore(i, visited, connectedComponentsNum, adj);
                cc += 1;
            }
        }
    }

    private static void explore(int x, boolean[] visited, int[] connectedComponentsNum, ArrayList<Integer>[] adj) {
        visited[x] = true;
        connectedComponentsNum[x] = cc;
        for (int y: adj[x]) {
            if (!visited[y]) {
                explore(y, visited, connectedComponentsNum, adj);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        boolean[] visited = new boolean[n];
        int[] connectedComponentsNum = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            visited[i] = false;
            connectedComponentsNum[i] = 0;
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
        doDepthFirstSearch(visited, connectedComponentsNum, adj);
        System.out.println(reach(adj, x, y, connectedComponentsNum));
    }
}

