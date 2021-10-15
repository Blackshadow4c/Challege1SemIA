
package examples.challege2;

import jade.core.Agent;



public class Challege2 extends Agent {

  protected void setup() {
  	
    int b0 = 168;
    int b1 = 23;
    int x [] = {23, 26, 30, 34, 43, 48, 52, 57, 58, 60, 70, 80}; //12 
    int y = 0; 
    int zCont = 0; 

    for ( zCont = 0; zCont < 12; zCont++){
      y = b0 + (b1 * x[zCont]); 
      System.out.println("Y = "+y);
      System.out.printf(" Year %d B0=  %d B1=  %d  . " ,zCont,b0,b1);
    }
    
  	// Make this agent terminate
  	doDelete();
  } 
}

