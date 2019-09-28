import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Clustering {
    private static int cc = 0;
    private static double clustering(int[] x, int[] y, int k) {
        double distanceClusters = 0.;
        int n = x.length; // needs to merge n - k times
        double[][] dist = new double[n][n];
        int[] clusterIndex = new int[n];
        double upperBound = 2. * Math.sqrt(2.) * Math.pow(10., 3);
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            clusterIndex[i] = -1;
            for (int j = 0; j < n; j++) {
                dist[i][j] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.offer(new Pair(i, j, dist[i][j]));
            }
        }
        for (int l = 0; l < n - k; l++) {
            // get edge from priority queue
            // check that they belong to different clusters
            // merge them
            int i, j;
            do {
                Pair p = pq.poll();
                i = p.getI();
                j = p.getJ();
            } while (isSameCluster(clusterIndex, i, j));
            mergeClusters(clusterIndex, adj, i, j);
        }
        int i, j;
        do {
            Pair p = pq.poll();
            i = p.getI();
            j = p.getJ();
            distanceClusters = p.getDistance();
        } while (isSameCluster(clusterIndex, i, j));
        return distanceClusters;
    }

    private static boolean isSameCluster(int[] clusterIndex, int i, int j) {
        if (clusterIndex[i] == -1 && clusterIndex[j] == -1) return false;
        return clusterIndex[i] == clusterIndex[j];
    }

    private static void doDepthFirstSearch(int[] clusterIndex, ArrayList<Integer>[] adj) {
        // declare visited and put false
        int n = clusterIndex.length;
        boolean[] visited = new boolean[n];
        cc = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                explore(i, visited, clusterIndex, adj);
                cc += 1;
            }
        }
    }

    private static void explore(int x, boolean[] visited, int[] clusterIndex, ArrayList<Integer>[] adj) {
        visited[x] = true;
        clusterIndex[x] = cc;
        for (int y: adj[x]) {
            if (!visited[y]) {
                explore(y, visited, clusterIndex, adj);
            }
        }
    }

    private static void mergeClusters(int[] clusterIndex, ArrayList<Integer>[] adj, int i, int j) {
        adj[i].add(j);
        adj[j].add(i);
        doDepthFirstSearch(clusterIndex, adj);
        return;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

class Pair implements Comparable<Pair> {
    private int i;
    private int j;
    private double distance;

    public Pair(int i, int j, double distance) {
        this.i = i;
        this.j = j;
        this.distance = distance;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public double getDistance() {
        return distance;
    }

    public int compareTo(Pair p) {
        return Double.compare(this.distance, p.getDistance());
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        // if (!super.equals(object)) return false;

        Pair pair = (Pair) object;

        if (i != pair.i) return false;
        if (j != pair.j) return false;
        if (java.lang.Double.compare(pair.distance, distance) != 0) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + i;
        result = 31 * result + j;
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
