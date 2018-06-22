
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
public class CmetodoFalsaPocision extends Cpolinomio{
    public double metodoFalsaPocision(double x0, double xf,Cpolinomio pol,int cifra)//para que este metodo funcione pedimos al usuario que ingrese un punto inicial y uno final
            //ademas de un cpolinomio con el fin de evaluarlo y asi poder optener la aproximacion a la raiz, y las sifras significaticas
    {
        Temporizador t=new Temporizador();
        t.iniciar();
        double xm=xf,fx0,fxm,fxf,error=1,valorRe=0;
        fx0=pol.evaluarPoli(x0);
        fxf=pol.evaluarPoli(xf);
        double tolerancia= ((0.5)* pow(10,2-cifra));
        if(x0>xf)//donde comienza el intervalo es mayor a donde termina cambiamos el sentido
        {
            xf=x0;
            x0=xm;
        }//esto mismo se hace en el metodo de biseccion
        if(fxf*fx0>0)//si al multiplicar los valores evaluado de los extremos del intervalo es mayor a cero significa que la raiz no esta ahi, pero podemos evaluar 
        {//de varios puntos hasta encontrar donde cambia de signo 
            xf= (x0+0.5);
            fxf=pol.evaluarPoli(xf);
            while(fxf*fx0>0 && xf<xm)//de esta forma el valor de xf nuevo no puede ser mayor al que tenia antes, el anterior lo guardamos en xm
            {
                xf+=0.05;
                fxf=pol.evaluarPoli(xf);
                if(fxf==0)
                {
                    xm=xf;
                    break;
                }
            }
        }
        else if(fxf*fx0==0)//si la multiplicacion de ambos 
        {
            if(fxf==0)
            {
                xm=xf;
            }
            else if(fx0==0)
            {
                xm=x0;
            }
        }
        if(fxf*fx0<0)
        {
            xm=(xf*fx0-x0*fxf)/(fx0-fxf);
            while(Math.abs(error)>tolerancia)
            {
                this.iteracion++;
                fxm=pol.evaluarPoli(xm);
                if(fxm==0)
                {
                    break;
                }
                else if(fxm*fx0<0)
                {
                    xf=xm;
                    fxf=fxm;
                }
                else if(fxm*fxf<0)
                {
                    x0=xm;
                    fx0=fxm;
                }
                if(iteracion==1)
                {
                    valorRe=xm+1;
                }
                error=((valorRe-xm)/valorRe)*100;
                System.out.println(xm);
                valorRe=xm;
                    xm=(xf*fx0-x0*fxf)/(fx0-fxf);
            }
        }
        
        System.out.println(t.detenerse());
        return valorRe;
        
    }
}
//8x^3+12x^2+20x+8