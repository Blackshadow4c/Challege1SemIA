/*Modulos y Paquetes de Jade para crear agentes y comportamientos OneShotBehaviour*/
package examples.C2;

import java.util.Random;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.Hashtable; 
import java.text.DecimalFormat;

public class OneShotAgent extends Agent {
  /*Clase principal OneShotAgent es la encargada de ejecutar los comportamientos*/

  private OneShotAgentGui myGui;

  protected void setup() {
    /*Metodo set up que precarga todos los comportamientos de la clase*/
    myGui = new OneShotAgentGui(this);
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
        /*Metodo setter advertisingX*/
        this.advertisingX = advertisingX;
    }

    public double[] getSalesY() {
        /*Metodo getter salesY*/
        return salesY;
    }

    public void setSalesY(double[] salesY) {
        /*Metodo setter salesY*/
        this.salesY = salesY;
    }
  }

  private class GradienteDescendiente {

    private DataSet Benetton;
    private double learning_rate;
    private double beta_cero = 0;
    private double beta_uno = 0;
    private double n;
    private double x;
    
    /*Constructor*/
    public GradienteDescendiente(DataSet data, double alpha, double x) {
        this.Benetton = data;
        this.learning_rate = alpha;
        this.n = (double)Benetton.getAdvertisingX().length;
        this.x = x;
    }
    /*Perdida*/
    public double loss_function() {
        double sumatoria = 0;
        var equis = Benetton.getAdvertisingX();
        var ygriega = Benetton.getSalesY();
        for (int i = 0; i < n; i++) {
            sumatoria = sumatoria + (ygriega[i] - (beta_cero + beta_uno * equis[i]));
        }
        return (sumatoria * sumatoria) * 1.0/n;
    }
    /*Drivada b1*/
    public double derivada_b1() {
        double sumatoria = 0;
        var equis = Benetton.getAdvertisingX();
        var ygriega = Benetton.getSalesY();
        for (int i = 0; i < n; i++) {
            sumatoria = sumatoria + (equis[i] * (ygriega[i] - (beta_cero + beta_uno * equis[i])));
        }
        return (-2.0/n) * sumatoria;
    }
    /*Drivada b0*/
    public double derivada_b0() {
        double sumatoria = 0;
        var equis = Benetton.getAdvertisingX();
        var ygriega = Benetton.getSalesY();
        for (int i = 0; i < n; i++) {
            sumatoria = sumatoria + (ygriega[i] - (beta_cero + beta_uno * equis[i]));
        }
        return (-2.0/n) * sumatoria;
    }
    /*GDC*/
    public void gradiente_descendente() {
        double gradiente_b1, gradiente_b0;
        DecimalFormat df = new DecimalFormat("#.00");
        while(loss_function() >= 0.01){
            gradiente_b1 = derivada_b1();
            gradiente_b0 = derivada_b0();
            beta_uno = beta_uno - (learning_rate * gradiente_b1);
            beta_cero = beta_cero - (learning_rate * gradiente_b0);
            System.out.println("Loss function =" +  df.format(loss_function()) + "\n");
            System.out.println("beta_uno =" +  df.format(beta_uno) + "\n");
            System.out.println("beta_cero =" + df.format(beta_cero) + "\n");
        }
        System.out.println("Regresion lineal con gradiente");
        System.out.println("y = " + df.format(beta_cero) + " + " + df.format(beta_uno) + " * " + x + " = " + df.format((beta_cero + beta_uno * x)));
    }
  }
  /*Prediccion por regresion lineal*/
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
      System.out.println("Regresion lineal sola");
      System.out.println("y = " + df.format(b0) + " + " + df.format(b1) + " * " + x + " = " + df.format((b0 + b1 * x)));
    }

  }
/*Main*/
  public void inicializar(){
    DataSet a = new DataSet();
    GradienteDescendiente b = new GradienteDescendiente(a, 0.01, myGui.equis);
    RegresionLineal c = new RegresionLineal(a, myGui.equis);
    b.gradiente_descendente();
    c.impresion_de_Regresion();
  }
  /*Herencia*/
  private class MyOneShotBehaviour extends OneShotBehaviour {
    public void action() {
      /*Confirmacion de que se ejecuto bien*/
      System.out.println("Agent's action method executed");    
      myGui.showGui();
    } 

    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    
}