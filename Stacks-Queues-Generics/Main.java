/**
 * Name: Norman Cook
 * Date: 01/15/2019
 * Description:  Implements a stack and queue using list implementation from a
 * 	previous assignment. This uses the list operations to accomplist the operations
 * 	of a stack and queue.
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/** contains our entry point
  */
public class Main {
  private static boolean REVERSE_MAP = true;
  
  private static Stack<Character> stack;
  private static Queue<Character> queue;
  private static String[] map;
  private static int playerRow;
  private static int playerCol;
  private static final char PLAYER_SYMBOL = ';';
  private static final int PLAYER_START_ROW = 8;
  private static final int PLAYER_START_COL = 9;
  private static Scanner scanner;
  private static boolean running;
  
  /** entry point */
  public static void main(String[] args) {
    stack = new Stack<Character>();
    queue = new Queue<Character>();
    playerRow = PLAYER_START_ROW;
    playerCol = PLAYER_START_COL;
    scanner = new Scanner(System.in);
    running = true;
    loadMap();
    if (REVERSE_MAP) {
      reverseMap();
    }
    run();
  }
  
  private static void loadMap() {
    map = new String[12];
    map[ 0] = "STATICxSTATICxSTATI";
    map[ 1] = "ST ATI CxS * STATIC";
    map[ 2] = "ST AT   ICx STATICx";
    map[ 3] = "S  T     AT ICx  ST";
    map[ 4] = "S    T A     TI  CS";
    map[ 5] = "STATIC xSTA  TIC xS";
    map[ 6] = "S            TA  TI";
    map[ 7] = "S TATICxSTATIC  xST";
    map[ 8] = "S                 T";
    map[ 9] = "S TATICxSTATICxST A";
    map[10] = "S                 T";
    map[11] = "STATICxSTATICxSTATI";
  }
  
  private static void reverseMap() {
     for(int i = 0; i < map.length; i++) {
       String line = map[i];
       Queue<Character> queue = new Queue<Character>();
       for(char c : line.toCharArray()) {
         queue.enqueue(c);
       }
       queue.reverse();
       String revLine = "";
       while (!queue.isEmpty()) {
         revLine += queue.dequeue();
       }
       map[i] = revLine;
     }
  }
  
  private static void run() {
    while(running) {
      clearScreen();
      draw();
      menu();
    }
  }
  
  private static void clearScreen() {
    // use whichever method works for you
    // comment out the other method
    
    // UNIX based systems
    //System.out.print("\033[H\033[2J");
    
    // WINDOWS based systems
    /*try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    catch(Exception e) {
      // couldn't clear, oh well    }*/
  }
  
  private static void draw() {
    if (!REVERSE_MAP && playerRow == 1 && playerCol == 11) {
      map[2] = "ST AT   ICxxSTATICx";
      map[9] = "S >>>>>>>>>>>>>>>  ";
    }
    else if (REVERSE_MAP && playerRow == 1 && playerCol == 7) {
      loadMap();
      map[2] = "ST AT   ICxxSTATICx";
      map[9] = "S <<<<<<<<<<<<<<<  ";
      reverseMap();
    }
    
    for(int i = 0; i < map.length; i++) {
      if (playerRow == i) {
        for(int j = 0; j < playerCol; j++) {
          System.out.print(map[i].charAt(j));
        }
        System.out.print(PLAYER_SYMBOL);
        for(int j = playerCol + 1; j < map[i].length(); j++) {
          System.out.print(map[i].charAt(j));
        }
        System.out.println();
      }
      else {
        System.out.println(map[i]);
      }
    }
    System.out.println();
    
    if (!REVERSE_MAP && playerRow == 9 && playerCol == 18) {
      int[] nums = {206, 232, 232, 212, 220, 106, 112, 242, 94, 218, 222, 198, 92, 
                    216, 228, 234, 242, 220, 210, 232, 94, 94, 116, 230, 224, 232, 
                    232, 208, 64, 222, 232, 64, 222, 142};
      char[] chars = new char[nums.length];
      for(int i = nums.length - 1; i >= 0; i--) {
        chars[nums.length - i - 1] = (char)(nums[i] / 2);      }
      System.out.println(new String(chars));      System.out.println();
    }
    else if (REVERSE_MAP && playerRow == 9 && playerCol ==  0) {
      rr();
    }
  }
  
  private static void rr() {
    String something = "110100001110100011101000111000001110011001110100010111100101111011101000110100101101110011110010111010101110010011011000010111001100011011011110110110100101111001100100110011001100011011100000111001001100101001101100";
    String somethingElse = "";
    String x = "";
    int n;
    for(int i = 0; i < something.length(); i++) {
      char c = something.charAt(i);
      if (i != 0 && i % 8 == 0) {
        n = Integer.parseInt(x, 2);
        n /= 2;
        somethingElse += Character.toString((char)n);
        x = "";
      }
      x += "" + c;
    }
    n = Integer.parseInt(x, 2);
    n /= 2;
    somethingElse += Character.toString((char)n);
    System.out.println(somethingElse);
    System.out.println();
  }
  
  private static void menu() {
    System.out.println("Action?");
    System.out.println("w: go up");
    System.out.println("a: go left");
    System.out.println("s: go down");
    System.out.println("d: go right");
    if (!stack.isEmpty()) {
      System.out.println("u: undo last move");
    }
    if (!queue.isEmpty()) {
      System.out.println("r: replay all");
    }
    System.out.println("q: quit");
    System.out.print  ("? ");
    String inputLine = scanner.nextLine().trim();
    if (inputLine.length() <= 0)
      return;
    char choice = inputLine.charAt(0);
    char mapChar;
    switch(choice) {
      // go up
      case 'w':
        if (playerRow-1 >= 0) {
          mapChar = map[playerRow-1].charAt(playerCol);
          if (mapChar == ' ' || mapChar == '*') {
            playerRow--;
            stack.push('w');
            queue.enqueue('w');
          }
        }
        break;
        
      // go left
      case 'a':
        if (playerCol-1 >= 0) {
          mapChar = map[playerRow].charAt(playerCol-1);
          if (mapChar == ' ' || mapChar == '*') {
            playerCol--;
            stack.push('a');
            queue.enqueue('a');
          }
        }
        break;
        
      // go down
      case 's':
        if (playerRow+1 < map.length) {
          mapChar = map[playerRow+1].charAt(playerCol);
          if (mapChar == ' ' || mapChar == '*') {
            playerRow++;
            stack.push('s');
            queue.enqueue('s');
          }
        }
        break;
        
      // go right
      case 'd':
        if (playerCol+1 < map[0].length()) {
          mapChar = map[playerRow].charAt(playerCol+1);
          if (mapChar == ' ' || mapChar == '*') {
            playerCol++;
            stack.push('d');
            queue.enqueue('d');
          }
        }
        break;
        
      // undo using stack
      case 'u':
        if (!stack.isEmpty()) {
          char top = stack.pop();
          switch(top) {
            case 'w': playerRow++; queue.enqueue('s'); break;
            case 'a': playerCol++; queue.enqueue('d'); break;
            case 's': playerRow--; queue.enqueue('w'); break;
            case 'd': playerCol--; queue.enqueue('a'); break;
          }
        }
        break;
        
      // replay using queue
      case 'r':
        if (!queue.isEmpty()) {
          playerRow = PLAYER_START_ROW;
          playerCol = PLAYER_START_COL;
          Queue<Character> newQueue = new Queue<>();
          while(!queue.isEmpty()) {
            clearScreen();
            draw();
            System.out.flush();
            char move = queue.dequeue();
            switch(move) {
              case 'w': playerRow--; break;
              case 'a': playerCol--; break;
              case 's': playerRow++; break;
              case 'd': playerCol++; break;
            }
            newQueue.enqueue(move);
            try {
              TimeUnit.MILLISECONDS.sleep(600);
            }
            catch(InterruptedException e) {
              System.out.println("Shit happens");
            }
          }
          queue = newQueue;
        }
        break;
      
      // quit
      case 'q':
        running = false;
        break;
    }
  }
}
