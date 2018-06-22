
import static java.lang.Math.pow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardo
 */
public class CMetodoSecante extends Cpolinomio{
    public double MetodoSecante(double x1,double x0,Cpolinomio poli,int cifras)//x0 representa el punto donde comienza a buscar el metodo
    {//Cpolinomio indica que espera la entrada de un objeto de tipo polinomio, al cual llamo poli, cifras indica el numero de cifras significativas que deseamos
        Temporizador t=new Temporizador();//para saber cuanto tiempo se corre el programa en milisegundo instanciamos este objeto de la clase temporizador
        t.iniciar();//lo iniciamos y esto sucede en las otras clase de metodos numericos
        double tolerancia,error,x2;
        tolerancia= ((0.5)* pow(10,2-cifras));//metodos de derivar evaluar y valir a un polinomio
        do
        {
            iteracion++;
            x2=x0-(poli.evaluarPoli(x0)/((poli.evaluarPoli(x1)-poli.evaluarPoli(x0))/(x1-x0)));
            if(poli.evaluarPoli(x1)==0)
            {
                break;
            }
            error=((x0-x2)/x0)*100;
            x0=x1;
            x1=x2;
        }while(Math.abs(error)>tolerancia);
        
        System.out.println(t.detenerse());//por ultimo imprimimos el tiempo de ejecucion 
        return x1;
    }
}
