package maze;

import java.util.*;

public class GridGraph {

    List<List<Integer>> adjacencyList;
    int total;

    public GridGraph(int width, int height) {
        total = height * width;
        adjacencyList = new ArrayList<>(total);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                List<Integer> edges = new ArrayList<>(4);
                int vertexIndex = i * width + j;
                if (i > 0) {
                    edges.add(vertexIndex - width);
                }
                if (j > 0) {
                    edges.add(vertexIndex - 1);
                }
                if (i < height - 1) {
                    edges.add(vertexIndex + width);
                }
                if (j < width - 1) {
                    edges.add(vertexIndex + 1);
                }
                adjacencyList.add(edges);
            }
        }
    }

    public GraphEdge[] getSnappingTree() {
        Random r = new Random();
        int startPoint = r.nextInt(total);
        boolean[] connected = new boolean[total];
        connected[startPoint] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint);
        int i = 0;

        GraphEdge[] edges = new GraphEdge[total - 1];
        while (total > i + 1) {
            int v = stack.pop();
            List<Integer> a = adjacencyList.get(v);
            Collections.shuffle(a, r);
            for (int k: a) {
                if (!connected[k]) {
                    connected[k] = true;
                    edges[i] = new GraphEdge(v, k);
                    stack.push(k);
                    i++;
                }
            }
        }
        return edges;
    }
}
