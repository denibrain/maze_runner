package maze;

import java.io.*;
import java.util.Scanner;

public class Manager {
    
    Maze maze;
    
    public Manager() {
        
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int code;
        do {
            displayMenu();
            code = scanner.nextInt();
            scanner.nextLine();
            switch (code) {
                case 0:
                    break;

                case 1:
                    System.out.println("Enter the size of a new maze");
                    int sideSize = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        maze = new Maze(sideSize, sideSize);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(maze);
                    break;
                case 2:
                    String fileName = scanner.nextLine();
                    loadMaze(fileName);
                    break;

                case 3:
                    String fileNameToSave = scanner.nextLine();
                    saveMaze(fileNameToSave);
                    break;

                case 4:
                    System.out.println(maze);
                    break;

                default:
                    System.out.println("Incorrect option. Please try again");
            }
        } while (code != 0);
    }

    private void saveMaze(String fileNameToSave) {
        try (FileOutputStream fout = new FileOutputStream(fileNameToSave);
             ObjectOutputStream oos =  new ObjectOutputStream(fout);
        ) {
            oos.writeObject(maze);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    private void loadMaze(String fileName) {
        try (
            FileInputStream streamIn = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(streamIn);
        ) {
            maze = (Maze) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.printf("The file %s does not exist%n", fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cannot load the maze. It has an invalid format");
        }
    }

    private void displayMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        System.out.println("2. Load a maze");
        if (maze != null) {
            System.out.println("3. Save the maze");
            System.out.println("4. Display the maze");
        }
        System.out.println("0. Exit");
    }


}
