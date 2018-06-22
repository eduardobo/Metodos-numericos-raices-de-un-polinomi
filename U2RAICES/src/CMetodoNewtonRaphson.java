
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
public class CMetodoNewtonRaphson extends Cpolinomio{
    public double MetodoNewtonr(double x0,Cpolinomio poli,int cifras)//x0 representa el punto donde comienza a buscar el metodo
    {//Cpolinomio indica que espera la entrada de un objeto de tipo polinomio, al cual llamo poli, cifras indica el numero de cifras significativas que deseamos
       Temporizador t=new Temporizador();
        t.iniciar();
        double tolerancia,error=1,x1;
        
        Cpolinomio dpolinomio=new Cpolinomio(); //se declara dpolinomio para aqui guardar la derivada del polinomio
                dpolinomio.setPolinomio(poli.derivapoli(poli.getPolinomio()));//poli es un objeto de tipo "polinomio" que cuenta con
        tolerancia=((0.5)* pow(10,2-cifras));//metodos de derivar evaluar y valir a un polinomio
        do//en la linea anterior se calculo la tolerancia
        {
            iteracion++;// se aument la iteracion en 1
            x1=x0-(poli.evaluarPoli(x0)/dpolinomio.evaluarPoli(x0));//para ejecutar este metodo pedimos un xo inicial la cantidad de cifras que deseamos
            if(poli.evaluarPoli(x1)==0)//encontrar y un objeto de Cpolinomio, aunque acepta objetos que heredan de cpolinomio
            {
                break;
            }
            if(x0==0)//puede darse el caso en que comenzemos con que x0 sea cero y eso nos lanzaria un error por lo tanto hacemos esto para excluir esto
            {
                error=1;
            }
            else 
                error=((x0-x1)/x0)*100;
            x0=x1;
        }while(Math.abs(error)>tolerancia);
        
        System.out.println(t.detenerse());
        return x1;
    }
}
//x^7+28x^6+84x^5+140x^4+132x^3+72x^2-48x-32
