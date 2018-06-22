//MostrarGrafica es una clase auxiliar para asi crear los arreglos que pares ordenadas que llenaran la grafica
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MostrarGrafica extends JFrame{
    public MostrarGrafica(String nom,Cpolinomio polinomio)//el primer metodo ingresa un texto a ingresar en la grafica y un polinomio, que tiene como metodos evaluar polinomio, restar polinomio, validarlo y derivarlo
    {
        CGrafica graf=new CGrafica(nom);//creamos un objetos de tipo grafica
        double []x=rango(-3,3,0.01);//(el -3 indica donde comienza la grafica y 3 indica donde termina) para calcular el un pequeño rango que se graficara, la tercer componen indica el aumento en x para calcular la siguiente imagen
        double []y=f(x,polinomio);//f() es un metodo que evalua todos los valores del vector x[] gracias a el metodo evaluar de polinomio, y guarda esos valores en el vector y
        graf.agregarGrafica(nom, x, y);//por ultimo agremos el nombre a la grafica y tambien agregamos los vectores 'x' y 'y'
        auxiliar(graf);//este metodo hace visible a la grafica, creando un panel para ahi poner la grafica
    }
    public void auxiliar(CGrafica graf)//este es el ultimo metodo que se ve en la parte superior
    {
        JPanel panel=graf.obtienePanel();//accede a un metodo de la clase grafica, para guardar la grafica en un panel
        this.setSize(600, 600);//asignamos tamaño a ese panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//esto indica la manera en que se cierra el panel, que sea precionando x
        this.add(panel);//agregamos panel a panel
        this.setVisible(true);//lo mostramos
    }
    
    public MostrarGrafica(String nom, Cpolinomio polino,double xi, double xn)//este metodo funiona cuando nostros queremos asiganar el rango que se va a graficar
    {
        CGrafica graf=new CGrafica(nom);//funciona de la misma forma que el metodo anterior
        double []x=rango(xi,xn,0.01);
        double []y=f(x,polino);
        graf.agregarGrafica(nom, x, y);
        auxiliar(graf);
    }
    
    public double[] rango(double x0, double xf,double d)//se encarga de llenar el vector x tomando en cuenta el rango de valores que queremos
    {
        int n=(int)((xf-x0)/d);//esta parte crea el tamaño que tendra el vector x, debido a que d es el aumento en cada x que evaluaremos
        double []r=new double[n];//estos valores los guardaremos en un vector r, para despues regresarlos al vector x
        for(int i=0;i<n;i++)
        {
            r[i]=x0+d*i;//iremos llenando cada espacio del vector, con su devido aumento
        }
        return r;//regresamos el vector r que se guardara en el vector x
    }
    public double[] f(double[] x,Cpolinomio pol)//este metodo se encarga de llenar el vecor 'y' con sus valores correspondientes a cada x evaluados en el polinomio
    {
        int n=x.length;//en n guardamos el tamaño del vector 'x' para asi crear un vector del mismo tamaño que 'x' y asi guardar toos los valores correspondientes
        double[] y=new double[n];//creamos un vector de tamaño n, que es el que nos interesa regresar a 'y', en este caso se les puso el mismo nombre pero cada uno es diferente en la memoria
        for(int i=0;i<n;i++)
        {
            y[i]=pol.evaluarPoli((float)x[i]);//evaluamos los valores que enemos en el vector 'x', con el metodo evaluar polinomio, y guardamos el resultado en 'y', la variable 'i' se encarga de que sea la misma posicion para mbos vetores
        }
        return y;//regresamos el vector obtenido y se guardara en el vector 'y'
    }
}
