package maze;

import java.io.Serializable;

public class Maze implements Serializable {
    private final boolean[][] map;
    private int width;
    private int height;

    public Maze(int width, int height) throws Exception {
        if (width <= 2 || height <= 2) {
            throw new Exception("Invalid width or height");
        }

        this.width = width;
        this.height = height;
        map = MazeBuilder.build(width, height);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row: map) {
           for (boolean b:row) {
               sb.append(b ? "  " : "\u2588\u2588");
           }
           sb.append("\n");
        }
        return sb.toString();
    }

    public void print() {
        for (boolean[] row: map) {
            StringBuilder sb = new StringBuilder();
            for (boolean b:row) {
                sb.append(b ? "  " : "\u2588\u2588");
            }
            System.out.println(sb.toString());
        }
    }
}
