
import static java.lang.Math.pow;
public class CmetodoBiseccion {
    public double metodoBiseccion(double x0, double xf,Cpolinomio pol,int cifra)
    {
        int iteracion=0;
        Temporizador t=new Temporizador();
        t.iniciar();
        double xm=xf,fx0,fxm,fxf,error=1,valorRe=0;
        fx0=pol.evaluarPoli(x0);
        fxf=pol.evaluarPoli(xf);
        double tolerancia= (0.5)* pow(10,2-cifra);
        System.out.println(tolerancia);
        if(x0>xf)
        {
            xf=x0;
            x0=xm;
        }
        if(fxf*fx0>0)
        {
            xf=(x0+0.5);
            fxf=pol.evaluarPoli(xf);
            while(fxf*fx0>0 && xf<xm)
            {
                xf+=0.05;
                fxf=pol.evaluarPoli(xf);
            }
        }
        else if(fxf*fx0==0)
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
            xm=(x0+xf)/2;
            while(Math.abs(error)>tolerancia)
            {
                iteracion++;
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
                valorRe=xm;
                    xm=(x0+xf)/2;
            }
        }
        
        System.out.println(t.detenerse());
        return valorRe;
    }
}
