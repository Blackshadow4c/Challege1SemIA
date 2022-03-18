
package examples.challege2;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.text.DecimalFormat; 



public class Challege2 extends Agent {
  
  private Challege2 myGui;
  
  protected void setup() {
    myGui = new Challege2(this);
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 
  private class DataSet {

    /*Clase DataSet encargada de crear el data set para su futura manipulacion*/
    private double advertisingX[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    private double salesY[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    public double[] getAdvertisingX() {
        /*Metodo getter advertisingX*/
        return advertisingX;
    }

    public void setAdvertisingX(double[] advertisingX) {
        /*Metodo setter advertisingX sin uso por ahora*/
        this.advertisingX = advertisingX;
    }

    public double[] getSalesY() {
        /*Metodo getter salesY sin uso por ahora*/
        return salesY;
    }

    public void setSalesY(double[] salesY) {
        /*Metodo setter salesY*/
        this.salesY = salesY;
    }
  }

  private class RegresionLineal {
    private DataSet Benetton;
    private double n;
    private double x;
    public RegresionLineal(DataSet data, double x){
      this.Benetton = data;
      this.n = (double)Benetton.getAdvertisingX().length;
      this.x = x;
    }
    
    public double b1(){
      double sum[] = new double[4];
        for (int i = 0; i < n ; i++) {
            sum[0] = sum[0] + Benetton.getAdvertisingX()[i] * Benetton.getSalesY()[i];
        }
        for (int i = 0; i < n; i++) {
            sum[1] = sum[1] + Benetton.getAdvertisingX()[i];
        }
        for (int i = 0; i < n; i++) {
            sum[2] = sum[2] + Benetton.getSalesY()[i];
        }
        for (int i = 0; i < n; i++) {
            sum[3] = sum[3] + Benetton.getAdvertisingX()[i] * Benetton.getAdvertisingX()[i];
        }
        return (n * sum[0] - sum[1] * sum[2]) / (n * sum[3] - sum[1] * sum[1]);/*Formula*/
    }

    public double b0() {
      /*Metodo B0 para el calculo de la Regresion Lineal*/
      double x = 0, y = 0;
      for (int i = 0; i < n; i++) {
          x = x + Benetton.getAdvertisingX()[i];
      }
      x = x / n;
      for (int i = 0; i < n; i++) {
          y = y + Benetton.getSalesY()[i];
      }
      y = y / n;
      return y - b1() * x;/*Formula*/
    }
    /*Mortrar resultados*/
    public void impresion_de_Regresion(){
      double b0 = b0(), b1 = b1();
      DecimalFormat df = new DecimalFormat("#.00");
      System.out.println("Regresion lineal");
      System.out.println("y = " + df.format(b0) + " + " + df.format(b1) + " * " + x + " = " + df.format((b0 + b1 * x)));
    }

  }
  public void inicializar(){
    DataSet a = new DataSet();
    RegresionLineal c = new RegresionLineal(a, myGui.equis);
    c.impresion_de_Regresion();
  }
  /*Herencia*/
  private class MyOneShotBehaviour extends OneShotBehaviour {
    public void action() {
      /*Confirmacion de que se ejecuto bien*/
      System.out.println("Agent's action method executed");    
    } 

    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }
  
}

