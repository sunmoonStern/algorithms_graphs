import java.util.*;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        int n = x.length;
        double[][] dist = new double[n][n];
        double[] cost = new double[n];
        double upperBound = 2. * Math.sqrt(2.) * Math.pow(10., 3);
        for (int i = 0; i < n; i++) {
            cost[i] = upperBound;
            for (int j = 0; j < n; j++) {
                dist[i][j] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
            }
        }
        cost[0] = 0.;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(new Point(i, cost[i]));
        }
        while (pq.size() > 0) {
            Point p = pq.poll();
            int i = p.getIndex();
            double c = p.getCost();
            result += c;
            for (int j = 0; j < n; j++) {
                if (pq.contains(new Point(j, cost[j])) && cost[j] > dist[i][j]) {
                    pq.remove(new Point(j, cost[j]));
                    cost[j] = dist[i][j];
                    pq.offer(new Point(j, cost[j]));
                }
            }
        }
        return result;
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
        System.out.println(minimumDistance(x, y));
    }
}

class Point implements Comparable<Point> {
    private int index;
    private double cost;

    public Point(int index, double cost) {
        this.index = index;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public double getCost() {
        return cost;
    }

    public int compareTo(Point p) {
        return Double.compare(this.cost, p.getCost());
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Point point = (Point) object;
        return index == point.index &&
                java.lang.Double.compare(point.cost, cost) == 0;
    }

    public int hashCode() {
        return Objects.hash(index, cost);
    }
}

