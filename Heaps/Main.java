/*
* Name: Norman Cook
* Description: Implementation of a max heap and a min heap to store integers. This
* 	uses an integer array as the backing data structure.
*/
import java.util.Scanner;

public class Main {
  public static final char WALL = '@';
  public static final char INFO = '*';
  public static final char HERO = ';';
  public static final int SLEEP = 0;
  public static boolean mhw;
  public static int heroRow;
  public static int heroCol;
  public static int infoRow;
  public static int infoCol;
  public static int info2Row;
  public static int info2Col;
  
  public static boolean quit;
  public static Scanner scanner;
  
  public static char[][] map;
  public static boolean smh;
  public static boolean ihw;
  
  public static void main(String[] args) throws InterruptedException {
    // initialize map and hero coordinates
    map = new char[8][10*2];
    heroRow = 6;
    heroCol = 1;
    infoRow = 1;
    infoCol = 9;
    info2Row = 6;
    info2Col = 17;
    smh = false;
    mhw = true;
    ihw = true;
    
    quit = false;
    scanner = new Scanner(System.in);
    while (!quit) {
      clearScreen();
      draw();
      System.out.println();
      if (!smh && (heroRow == infoRow && heroCol == infoCol)) {
        smh = true;
      }
      else {
        menu();
      }
    }
  }
  
  private static void clearScreen() {
    // UNIX based systems
    System.out.print("\033[H\033[2J");
    
    // WINDOWS based systems
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    catch(Exception e) {
      // couldn't clear, oh well
    }
    
    // clear map too
    for(int r = 0; r < map.length; r++) {
      for(int c = 0; c < map[r].length; c++) {
        map[r][c] = ' ';
      }
    }
  }
  
  private static void maxHeapMap() {
    // load heap
    int[] nums = {32,14,5,41,50,23};
    MaxHeap heap = new MaxHeap();
    mhw &= (heap.isEmpty() == true);
    for(int num : nums) {
      heap.insert(num);
    }
    mhw &= (heap.isEmpty() == false);
    
    // upper wall
    for(int i = 0; i < 10; i++) {
      map[0][i] = WALL;
    }
    
    // inner walls
    for(int h = 0; h < nums.length; h++) {
      int num = heap.findMax();
      int num2 = heap.extractMax();
      mhw &= (num == num2);
      
      int fnum = num / 10;
      int snum = num - (fnum * 10);
      int col = 0;
      map[h+1][col++] = WALL;
      map[h+1][col++] = WALL;
      for(int i = 0; i < fnum; i++) {
        map[h+1][col++] = WALL;
      }
      map[h+1][col++] = ' ';
      map[h+1][col++] = ' ';
      for(int i = 0; i < snum; i++) {
        map[h+1][col++] = WALL;
      }
      map[h+1][col++] = WALL;
    }
    mhw &= (heap.findMax() == -1 && heap.extractMax() == -1);
    mhw &= (heap.isEmpty() == true);
    
    // lower wall
    for(int i = 0; i < 10; i++) {
      map[nums.length+1][i] = WALL;
    }
  }
  
  private static void minHeapMap() {
    // load heap
    int[] nums = {32,14,5,41,50,23};
    MinHeap heap = new MinHeap();
    ihw &= (heap.isEmpty() == true);
    for(int num : nums) {
      heap.insert(num);
    }
    ihw &= (heap.isEmpty() == false);
    
    // upper wall
    for(int i = 0; i < 9; i++) {
      map[0][i+10] = WALL;
    }
    
    // inner walls
    for(int h = 0; h < nums.length; h++) {
      int num = heap.findMin();
      int num2 = heap.extractMin();
      ihw &= (num == num2);

      int fnum = num / 10;
      int snum = num - (fnum * 10);
      int col = 0;
      for(int i = 0; i < fnum; i++) {
        map[h+1][col++ + 10] = WALL;
      }
      map[h+1][col++ + 10] = ' ';
      map[h+1][col++ + 10] = ' ';
      for(int i = 0; i < snum; i++) {
        map[h+1][col++ + 10] = WALL;
      }
      map[h+1][col++ + 10] = WALL;
      map[h+1][col++ + 10] = WALL;
    }
    ihw &= (heap.findMin() == -1 && heap.extractMin() == -1);
    ihw &= (heap.isEmpty() == true);
    
    // lower wall
    for(int i = 0; i < 9; i++) {
      map[nums.length+1][i + 10] = WALL;
    }
  }
  
  private static void draw() throws InterruptedException {
    // load map
    maxHeapMap();
    if (smh) {
      minHeapMap();
    }
    
    // place info
    if (!smh) {
      map[infoRow][infoCol] = INFO;
    }
    else {
      map[infoRow][infoCol] = ' ';
      map[info2Row][info2Col] = INFO;
    }
    
    // clear out hero init position
    map[6][1] = ' ';
    String text = Stuff.get();
    
    map[4][7] = text.charAt(0);
    map[4][8] = text.charAt(1);
    map[4][9] = text.charAt(2);
    if (smh) {
      map[4][10] = text.charAt(3);
    }
    
    map[5][6] = text.charAt(4);
    map[5][7] = text.charAt(5);
    map[5][8] = text.charAt(6);
    map[5][9] = text.charAt(7);
    if (smh) {
      map[5][10] = text.charAt(8);
      map[5][11] = text.charAt(9);
      map[5][12] = text.charAt(10);
    }
    
    map[6][5] = text.charAt(11);
    map[6][6] = text.charAt(12);
    map[6][7] = text.charAt(13);
    map[6][8] = text.charAt(14);
    map[6][9] = text.charAt(15);
    if (smh) {
      map[6][10] = text.charAt(16);
      map[6][11] = text.charAt(17);
      if (heroRow == info2Row && heroCol == info2Col) {
        map[6][12] = text.charAt(18);
        map[6][13] = text.charAt(19);
      }
    }
    
    // place hero
    map[heroRow][heroCol] = HERO;
    
    // show map
    for(int r = 0; r < map.length; r++) {
      for(int c = 0; c < map[r].length; c++) {
        System.out.print(map[r][c]);
        Thread.sleep(SLEEP);
      }
      System.out.println();
      Thread.sleep(SLEEP);
    }
  }
  
  private static void menu() {
    System.out.println("Action?");
    System.out.println("w: go up");
    System.out.println("a: go left");
    System.out.println("s: go down");
    System.out.println("d: go right");
    System.out.println("q: quit");
    System.out.print  ("? ");
    
    // grab input
    String line = scanner.nextLine();
    
    // make sure something was entered
    if (line.trim().equals(""))
      return;
    
    // grab first char
    char choice = line.charAt(0);
    
    // process input
    char mapChar;
    switch(choice) {
      // go up
      case 'w':
        if (heroRow-1 >= 0) {
          mapChar = map[heroRow-1][heroCol];
          if (mapChar == ' ' || mapChar == INFO) {
            heroRow--;
          }
        }
        break;
        
      // go left
      case 'a':
        if (heroCol-1 >= 0) {
          mapChar = map[heroRow][heroCol-1];
          if (mapChar == ' ' || mapChar == INFO) {
            heroCol--;
          }
        }
        break;
        
      // go down
      case 's':
        if (heroRow+1 < map.length) {
          mapChar = map[heroRow+1][heroCol];
          if (mapChar == ' ' || mapChar == INFO) {
            heroRow++;
          }
        }
        break;
        
      // go right
      case 'd':
        if (heroCol+1 < map[0].length) {
          mapChar = map[heroRow][heroCol+1];
          if (mapChar == ' ' || mapChar == INFO) {
            heroCol++;
          }
        }
        break;
        
      // quit
      case 'q':
        quit = true;
        break;
    }
  }
}
