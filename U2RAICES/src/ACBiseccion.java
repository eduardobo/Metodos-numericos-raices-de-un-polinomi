
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ACBiseccion extends JFrame{
    public static void main(String[] args) {
        try{//creamos un objeto para poder graficar, se usara despues
        Scanner entrada = new Scanner(System.in);//para ahorra lineas de codigo se escanearan los datos con el objeto "entrada"
            System.out.println("introduce un numero segun la opcion que desees"
            +"\n1.-Metodo Biseccion\n2.-Metodo Falsa pocision"
            +"\n3.-Metodo newton-Raphson\n4.-Metodo Secante");
        switch(entrada.nextInt())//accedemos a un menu con un switch
        {
            case 1:
            {
                System.out.println("Metodo Biseccion");
                CmetodoBiseccion polin=new CmetodoBiseccion();
                Cpolinomio poli=new Cpolinomio();//el espacio en la memoria se crea hasta que entra en el caso 1, y se crea "poli"
                System.out.println("introduce el punto de inicio del intervalo \n x0 = ");//"poli" almacena un string donde se guarda el polinomio
        double x0=entrada.nextDouble();//un contador de iteraciones, la clase cmetodobiseccion hereda de Cpolinomio
        System.out.println("punto final del intervalo \n xf = ");//Cpolinomio tiene metodos para evaluar polinomios, validarlos y sacar derivada
        double xf=entrada.nextDouble();//x0 almacena el inicio del intervalo y xf el final del intervalo
        System.out.println("introduce el polinomio f(x)= ");
        poli.setPolinomio(entrada.next());//el polinomio se lee en un String y se agrega a "poli"
        System.out.println("numero de cifras significativas");
        int cifras=entrada.nextInt();//"cifras" almacena la el valor de cifras significativas que deseamos
        boolean ProCD;
        do
        {
            ProCD=true;
            if(x0==xf)//esta parte funciona para validar en primera instancia los datos, por ejemplo el punto extremo del intervalo no puede ser igual al final del mismo
            {
                System.out.println("intervalo no valido\n introduce un nuevo intervalo\n"
                        + "inicia: ");
                x0=entrada.nextDouble();//si son iguales pide un nuevo intervalo y reinicia la validacion de estos datos
                System.out.println("termina: ");
                xf=entrada.nextDouble();
                ProCD=false;
            }
            if(cifras<2)
            {//para comodidad espero que el usurio ingrese almenos 2 en la cantidad de cifras significtivs
                System.out.println("se espera como minimo 2 cifras significativas\n"
                        + "introduce otra cantidad de cifras: ");
                cifras=entrada.nextInt();
                ProCD=false;
            }
            
        }while(!ProCD);//cuando se cumpla esta pequeña validacion comienza la busqueda de la raiz por el metodo de biseccion, esta secuencia se repite en este menu para todos los casos
            System.out.println("comenzando busqueda ");//la diferencia es que cada caso crea un objeto correspondiente a su metodo
            System.out.println("la raiz es : "+polin.metodoBiseccion(x0, xf, poli, cifras)+//por ultimo cada caso ingresa los valores por medio del objeto creado para el mismo y el metodo
                    "\nel metodo hizo "+poli.iteracion+" iteraciones");//que lleva por nombre el metodo numerico que realiza en este caso "poli.metodobiseccion(datos)" accede
            new MostrarGrafica("Metodo Biseccion", poli);//de esta forma mostramos la grafica ingresando el nombre y polinomio, el polinomio se agrega para calcular los valores de una matriz con valores x y se calcula una nueva con valores los valores de la imagen de cada x
            break;//al metodo numerico que ejecuta, esto se repite en todos los casos
            }// se ecplicaran las siguientes clases CPolinomio debido a que es la base para ejecutar los metodos en las otras clases
            case 2:
            {
                System.out.println("Metodo de  Falsa Posicion ");//aqui el objeto que se crea es de tipo CmetodoFalsaPocision y lo denomine "falsa"
                CmetodoFalsaPocision falsa= new CmetodoFalsaPocision();
                System.out.println("introduce el punto de inicio del intervalo \n x0 = ");
        double x0=entrada.nextDouble();
        System.out.println("punto final del intervalo \n xf = ");
        double xf=entrada.nextDouble();
        System.out.println("introduce el polinomio f(x)= ");
        falsa.setPolinomio(entrada.next());
        System.out.println("numero de cifras significativas");
        int cifras=entrada.nextInt();//cifras significativas
        boolean ProCD;
        do
        {
            ProCD=true;
            if(x0==xf)
            {
                System.out.println("intervalo no valido\n introduce un nuevo intervalo\n"
                        + "inicia: ");
                x0=entrada.nextDouble();
                System.out.println("termina: ");
                xf=entrada.nextDouble();
                ProCD=false;
            }
            if(cifras<2)
            {
                System.out.println("se espera como minimo 2 cifras significativas\n"
                        + "introduce otra cantidad de cifras: ");
                cifras=entrada.nextInt();
                ProCD=false;
            }

            }while(!ProCD);
            System.out.println("comenzando busqueda ");
            System.out.println("la raiz es : "+falsa.metodoFalsaPocision(x0, xf, falsa, cifras)+
                    "\nel metodo hizo "+falsa.iteracion+" iteraciones");
            new MostrarGrafica("Falsa pocision", falsa);
            break;
            }
            case 3:
            {
                System.out.println("Metodo Newton-Raphson");
                CMetodoNewtonRaphson newton=new CMetodoNewtonRaphson();
                System.out.println("punto inicial x0 = ");
                boolean ProCD=true;

        float x0=entrada.nextFloat();
            
            System.out.println("introduce el polinomio f(x)= ");
            newton.setPolinomio(entrada.next());
            System.out.println("numero de cifras significativas");
            int cifras=entrada.nextInt();
            do{
            if(cifras<2)
            {
                System.out.println("se espera como minimo 2 cifras significativas\n"
                        + "introduce otra cantidad de cifras: ");
                cifras=entrada.nextInt();
                ProCD=false;
            }
            
            }while(!ProCD);
            System.out.println("comenzando busqueda ");
            System.out.println("la raiz es : "+newton.MetodoNewtonr(x0, newton, cifras)+
                    "\nel metodo hizo "+newton.iteracion+" iteraciones");
                  new MostrarGrafica("Metodo Newton-Raphson", newton);
            break;
            }
            case 4:
            {
                 System.out.println("Metodo Secante");
                CMetodoSecante secante= new CMetodoSecante();
                CMetodoNewtonRaphson tangente=new CMetodoNewtonRaphson();
                System.out.println("introduce el punto de inicio del intervalo \n x0 = ");
                double x0=entrada.nextDouble();
                System.out.println("punto final del intervalo \n xf = ");
                double xf=entrada.nextDouble();
                System.out.println("introduce el polinomio f(x)= ");
                secante.setPolinomio(entrada.next());
                tangente.setPolinomio(secante.getPolinomio());
                System.out.println("numero de cifras significativas");
                int cifras=entrada.nextInt();//cifras significativas
                boolean ProCD;
        do
        {
            ProCD=true;
            if(x0==xf)
            {
                System.out.println("intervalo no valido\n introduce un nuevo intervalo\n"
                        + "inicia: ");
                x0=entrada.nextDouble();
                System.out.println("termina: ");
                xf=entrada.nextDouble();
                ProCD=false;
            }
            if(cifras<2)
            {
                System.out.println("se espera como minimo 2 cifras significativas\n"
                        + "introduce otra cantidad de cifras: ");
                cifras=entrada.nextInt();
                ProCD=false;
            }
            }while(!ProCD);
            System.out.println("comenzando busqueda por metodo secante");
            System.out.println("la raiz es : "+secante.MetodoSecante(xf, x0, secante, cifras)+
                    "\nel metodo hizo "+secante.iteracion+" iteraciones");
            
            System.out.println("aproximacion por Newton-Raphson  raiz es : "+tangente.MetodoNewtonr(x0, secante, cifras)+
                    "\nel metodo hizo "+tangente.iteracion+" iteraciones");
            new MostrarGrafica("Metodo Secante", secante);
            break;
            }
            
            default:break;
        }
        }
        catch(InputMismatchException e)
        {
            System.out.println("se intento introducir un valor no numerico,\ndonde se pedia un numero");//como estamos pidiento valores numerico puede que el usurio ingrese un caracter, entonces esta linea se encarga de eso, si el usurio ingresa otra cosa provoca una excepcion
        }
    }
    
}
//x^4+2x^3+5x^2+4x-4
//1/3x^6-17/140x^5-379/112x^4+15/14x^3+3/2x^2-3/16x+1/8