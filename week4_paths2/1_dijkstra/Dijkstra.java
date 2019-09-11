import java.util.*;

public class Dijkstra {
    static final int MAX_DIST = 1000;
    static final int NILL = -1;
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = MAX_DIST;
            prev[i] = NILL;
        }
        dist[s] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(new Vertex(dist[i], i));
        }
        while (pq.size() > 0) {
            int node = pq.poll().getIndex();
            for (int i = 0; i < adj[node].size(); i++) {
                int v = adj[node].get(i);
                if (dist[node] + cost[node].get(i) < dist[v]) {
                    int oldDistV = dist[v];
                    dist[v] = dist[node] + cost[node].get(i);
                    prev[v] = node;
                    pq.remove(new Vertex(oldDistV, v));
                    pq.offer(new Vertex(dist[v], v));
                }
            }
        }
        if (dist[t] == MAX_DIST) {
            return -1;
        } else {
            return dist[t];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

class Vertex implements Comparable<Vertex> {
    private int dist;
    private int index;

    public Vertex(int dist, int index) {
        this.dist = dist;
        this.index = index;
    }

    public int getDist() {
        return dist;
    }

    public int getIndex() {
        return index;
    }

    public int compareTo(Vertex v) {
        return Integer.compare(this.dist, v.getDist());
    }
}

