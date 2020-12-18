package maze;

public class MazeBuilder {
    public static boolean[][] build(int width, int height) throws Exception {
        if (width <= 2 || height <= 2) {
            throw new Exception("Invalid width or height");
        }

        boolean[][] m = new boolean[height][width];
        if (width == 3) {
            m[1][0] = true;
            m[height - 2][2] = true;
            for (int i = 1; i < height - 1; i++) {
                m[i][1] = true;
            }
        } else if (height == 3) {
            m[0][1] = true;
            m[2][width - 2] = true;
            for (int i = 1; i < width - 1; i++) {
                m[1][i] = true;
            }
        } else {
            int gWidth = (width - 1)/ 2;
            int gHeight = (height - 1) / 2;
            GridGraph g = new GridGraph(gWidth, gHeight);
            GraphEdge[] edges = g.getSnappingTree();
            // entrance & exit
            m[1][0] = true;
            m[(gHeight - 1) * 2 + 1][width - 1] = true;
            if (width % 2 == 0) {
                // if width is even the thick of the left wall is 2
                m[(gHeight - 1) * 2 + 1][width - 2] = true;
            }
            // vertex is always an empty cell
            for (int i = 0; i < gHeight; i++) {
                for (int j = 0; j < gWidth; j++) {
                    m[i * 2 + 1][j * 2 + 1] = true;
                }
            }
            // edge means empty cell
            for (GraphEdge e: edges) {
                int first = Math.min(e.a, e.b);
                int second = Math.max(e.a, e.b);
                int posY = (first / gWidth) * 2 + 1;
                int posX = (first % gWidth) * 2 + 1;
                if (second - first == 1) {
                    posX++;
                } else {
                    posY++;
                }
                m[posY][posX] = true;
            }
        }
        return m;
    }
}
