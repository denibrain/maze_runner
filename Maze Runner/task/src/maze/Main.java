package maze;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String v[] = s.split(" ");

        int height = Integer.parseInt(v[0]);
        int width = Integer.parseInt(v[1]);


        try {
            Maze m = new Maze(width, height);
            //m.print();
            System.out.print(m);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
