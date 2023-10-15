package astar;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Kayli
 */

/*
--SPECIFICATIONS--

15 x 15 (2d Array of Nodes) -- DONE
Each node has a 10% chance of being unpathable -- DONE
Display generated environment -- DONE
User selects starting node and end node -- DONE
If no findable path, say so
Happens in a loop
*/

/*
--STEPS--

-Create nodes, named in x y notations
-Add nodes to list
-Find a way to keep track of the nodes in the path
-Scanner for user, xy coordinate
-make closed list - hashmap

*/

/*
HASHMAP: An ARRAY of LINKED LISTS
*/

public class Main {
    
    private static Node[][] environment = new Node[15][15];
    private static int xStart = 0;
    private static int yStart = 0;
    private static int xEnd = 0;
    private static int yEnd = 0;
    private static Scanner scan = new Scanner(System.in);
    
    //?
    private static HashMap closedList = new HashMap();
    private static MinHeap openList = new MinHeap();


    public static void main(String[] args) {
        
        boolean done = false;
        
        makeEnvironment();
        print();
        
        while (!done) {
            
        System.out.println("\n\nPress 1 to choose a path or 0 to end.");
        int choice = scan.nextInt();
        if (choice == 0) {
            done = true;
        }
        
        System.out.println("\nPlease choose the x coordinate of your starting node: ");
        xStart = scan.nextInt();
        System.out.println("\nPlease choose the y coordinate of your starting node: ");
        yStart = scan.nextInt();
        System.out.println("\nPlease choose the x coordinate of your goal node: ");
        xEnd = scan.nextInt();
        System.out.println("\nPlease choose the y coordinate of your goal node: ");
        yEnd = scan.nextInt();
        
        

        }
    }
    
    public static void makeEnvironment() {
        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                
                //row column type
                if ((int)(Math.random() * 9) + 1 == 5) {
                    Node node = new Node(i, j, 1);
                    environment[i][j] = node;
                }
                else {
                    Node node = new Node(i, j, 0);
                    environment[i][j] = node;
                }
                
            }
        }
        
    }
    
    public static void print() {
        /*
        _   -> blank spot
        x   -> blocked node
        P   -> path

        */
        
        //ADD 0-15 coordinates
        
        for (int i = 0; i < 15; i++) {
            System.out.println("");
            for (int j = 0; j < 15; j++) {
                
                if (environment[i][j].getType() == 1) {
                    System.out.print("x" + " ");
                }
                else {
                    System.out.print("_" + " ");
                }

            }
        }
    }

}
