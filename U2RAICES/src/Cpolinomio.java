import com.sun.javafx.fxml.BeanAdapter;
import java.math.BigDecimal;
public class Cpolinomio {
    String polinomio;//se declara un String polinomio que almacenara el polinomio con el que se trabajara
    int iteracion=0;//tambien se declara iteracion y este se aumenta dentro de los metodos numericos
    public void setPolinomio(String poli)//se creean los metodos set y get para el polinomio y asi acceder a el
    {
        polinomio=poli;
    }
    
    public String getPolinomio()
    {
        return polinomio;
    }
    public int getIteracion()//solo se crea el metodo get para iteracion debido a que solo nos interesa llamarlo
    {
        return iteracion;
    }
    
    
    public double evaluarPoli(double x1)// en un principio tenemos el metodo para evaluar al polinomio, y para poder funcionar necesita que intruzcamos un valor x a evaluar
    {
        char arrayPoli[]=polinomio.toCharArray();//este metodo toma el string polinomio,lo separa por el metodo toCharArray y lo guarda en un array de tipo char
        int c=0;//c es un contador que utilizamos para movernos en las posiciones del array
        double evaluador=0,signo,valorx1=0;//se utiliza evaluador para ir juntando el valor numerico de cada expresion separad por un + o un - en la funcionde 
        try//el metodo puede llegar a dar excesiones por un desbordamiento en el arrayPoli
        {
        while(c<polinomio.length())//se sicla tomando en cuenta el tamaño ya sea de nuestro array o del tamaño del polinomio
        {
            signo=1;//signo sirve para multiplicarlo por el evaluador asi lo haremos positivo o negativo segun corresponda
            String coheficiente="",cohe="";//vaciamos el contenido del coheficiente y cohe que guardaran ya sea coheficiente un numerado y cohe un denominador
            if(arrayPoli[c]=='-')//al principio el polinomio puede tener un -,+,x o un numero
            {//si tiene un - se mete en esta sentencia y signo pasa a ser -1 con el fin de multiplicarlo despues para asi respetar el signo
                signo=-1;
                c++;//este c++ nos indica que avanzamos a la siguiente posicion del polinomio y asi para todos los otros
                while(arrayPoli[c]=='-' || arrayPoli[c]=='+')//este algoritmo acepta una sentencia como la siguiente --+5x^3, es decir los signos al pincipio multiplican a la expresion
                {
                    if(arrayPoli[c]=='-')//avanzamoz cuando vemos un mas o un menos, pero el singo solo cambia cuando encontramos un - por lo tanto multiplicamos a signo
                    {//por -1 para asi seguir respetando nuestro signo
                        signo=signo*-1;
                    }
                    c++;
                }
            }
            if(arrayPoli[c]=='+')//al igual que el if anterior nos sirve para avanzar mientras tengamos un signo y por lo mismo solo cambia su valor cuando encuentra un menos
            {
             signo=1;
                c++;
                while(arrayPoli[c]=='-' || arrayPoli[c]=='+')
                {
                    if(arrayPoli[c]=='-')
                    {
                        signo=signo*-1;
                    }
                    c++;
                }       
            }
            if(Character.isDigit(arrayPoli[c]))//como mencione puede que la expresion comienze con un digito o tambien despues del signo venga un digito
            {//para esto preguntamo con el metodo isDigit si el caracter que tenemos en la pocision actual es un digito, si no es un digito avanzamos al siguiente
                coheficiente+=arrayPoli[c];//if que es indica que el valor actual es una x o X
                c++;// si el valor actual es un digito lo concatenamos al estring coheficiente y avanzamos a la siguiente posicion con el c++
                if(c<polinomio.length())//lo siguiente que puede ocurrir es que se hayamos llegado al final de nuestro arrayPoli y desbordemos al preguntar
                {//si el siguiete valor es algo por lo tanto preguntamos si c es menor a la longitud del polinomio, si esto es verdadero continuamos, en caso contrareo
                    //agregamos coheficiente al evaluador y despues lo agregamos a valorx1 que es el valor que se regresara, en caso de que c sea menos al tamaño del
                    if(Character.isDigit(arrayPoli[c]))//del polinomio continuamos la ejecusion preguntando si el siguiete dato es un digito o es una barra diagonal
                    {//que indicaria un division
                        while(Character.isDigit(arrayPoli[c]))//si es un numeto nos metemos a este while que concantenara los siguientes digitos
                        {
                            coheficiente+=arrayPoli[c];
                            c++;
                            if(c==polinomio.length())
                            {
                                break;
                            }
                        }//cuando se cumpla que no sea un digito o que lleguemos al final del polinomio continuara preguntando si c es menor a la longitud del polinomio
                    }if(c<polinomio.length()){//en caso de ser menor preguntara si el siguiente caracter es una barra diagnonal que indica division
                    if(arrayPoli[c]=='/')// de se un diagonal avanzara a la siguiente pocision y concatenara una los valores numericos en cohe
                    {
                        c++;
                        while(Character.isDigit(arrayPoli[c]))
                        {
                            cohe+=arrayPoli[c];
                            c++;
                            if(c==polinomio.length())
                            {
                                break;
                            }
                        }//cuando el caracter siguiente ya no sea un digito o sea el final del polinomio avanzara
                    }}
                    if(cohe.equals(""))//en estas intruccion se pregunta si hubo algun denominador en caso de que haya es decir que cohe contenga algo avanzara
                        {//en caso de que cohe este vacio solo agregaremos el signo de esta sentencia del polinomio y su coheficiente
                        evaluador=signo*Integer.parseInt(coheficiente);
                        coheficiente="";//vacio la variable coheficiente para su uso posterior
                        }
                        else//en caso de que cohe tenga algo, es decir, existe un denominador haremos la operaicon y lo guardamos en evaluador
                        evaluador=signo*Integer.parseInt(coheficiente)/Integer.parseInt(cohe);
                }
                else//Esta opcion es cuando estamos evaluando el termino independiente o nuestra operacion solo tiene un digito como coheficiente
                {
                    evaluador=signo*Integer.parseInt(coheficiente);
                    coheficiente="";
                    valorx1+=evaluador;
                    break;
                
                }
            }
            if(c<polinomio.length()){//volvemos a preguntar si al introducir arrayPoli[c] desbordamos, en caso de que no desbordemos continuamos
            if(arrayPoli[c]=='x' || arrayPoli[c]=='X')//este algoritmo solo recibe x o X como variables para evaluar
            {
                if(c==0)//si el primer caracter en arrayPoli[c] es una x entonces evaluador vale 1 con el fin de que al multiplicarlo por el valor de x
                {//tengamos un resultado diferente de cero, ya que evaluador comienza en 0
                    evaluador=1;
                }
                c++;//avanzamos al siguiente caracter ignorando a x, debido a que solo nos indica que podemos entrar en esta secuencia
                if(c<polinomio.length())//siempre que aumentamos c es necesario preguntar si ya superamos el tamaño de nuestro array
                {
                if(arrayPoli[c]=='^')//despues de x puede o no seguir el simbolo de exponente
                {//Cuando lo tiene lo avanzamos a la siguiete pocision del arreglo debido a que necesitamos necesariamente minimo un digito
                        c++;//se pregunta si el siguiete es un digito aunque por el metodo validarPolinomio()sabremos si podemos continuar o no
                        if(Character.isDigit(arrayPoli[c]))//concatenamos todos los digitos siguientes a ^ con el fin de encontrar el exponente
                        {
                            coheficiente=""+arrayPoli[c];
                            c++;
                            if(c<polinomio.length())
                            {
                                while(Character.isDigit(arrayPoli[c]))
                                {
                                    coheficiente+=arrayPoli[c];
                                    c++;
                                    if(c==polinomio.length())
                                    {
                                        break;
                                    }
                                }
                            }
                            if(!coheficiente.equals("1")){//la expresion x^1 es una expresion correcta pero se maneja diferente, por eso cuando la encuentre solo multiplicaremos por el valor x
                            for(int i=1;i<=Integer.parseInt(coheficiente);i++)//cuando el valor despues del simbolo exponente es diferente de "1" entonces multiplico el valor
                                {//del x introducido el numero de veces correspondiente como el exponente
                                    evaluador=evaluador*x1;
                                }}
                            else
                                evaluador=evaluador*x1;
                        }
                    }
                    else
                       evaluador=evaluador*x1;//si no tiene un simbolo que indica exponente significia que esta a la ptencia 1 por lo tanto solo se multiplica por el valor de x introducido
                }
                else//este else se utiliza por si tenemos una expresion 15x donde su tamaño es 3 y en la ultima pocision tenemos a x, por lo tanto si queremos buscar la siguiente se desborda el array
                {//para que no se desborde se utiliza el if de arriba
                    coheficiente="";
                    valorx1+=evaluador*x1;
                    break;
                }
            }
            valorx1+=evaluador;//por ultimo todo lo que agregamos a evaluador como valor numerico lo vamos sumando a valorx1 que es quien guarda el valor de nuestro polinomio evaluado en x
        }//cuando estamos en una expresion con solo coheficiente inmediatamente guardamos el valor en valorx1
        else
            valorx1+=evaluador;
        }
        
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
        System.out.print("");
    }
        return valorx1;//por utimo regresamos este valor
    }
    
    public boolean validarPoli()//el polinomio puede tener como primera instancia, un -, un +, una x,un coeficiente(digito) por esta razon estas son nuestras opcciones para comprobar al principio
    {
        char arrayPoli[]=polinomio.toCharArray();
        boolean pro=true;
        int c=0;
        try{
        c=0;
        while(c<polinomio.length() && pro==true)
        {
            if(arrayPoli[c]=='-')//cuando encontramos un menos, puede que despues encontremos otro menos o un mas indicando una multiplicacion de signo, debido a eso la expresion se toma como correcta y se avanza hasta que no haya mas signos
            {
                c++;
                if(arrayPoli[c]=='-' || arrayPoli[c]=='+')
                {
                    c++;
                    while(arrayPoli[c]=='-' || arrayPoli[c]=='+')
                    {
                        c++;
                        if(c==polinomio.length())
                        {
                            break;
                        }
                    }
                    if(arrayPoli[c]!='x' || arrayPoli[c]!='x' || !Character.isDigit(arrayPoli[c]))//nuestro metodo de lectura esta relacionado directamente con polinomios que tienen x como variable independiente, por esta razon despues de 
                    {//evaluar si tenemos un signo podemos ver si tiene una variable, ademas puede que despues del signo encontremos un digito que tambien se toma en cuenta
                        pro=false;
                    }
                    
                }
            }
            else if(arrayPoli[c]=='+')//en el caso de que comienze con un menos la sentencia se aplica la misma logica que con el mas
            {
                c++;
                if(arrayPoli[c]=='-' || arrayPoli[c]=='+')
                {
                    c++;
                    while(arrayPoli[c]=='-' || arrayPoli[c]=='+')
                    {
                        c++;
                        if(c==polinomio.length())
                        {
                            break;
                        }
                    }
                    if(arrayPoli[c]!='x' || arrayPoli[c]!='x' || !Character.isDigit(arrayPoli[c]))
                    {
                        pro=false;
                    }
                }
            }
            else if(arrayPoli[c]=='x' || arrayPoli[c]=='X')//cuando encontramos una x el siguiente caracter puede ser un nuevo signo o un simbolo que indica que x esta elevado a una potencia
            {
                c++;
                if(arrayPoli[c]=='^')//lo anterior esta representado aqui
                {
                    c++;
                    if(Character.isDigit(arrayPoli[c]) || arrayPoli[c]=='+')//despues del simbolo de potencia se toma como valido tener un signo mas indicando una potencia positiva o puede que siga un digito que indica la potencia como tal
                    {
                        c++;
                        while(Character.isDigit(arrayPoli[c]))//despues de ese digito puede seguir uno o mas digitos representados por el siguiente siclo
                        {
                            c++;
                            if(c==polinomio.length())
                            {
                                break;
                            }
                        }
                    }
                    else 
                        pro=false;
                }
            }
            else if(Character.isDigit(arrayPoli[c]))//tambien puede que despues de encontrar un signo encontremos un coeficiente o termino independiente, la siguiete parte se encarga de validar ese hecho
            {
                c++;
                while(Character.isDigit(arrayPoli[c]) || arrayPoli[c]=='/')
                    {
                        c++;
                        if(c<=polinomio.length())
                        {
                            break;
                        }
                    }
            }
            else
               pro=false; 
        }//el siclo se repite hasta que lleguemos al final del arreglo
        
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e)
        {
        }
        return pro;
    }
    public String derivapoli(String polinomio)
    {//este metodo es muy parecido a evaluar polinomio solo que en esta ocasion solo trabajamos con string para no modificar el valor de acuerdo a la posicion de los digitos, con la intencion de esto solo agregamos un
        String newpolinomio="",coheficiente="",cohe="",exponente="";//string que maneja el exponente y lo multiplica por el coheficiente, ademas de restarle 1 al valor del exponente y cuando solo tenemos x y ningun exponente
        char arrayPoli[]=polinomio.toCharArray();//sustraemos x para dejar un nuevo termino independiente 
        int c=0,signo=1;
        String div="/",x1="",sign;
        try{
        while(c<polinomio.length())//esto indica que el siclo se cumplira hasta que c tenga el mismo tamaño que la cantidad de caracteres que tiene el string que guarda al polinomio
        {
            coheficiente="";
            cohe="";
            div="";
            exponente="";
            sign="";
            if(arrayPoli[c]=='-')//para comenzar el puede que tengamos un signo -, un +, un numero o la variable x, lo cual esta representado en los siguientes if anidados
            {
                signo=-1;
                c++;
                while(arrayPoli[c]=='-' || arrayPoli[c]=='+')//para comenzar si la expresion tiene varios +'s y -'s lo que haremos sera reducirlos hasta llegar a tener solo uno
                {
                    if(arrayPoli[c]=='-')//sabemos que el signo solo cambia cuando tenemos un menos, por lo tanto si encuentra un más no hace nada pero si encuentra un menos cambia el valor multiplicado por -1 que representa el signo negativo
                    {
                        signo=signo*-1;
                    }
                    c++;
                }
                if(signo==-1)
                {
                    sign="-";
                }
                else
                    sign="+";
            }
            if(arrayPoli[c]=='+')// funcina en el mismo sentido reducir la cantidad de signos que tenemos a uno solo
            {
                signo=1;
                c++;
                while(arrayPoli[c]=='-' || arrayPoli[c]=='+')// de igual manera que cuando tenemos un - al principio y despues de ese signo tenemos mas signos, la intencion de esta parte es reducir los signos a uno final
                {
                    if(arrayPoli[c]=='-')//como se dijo si encontramos un signo + no cambia, pero si encontramos un signo - este si cambia
                    {
                        signo=signo*-1;
                    }
                    c++;
                }
                if(signo==-1)//al final si la variable signo es negativa, entonces se agregara un signo negativo  a nuestra derivada
                {
                    sign="_";
                }
                else
                    sign="+";
            }
            if(Character.isDigit(arrayPoli[c]))//tambien puede que al comienzo del polinomio tengamos un coeficiente o que este lo tengamos despues de un signo, esta parte del codigo se encarga de concaternarlo
            {
                coheficiente+=arrayPoli[c];
                c++;
                if(c<polinomio.length())
                {
                    if(Character.isDigit(arrayPoli[c]))// despues de un digito puede que venga otro y otro, eso dependera de la cantidad de digitos que vengan en el polinomio juntos y este while se encanrga de ir juntandolos en un string
                    {
                        while(Character.isDigit(arrayPoli[c]))// se juntan en un string con la intencion de seguir conservando su valor posicional 
                        {
                            coheficiente+=arrayPoli[c];
                            c++;
                            if(c==polinomio.length())
                            {
                                break;
                            }
                        }
                    }
                    if(arrayPoli[c]=='/')//sin embargo despues de un digito puede que siga una diagonal que indica una division, por tanto esta parte se encarga de verificar si existe est diagonal indicando la division
                    {
                        div="/";
                        c++;
                        while(Character.isDigit(arrayPoli[c]))//despues de la diagonal sigue al menos un digito, o puede seguir mas de uno por tanto este while se encarga de concatenarlos, es decir concatena el denominador
                        {
                            cohe+=arrayPoli[c];
                            c++;
                            if(c==polinomio.length())
                            {
                                break;
                            }
                        }
                    }
                    if(c<polinomio.length()){
                    if(arrayPoli[c]=='x' || arrayPoli[c]=='X')//despues de las opciones anteriores o como primera opcion seguira la variable, esta es la opcion que se encarga de guardar dicha variable
                    {
                        c++;
                        if(c<polinomio.length())
                        {
                            x1="x";
                            if(c<polinomio.length())
                            {
                                if(arrayPoli[c]=='^')//despues de verificar si contiene una variable pasamos a ver si esta elevada a una potencia, asi guardamos esto en x1, para poder ordenarlo al momento de concatenar la ueva string
                                {//que guardara la derivada del polinomio introducido
                                    x1+="^";
                                    c++;
                                    if(Character.isDigit(arrayPoli[c]))
                                    {
                                        exponente+=arrayPoli[c];
                                        c++;
                                        if(c<polinomio.length())
                                        {
                                            while(Character.isDigit(arrayPoli[c]))
                                            {
                                                exponente+=arrayPoli[c];
                                                c++;
                                                if(c==polinomio.length())
                                                {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if(exponente.equals("1"))
                                    {
                                        x1="";
                                    }
                                    if(cohe.equals(""))
                                    {
                                        if(exponente=="2")
                                        {
                                        newpolinomio+=sign+(Integer.parseInt(coheficiente)*Integer.parseInt(exponente))+div+cohe+x1
                                                +(Integer.parseInt(exponente)-1);
                                        }
                                        else
                                            newpolinomio+=sign+(Integer.parseInt(coheficiente)*Integer.parseInt(exponente))+div+cohe+x1
                                                +(Integer.parseInt(exponente)-1);
                                    }
                                    else
                                        newpolinomio+=sign+(Integer.parseInt(coheficiente)*Integer.parseInt(exponente))+div+cohe+x1
                                                +(Integer.parseInt(exponente)-1);
                                    }
                                    else
                                newpolinomio+=sign+coheficiente;
                                }
                            }
                            else
                            {
                                if(cohe.equals(""))
                                {
                                    newpolinomio+=sign+coheficiente+div+cohe;
                                }
                                else
                                    newpolinomio+=sign+coheficiente+div+cohe;
                                }
                    }
                        else
                        {
                            if(cohe.equals(""))
                            {
                                newpolinomio+=signo+coheficiente+div;
                            }
                            else
                              newpolinomio+=signo+coheficiente+div+cohe;
                        }
                    }}
                }
            if(c<polinomio.length())
            {
                if(arrayPoli[c]=='x' || arrayPoli[c]=='x')
                    {
                        if(c<polinomio.length())
                        {
                            x1="x";
                            c++;
                            if(c<polinomio.length())
                            {
                                if(arrayPoli[c]=='^')
                                {
                                    x1+="^";
                                    c++;
                                    if(Character.isDigit(arrayPoli[c]))
                                    {
                                        exponente+=arrayPoli[c];
                                        c++;
                                        if(c<polinomio.length())
                                        {
                                            while(Character.isDigit(arrayPoli[c]))
                                            {
                                                exponente+=arrayPoli[c];
                                                c++;
                                                if(c==polinomio.length())
                                                {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if(coheficiente.equals(""))
                                    {
                                        coheficiente="1";
                                        newpolinomio+=sign+(Integer.parseInt(coheficiente)*Integer.parseInt(exponente))+div+x1
                                                +(Integer.parseInt(exponente)-1+"");
                                    }
                                    else
                                        newpolinomio+=sign+(Integer.parseInt(coheficiente)*Integer.parseInt(exponente))+div+cohe+x1
                                                +(Integer.parseInt(exponente)-1);
                                    }
                                }
                                
                            }
                            else
                            {
                                if(cohe.equals(""))
                                {
                                    newpolinomio+=sign+coheficiente+div+cohe;
                                }
                                else
                                    newpolinomio+=sign+coheficiente+div+cohe;
                                }
                    }
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
        }
        return newpolinomio;
    
    }
    public String restarPoli(String poli)//este siguiente algoritmo cambia los simbolos de + y - por su contrario
    {
        String concatena="";
        int c=0;
        try
        {
            while(c<=polinomio.length())
            {
                if(polinomio.charAt(c)=='-')//si se encuentra un -, se cambia por un mas en general
                {
                    concatena+='+';
                    c++;
                }
                if(polinomio.charAt(c)=='+')//si se encuentra un menos en el polinomio se cambia por un -
                {
                    concatena+='-';
                    c++;
                }
                if(Character.isDigit(polinomio.charAt(c)))//si se encuentra un digito, quiere decir que es el primer valor y a este se le agrega un +
                {
                    concatena+='+';
                    c++;
                }
                if(polinomio.charAt(c)=='x' || polinomio.charAt(c)=='x')//cuando tenemos por primer termino a x, esta es positiva, por tanto se agrega un +
                {
                    concatena+='+';
                    c++;
                }
                while(polinomio.charAt(c)!='-' && polinomio.charAt(c)!='+')//este while nos permite avanzar por todos aquellos caracteres que no nos interese cambiar
                {
                    concatena+=polinomio.charAt(c);
                    if(polinomio.charAt(c)=='^')
                    {
                       if(polinomio.charAt(c+1)=='+')
                        {
                            c++;
                            concatena+=polinomio.charAt(c);
                        } 
                    }
                    c++;
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
        }
        return polinomio+=concatena;
    }
}
