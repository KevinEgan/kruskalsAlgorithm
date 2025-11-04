import java.util.Arrays;
import java.util.Comparator;

class GfG {
    public static int kruskalsMST(int V, int[][] edges) {
        
        // Sort all edges based on weight
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        
        System.out.println("$$$$$$ Kruskal's Algorithm Execution");
        System.out.println("Edges sorted by weight: \n");
        for (int[] edge : edges) {
            System.out.println("Edge: " + edge[0] + " -> " + edge[1] + ". Weight: " + edge[2]);
        }
        System.out.println();
        
        // Traverse edges in sorted order
        DSU dsu = new DSU(V);
        int cost = 0, count = 0;
        
        System.out.println("Building MST:");
        for (int[] e : edges) {
            int x = e[0], y = e[1], w = e[2];
            
            // Make sure that there is no cycle
            if (dsu.find(x) != dsu.find(y)) {
                dsu.union(x, y);
                cost += w;
                count++;
                System.out.println("Added edge: " + x + " -> " + y + ". Weight: " + w + ". Total cost: " + cost);
                if (count == V - 1) {
                    break;
                }
            }
        }
        System.out.println();
        return cost;
    }

    public static void main(String[] args) {
        
        // An edge contains, weight, source and destination
        // $$$$$$ Input graph values here $$$$$$$$
        int[][] firstGraph = {
            {0, 1, 2}, {2, 3, 8}, {3, 5, 5}, {5, 6, 2}, {6, 1, 6}, {2, 4, 6}, {3, 4, 3}, {4, 5, 6}, {4, 6, 7}, {4, 1, 5}
        };

        int[][] secondGraph = {
            {0, 1, 4}, {1, 2, 8}, {2, 3, 7}, {3, 4, 9}, {4, 5, 10}, {5, 6, 2}, {6, 7, 1}, {7, 0, 8},
            {1, 7, 11}, {2, 8, 2}, {8, 6, 6}, {5, 2, 4}, {3, 5, 14}, {7, 8, 7}
        };

        System.out.println("First graph:" + kruskalsMST(7, firstGraph));
        System.out.println("Second graph:" + kruskalsMST(9, secondGraph));
    }
}

// Disjoint set data structure
class DSU {
    private int[] parent, rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int s1 = find(x);
        int s2 = find(y);
        if (s1 != s2) {
            if (rank[s1] < rank[s2]) {
                parent[s1] = s2;
            } else if (rank[s1] > rank[s2]) {
                parent[s2] = s1;
            } else {
                parent[s2] = s1;
                rank[s1]++;
            }
        }
    }
}