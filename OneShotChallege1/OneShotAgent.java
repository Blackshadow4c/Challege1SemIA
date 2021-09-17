package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.lang.Math;

public class OneShotAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  static double objetive(double x){
    return Math.pow(x,2.0);
  } 
  static double derivative(double x){
    return x * 2.0;
  }

  private class MyOneShotBehaviour extends OneShotBehaviour {

    

    public void action() {//gradient_decent
      
      double step= 1.0;
      //double range [-1.0] [1.0]; 
      int Iterations = 10;
      double Solution = (double)Math.floor(Math.random()*(1.0-(-1.0)+1)-1.0); 
      double gradient = 0;
      double SolutionEv = 0;

      for (int i = 0; i < Iterations; i++) {
        gradient = derivative(Solution);
        Solution = Solution - step * gradient;
        SolutionEv = objetive(Solution);
        System.out.printf("%d F %d =  %d  . " ,i,Solution,SolutionEv);
      }
      System.out.println("Terminado.");

    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
