
package examples.challege3;
import java.util.Scanner;
import jade.core.Agent;



public class Challege3 extends Agent {

  protected void setup() {
  	
    int b0 = 168;
    int b1 = 23;
    int x = 0 ; 
    int y = 0; 
    Scanner in = new Scanner(System.in);
    
    System.out.println("Dame el valor de X: ");
    int a = in.nextInt();

    y = b0 + (b1 * x); 
    System.out.println("Y = "+y);
    System.out.printf("B0=  %d B1=  %d  . " ,b0,b1);

    
  	
  	// Make this agent terminate
  	doDelete();
  } 
}

